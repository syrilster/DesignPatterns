## What is a Web Crawler?
A web crawler is a software program which browses the World Wide Web in a methodical and automated manner. Many sites, particularly search engines, use web crawling as a means of providing up-to-date data. Search engines download all the pages to create an index on them to perform faster searches.

Some other uses of web crawlers are:

* To test web pages and links for valid syntax and structure.
* To monitor sites to see when their structure or contents change.
* To maintain mirror sites for popular Web sites.
* To search for copyright infringements.
* To build a special-purpose index, e.g., one that has some understanding of the content stored in multimedia files on the Web.

## Design Considerations
Crawling the web is a complex task, and there are many ways to go about it. 

Our crawler is going to deal with HTML only, but it should be extensible and make it easy to add support for new media types.

**What is the expected number of pages we will crawl?**
How big will the URL database become? Assuming we need to crawl one billion websites. Since a website can contain many, many URLs, let’s assume an upper bound of 15 billion different web pages that will be reached by our crawler.

**What is ‘RobotsExclusion’ and how should we deal with it?**
Courteous Web crawlers implement the Robots Exclusion Protocol, which allows Webmasters to declare parts of their sites off limits to crawlers. The Robots Exclusion Protocol requires a Web crawler to fetch a special document called robot.txt, containing these declarations from a Web site before downloading any real content from it.

## High Level design
The basic algorithm executed by any Web crawler is to take a list of seed URLs as its input and repeatedly execute the following steps:

* Pick a URL from the unvisited URL list.
* Determine the IP Address of its host-name.
* Establishing a connection to the host to download the corresponding document.
* Parse the document contents to look for new URLs.
* Add the new URLs to the list of unvisited URLs.
* Process the downloaded document, e.g., store it or index its contents, etc.
* Go back to step 1

**How to crawl?**
**Breadth first or depth first?** Breadth-first search (BFS) is usually used. However, Depth First Search (DFS) is also utilized in some situations, such as if your crawler has already established a connection with the website, it might just DFS all the URLs within this website to save some handshaking overhead.

**Path-ascending crawling:** Path-ascending crawling can help discover a lot of isolated resources or resources for which no inbound link would have been found in regular crawling of a particular Web site. In this scheme, a crawler would ascend to every path in each URL that it intends to crawl. For example, when given a seed URL of http://foo.com/a/b/page.html, it will attempt to crawl /a/b/, /a/, and /.

**Difficulties in implementing efficient web crawler**
There are two important characteristics of the Web that makes Web crawling a very difficult task:

* Large volume of Web pages: A large volume of web page implies that web crawler can only download a fraction of the web pages at any time and hence it is critical that web crawler should be intelligent enough to prioritize download.

* Rate of change on web pages. Another problem with today’s dynamic world is that web pages on the internet change very frequently, as a result, by the time the crawler is downloading the last page from a site, the page may change, or a new page has been added to the site.

**A bare minimum crawler needs at least these components:**

* URL frontier: To store the list of URLs to download and also prioritize which URLs should be crawled first.
* HTTP Fetcher: To retrieve a web page from the server.
* Extractor: To extract links from HTML documents.
* Duplicate Eliminator: To make sure same content is not extracted twice unintentionally.
* Datastore: To store retrieve pages and URL and other metadata.

## Detailed Component Design
Let’s assume our crawler is running on one server, and all the crawling is done by multiple working threads, where each working thread performs all the steps needed to download and process a document in a loop.

* The first step of this loop is to remove an absolute URL from the shared URL frontier for downloading. An absolute URL begins with a scheme (e.g., “HTTP”), which identifies the network protocol that should be used to download it. 

* Based on the URL’s scheme, the worker calls the appropriate protocol module to download the document. 

* After downloading, the document is placed into a Document Input Stream (DIS). Putting documents into DIS will enable other modules to re-read the document multiple times.

* Once the document has been written to the DIS, the worker thread invokes the dedupe test to determine whether this document (associated with a different URL) has been seen before. If so, the document is not processed any further, and the worker thread removes the next URL from the frontier.

* Next, our crawler needs to process the downloaded document. Each document can have a different MIME type like HTML page, Image, Video, etc. Based on the downloaded document’s MIME type, the worker invokes the process method of each processing module associated with that MIME type.

* Furthermore, our HTML processing module will extract all links from the page. Each link is converted into an absolute URL and tested against a user-supplied URL filter to determine if it should be downloaded. If the URL passes the filter, the worker performs the URL-seen test, which checks if the URL has been seen before, namely, if it is in the URL frontier or has already been downloaded. If the URL is new, it is added to the frontier.

![image](https://user-images.githubusercontent.com/6800366/38130903-70260380-3422-11e8-8d7e-1da309bc660d.png)

## Components involved

* The URL frontier: The URL frontier is the data structure that contains all the URLs that remain to be downloaded. We can crawl by performing a breadth-first traversal of the Web, starting from the pages in the seed set. Such traversals are easily implemented by using a FIFO queue.

Since we’ll be having a huge list of URLs to crawl, we can distribute our URL frontier into multiple servers. Let’s assume on each server we have multiple worker threads performing the crawling tasks. Let’s also assume that our hash function maps each URL to a server which will be responsible for crawling it.

** politeness**

Every site has different politeness requirements and ignoring might lead to IP blocks. Expect mails from web admins!. You need to cache the robot.txt files for every domain and follow the rules to avoid getting blocked. Also sites usually have time-gap requirements and this means slowing the fetching rate. **If your system is distributed, better to aggregate all requests pertaining to a domain on the same node.**

**To implement this politeness constraint**, our crawler can have a collection of distinct FIFO sub-queues on each server. Each worker thread will have its separate sub-queue, from which it removes URLs for crawling. When a new URL needs to be added, the FIFO sub-queue in which it is placed will be determined by the URL’s canonical hostname. Our hash function can map each hostname to a thread number. Together, these two points imply that at most one worker thread will download documents from a given Web server and also by using FIFO queue it’ll not overload a Web server.

**How big our URL frontier would be?**
The size would be in the hundreds of millions of URLs. Hence, we need to store our URLs on disk. We can implement our queues in such a way that they have separate buffers for enqueuing and dequeuing. Enqueue buffer, once filled will be dumped to the disk, whereas dequeue buffer will keep a cache of URLs that need to be visited, it can periodically read from disk to fill the buffer.

* The fetcher module: The purpose of a fetcher module is to download the document corresponding to a given URL using the appropriate network protocol like HTTP. As discussed above webmasters create robot.txt to make certain parts of their websites off limits for the crawler. To avoid downloading this file on every request, our crawler’s HTTP protocol module can maintain a fixed-sized cache mapping host-names to their robots exclusion rules.

* Document input stream: Our crawler’s design enables the same document to be processed by multiple processing modules. To avoid downloading a document multiple times, we cache the document locally using an abstraction called a Document Input Stream (DIS).

A DIS is an input stream that caches the entire contents of the document read from the internet. It also provides methods to re-read the document. The DIS can cache small documents (64 KB or less) entirely in memory, while larger documents can be temporarily written to a backing file.

Each worker thread has an associated DIS, which it reuses from document to document. After extracting a URL from the frontier, the worker passes that URL to the relevant protocol module, which initializes the DIS from a network connection to contain the document’s contents. The worker then passes the DIS to all relevant processing modules.

* Document Dedupe test: Many documents on the Web are available under multiple, different URLs. There are also many cases in which documents are mirrored on various servers. Both of these effects will cause any Web crawler to download the same document contents multiple times. To prevent processing a document more than once, we perform a dedupe test on each document to remove duplication.

To perform this test, we can calculate a 64-bit checksum of every processed document and store it in a database. For every new document, we can compare its checksum to all the previously calculated checksums to see the document has been seen before. We can use MD5 or SHA to calculate these checksums.

How big would be the checksum store? If the whole purpose of our checksum store is to do dedupe, then we just need to keep a unique set containing checksums of all previously processed document. Considering 15 billion distinct web pages, we would need:

15B * 8 bytes => 120 GB

Although this can fit into a modern-day server’s memory, if we don’t have enough memory available, we can keep smaller LRU based cache on each server with everything in a persistent storage. The dedupe test first checks if the checksum is present in the cache. If not, it has to check if the checksum resides in the back storage. If the checksum is found, we will ignore the document. Otherwise, it will be added to the cache and back storage.

**A distributed data structure like redis is also worth considering for URL frontier and duplicate detector.**

* URL filters: The URL filtering mechanism provides a customizable way to control the set of URLs that are downloaded. This is used to blacklist websites so that our crawler can ignore them. Before adding each URL to the frontier, the worker thread consults the user-supplied URL filter. We can define filters to restrict URLs by domain, prefix, or protocol type.

* Domain name resolution: Before contacting a Web server, a Web crawler must use the Domain Name Service (DNS) to map the Web server’s hostname into an IP address. DNS name resolution will be a big bottleneck of our crawlers given the amount of URLs we will be working with. To avoid repeated requests, we can start caching DNS results by building our local DNS server.

* URL dedupe test: While extracting links, any Web crawler will encounter multiple links to the same document. To avoid downloading and processing a document multiple times, a URL dedupe test must be performed on each extracted link before adding it to the URL frontier.

To perform the URL dedupe test, we can store all the URLs seen by our crawler in canonical form in a database. To save space, we do not store the textual representation of each URL in the URL set, but rather a fixed-sized checksum.

To reduce the number of operations on the database store, we can keep an in-memory cache of popular URLs on each host shared by all threads. The reason to have this cache is that links to some URLs are quite common, so caching the popular ones in memory will lead to a high in-memory hit rate.

How much storage we would need for URL’s store? If the whole purpose of our checksum is to do URL dedupe, then we just need to keep a unique set containing checksums of all previously seen URLs. Considering 15 billion distinct URLs and 2 bytes for checksum, we would need:

15B * 2 bytes => 30 GB

**Can we use bloom filters for deduping?**

In a nutshell, a bloom filter is a space-efficient probabilistic data structure that allows you to test if an element is in a set. However, it may have false positive. In other words, if a bloom filter can tell you either a URL is definitely not in the pool or it probably is in the pool.

To briefly explain how bloom filter works, an empty bloom filter is a bit array of m bits (all 0). There are also k hash functions that map each element to one of the m bits. So when we add a new element (URL) into the bloom filter, we will get k bits from the hash functions and set all of them to 1. Thus, when we check the existence of an element, we first get the k bits for it and if any of them is not 1, we know immediately that the element doesn’t exist. However, if all of the k bits are 1, this can come from the combination of several other elements.

Bloom filter is a very commonly used technique and it’s the perfect solution for deduping URLs in a web crawler.

The disadvantage to using a bloom filter for the URL seen test is that each false positive will cause the URL not to be added to the frontier, and therefore the document will never be downloaded. The chance of a false positive can be reduced by making the bit vector larger.

* Checkpointing: A crawl of the entire Web takes weeks to complete. To guard against failures, our crawler can write regular snapshots of its state to disk. An interrupted or aborted crawl can easily be restarted from the latest checkpoint.
