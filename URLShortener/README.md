## Background and strategic fit
We currently use the Google URL shortening API (goo.gl) to create a short URL to the appointment confirmation link that is sent in appointment reminder SMS messages.  A short URL is needed because SMS messages are limited to 160 characters total, and the full URL to the appointment confirmation page, combined with the other text in the message, is much longer than 160 characters.

The goo.gl service is free, but Google has a rate limit of 100 calls per 100 seconds to the API.  It has also been observed that the call can take up to 2 or 3 seconds to return a shortened URL.  This has caused issues in our SMS appointment reminder service, because we depend on the shortened URLs to include in the message text.  The goo.gl service returns many failures, likely due to the rate limiter.  We have been getting around this by running the SMS process multiple times per day, as customer usage and SMS volume increases, the SMS process is running later and later into the day.

**Google API Limits:**
The API limits requests to 1 request / per second / per user. A user is defined as a unique IP address. So if you were doing your load testing from a single IP this would have cause your rate limit issue.

## Data Constraints:
There will able around 5k to 10K URL's to be generated per day. So roughly around 300,000 new URL's per month.

## Skeleton Design: (Few Q&A)
How should we define our APIs?
* ShorteningAPI(url) {store the url_mapping and return hash(url)}
* RedirectionAPI(hash) {redirect_to url_mapping[hash]}

Basically, we are trying to build a service which serves as a huge Hash Map
## How should we compute the hash of a URL?

Since the requirement is asking for a repeatable behavior i.e.  when the same long URL is shortened multiple times, the same shortened URL is generated. Therefore, randomization cannot to be introduced.

## What data do we need to store?
Data storage layer: Hash => URL mapping. This helps as a look up table during redirection.

## High-level idea:
When a URL shortener gets a new URL, it has to create a new short URL that is not yet taken and return this. It will then store the short URL and the long URL in a key-value store and use this at lookup time. (i.e. when the user clicks on short URL redirection to appointment confirmation page)

Since URL shortening is nothing but a huge hash map. We need to have a **Bijective Function**. This is necessary so that you can find a inverse function g('abcde') = 123456 for your f(123456) = 'abcde' function. i.e longURL to shortURL and viceversa mapping.
## Example:
* Base URL https://temp.healthfusionclaims.com/portal/appointmentconfirmsms.do?id=
* Confirm Params: PRACTICE_ID=413779&MEMBER_ID=3002358&APPOINTMENT_ID=1041419&TIMESTAMP=1490172292231
* Final Long URL: https://temp.healthfusionclaims.com/portal/appointmentconfirmsms.do?id=UFJBQ1RJQ0VfSUQ9NDEzNzc5Jk1FTUJFUl9JRD0zMDAyMzU4JkFQUE9JTlRNRU5UX0lEPTEwNDE0MTkmVElNRVNUQU1QPTE0OTAxNzIyOTIyMzE=
Final Short URL: https://hfsn.us/b3DBKS

## Low Level Design:
**Solution 1 (Simple but dependent on a RDBMS database):**
Database Design:
id (auto increment)
hash
URL
123456	b3DBKS 	https://temp.healthfusionclaims.com/portal/appointmentconfirmsms.do?id=UFJBQ1RJQ0VfSUQ9NDEzNzc5Jk1FTUJFUl9JRD0zMDAyMzU4JkFQUE9JTlRNRU5UX0lEPTEwNDE0MTkmVElNRVNUQU1QPTE0OTAxNzIyOTIyMzE=

Idea is to use a auto increment value (Let’s say a 6-digit number) generated from the DB and convert it to character string that is at most 6 characters long. This problem can basically seen as a base conversion problem. i.e. number with base 10 to X with base 62

**A URL character can be one of the following**
* A lower case alphabet [‘a’ to ‘z’], total 26 characters
* An upper case alphabet [‘A’ to ‘Z’], total 26 characters
* A digit [‘0’ to ‘9’], total 10 characters
There are total 26 + 26 + 10 = 62 possible characters. 
Example : 125 base 10 = 2×62^1 + 1×62^0 = [2,1]. 

Now map the indices 2 and 1 to your alphabet. With 2 → c and 1 → b you will receive cb base 62 as the shortened URL. https://hfsn.us/cb

Note : We also need a alphabet map for alphabet to number lookup. Ex: a→0, b→1, c→2, d→3 and so on.

Finally to get the original long url, we need to get url id in database. The id can be obtained using base 62 to decimal conversion. For this we do a reverse lookup in alphabet map to find database-record with WHERE id = 125 and do the redirection

## Solution 2 (Hashing logic not dependent on DB):
Compute the shorten url using base conversion (from 10-base to 62-base) and java hashing function to arrive at a single big integer and then reduce it to a unique 6-digit integer using base 62 and finally populate the letters using alphabet map lookup. we are taking about 62^6 i.e more than 5 billion unique URL's.
**Store this key value entry using below either of the two options:**
* Use a MYSQL (Index Lookups are very fast) table to store (hash, original_url, expiration_date). This way we can run a cleanup SQL to delete entries from the DB when the ApptComfirmSMSer process is finished for the day.

**For Cheap Pricing :** Use Amazon S3 to keep this key value store and define object expiration rule at the bucket level. Since the users no need to access this short URL after the appointment date, we can use the day after appointment as an expiration rule.
**Single digit millisecond latency :** Use Amazon Dynamo DB as Key-Value store. Time To Live (TTL) for DynamoDB allows you to define when items in a table expire so that they can be automatically deleted from the database.

Database Design: For the below table, we could add a unique key constraint on the hash column to make sure that the hash will always be unique. In case of failures we can do a retry at the end of the process.
hash
URL
expiration_date
b3DBKS 	https://temp.healthfusionclaims.com/portal/appointmentconfirmsms.do?id=UFJBQ1RJQ0VfSUQ9NDEzNzc5Jk1FTUJFUl9JRD0zMDAyMzU4JkFQUE9JTlRNRU5UX0lEPTEwNDE0MTkmVElNRVNUQU1QPTE0OTAxNzIyOTIyMzE=	2017-03-23 10:54:30.22

