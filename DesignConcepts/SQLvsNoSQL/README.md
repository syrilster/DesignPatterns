Relational databases are structured and have predefined schemas, like phone books that store phone numbers and addresses. Non-relational databases are unstructured, distributed and have a dynamic schema, like file folders that hold everything from a person’s address and phone number to their Facebook ‘likes’ and online shopping preferences.

## SQL
Relational databases store data in rows and columns. Each row contains all the information about one entity, and columns are all the separate data points. Some of the most popular relational databases are MySQL, Oracle, MS SQL Server, SQLite, Postgres, MariaDB, etc.

## NoSQL
Following are most common types of NoSQL:

Columnar DB:
* Store each column in a seperate file
* Column repreesenttion compresses well. Each column can be compressed using a different scheme.
    Example : If a table is sorted by stock symbol, run length encoding can be used.
    APPL, APPL, APPL, IBM, IBM --> APPL(3), IBM(2)
* But the above compression example can't be used for all columns. like for example a stock price column which is floating point and changes too often. Here a different compression technique should be applied.
