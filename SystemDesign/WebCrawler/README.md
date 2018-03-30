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
