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

Implementation of Trie [here].






[here]: https://github.com/syrilster/DataStructuresAndAlgo/tree/master/src/Trie

