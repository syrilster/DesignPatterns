## What is Typeahead Suggestion?
Typeahead suggestions enable users to search for known and frequently searched terms. As the user types into the search box, it tries to predict the query based on the characters the user has entered and gives a list of suggestion to complete the query. Typeahead suggestions help the user to articulate their search queries better. It’s not about speeding up the search process but rather about guiding the users and lending them a helping hand in constructing their search query.

## Requirements and Goals of the System
Functional Requirements: As the user types in their query, our service should suggest top 10 terms starting with whatever user has typed.

Non-function Requirements: The suggestions should appear in real-time. The user should be able to see the suggestions within 200ms.

## Basic System Design and Algorithm
Here we have a lot of ‘strings’ that need to be stored in such a way that users can search on any prefix. This service will suggest next terms that will match the given prefix. For example, if our database contains following terms: cap, cat, captain, capital; and the user has typed in ‘cap’, our system should suggest ‘cap’, ‘captain’ and ‘capital’.

Since we’ve to serve a lot of queries with minimum latency, we need to come up with a scheme that can efficiently store our data such that it can be queried quickly. We can’t depend upon some database for this; we need to store our index in memory in a highly efficient data structure.

One of the most appropriate data structure that can serve our purpose would be the **Trie (pronounced “try”)**. A trie is a tree-like data structure used to store phrases where each node stores a character of the phrase in a sequential manner. For example, if we need to store ‘cap, cat, caption, captain, capital’ in the trie, it would look like:

![trie](https://user-images.githubusercontent.com/6800366/41505471-a3ebe0a2-7227-11e8-8745-cd6cf3e3ffe5.PNG)

Custom Implementation of Trie DS can be found [here].

Now if the user has typed ‘cap’, our service can traverse the trie to go to the node ‘P’ to find all the terms that start with this prefix (e.g., cap-tion, cap-ital etc).

We can merge nodes that have only one branch to save storage space. The above trie can be stored like this:

![trie merged](https://user-images.githubusercontent.com/6800366/41505565-3bad53e8-7229-11e8-8e0a-c7eb54e02a6c.PNG)

## How to find top suggestion? 

Now that we can find all the terms given a prefix, how can we know what’re the top 10 terms that we should suggest? One simple solution could be to store the count of searches that terminated at each node, e.g., if users have searched about ‘CAPTAIN’ 100 times and ‘CAPTION’ 500 times, we can store this number with the last character of the phrase. So now if the user has typed ‘CAP’ we know the top most searched word under the prefix ‘CAP’ is ‘CAPTION’. So given a prefix, we can traverse the sub-tree under it, to find the top suggestions.

Given a prefix, how much time it can take to traverse its sub-tree? Given the amount of data we need to index, we should expect a huge tree. Even, traversing a sub-tree would take really long, e.g., the phrase ‘system design interview questions’ is 30 levels deep. Since we’ve very tight latency requirements, we do need to improve the efficiency of our solution.

## Can we store top suggestions with each node? 

This can surely speed up our searches but will require a lot of extra storage. We can store top 10 suggestions at each node that we can return to the user. We’ve to bear the big increase in our storage capacity to achieve the required efficiency.

## How would we build this trie? 
We can efficiently build our trie bottom up. Each parent node will recursively call all the child nodes to calculate their top suggestions and their counts. Parent nodes will combine top suggestions from all of their children to determine their top suggestions.

## How to update the trie? 
Assuming five billion searches every day, which would give us approximately 60K queries per second. If we try to update our trie for every query it’ll be extremely resource intensive and this can hamper our read requests too. One solution to handle this could be to update our trie offline after a certain interval.

As the new queries come in, we can log them and also track their frequencies. Either we can log every query or do sampling and log every 1000th query. For example, if we don’t want to show a term which is searched for less than 1000 times, it’s safe to log every 1000th searched term.

We can have a **Map-Reduce (MR)** setup to process all the logging data periodically, say every hour. These MR jobs will calculate frequencies of all searched terms in the past hour. We can then update our trie with this new data. We can take the current snapshot of the trie and update it with all the new terms and their frequencies. We should do this offline, as we don’t want our read queries to be blocked by update trie requests. We can have two options:

We can make a copy of the trie on each server to update it offline. Once done we can switch to start using it and discard the old one.


Another option is we can have a master-slave configuration for each trie server. We can update slave while the master is serving traffic. Once the update is complete, we can make the slave our new master. We can later update our old master, which can then start serving traffic too.


## How can we update the frequencies of typeahead suggestions? 
Since we are storing frequencies of our typeahead suggestions with each node, we need to update them too. We can update only difference in frequencies rather than recounting all search terms from scratch. 

If we’re keeping count of all the terms searched in last 10 days, we’ll need to subtract the counts from the time period no longer included and add the counts for the new time period being included. We can add and subtract frequencies based on Exponential Moving Average (EMA) of each term. In EMA, we give more weight to the latest data. It’s also known as the exponentially weighted moving average.

After inserting a new term in the trie, we’ll go to the terminal node of the phrase and increase its frequency. Since we’re storing the top 10 queries in each node, it is possible that this particular search term jumped into the top 10 queries of a few other nodes. So, we need to update the top 10 queries of those nodes then. 

We’ve to traverse back from the node to all the way up to the root. For every parent, we check if the current query is part of the top 10. If so, we update the corresponding frequency. If not, we check if the current query’s frequency is high enough to be a part of the top 10. If so, we insert this new term and remove the term with the lowest frequency.

## How can we remove a term from the trie? 
Let’s say we’ve to remove a term from the trie, because of some legal issue or hate or piracy etc. We can completely remove such terms from the trie when the regular update happens, meanwhile, we can add a filtering layer on each server, which will remove any such term before sending them to users.

## What could be different ranking criteria for suggestions? 
In addition to a simple count, for terms ranking, we have to consider other factors too, e.g., freshness, user location, language, demographics, personal history etc.



[here]: https://github.com/syrilster/DataStructuresAndAlgo/tree/master/src/Trie

