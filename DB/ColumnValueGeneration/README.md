## Choosing Between IDENTITY and SEQUENCE

Although both identity columns and SEQUENCE objects are useful for generating incremental numeric values, you will be confronted
with situations where you will have to choose between the two. 
Consider the following criteria for when to use one instead of the other. 

## Identity columns are useful when: 
* Only one column in a table requires automatically generated values
* Each row requires a separate value
* An automatic generator is desired for a primary key of a table
* The LOAD utility is not used to load data into the table
* The process of generating a new value is tied closely to inserting into a table, regardless of how the insert happens

## SEQUENCE objects are useful when:
* Values generated from one sequence are to be stored in more than one table.
* More than one column per table requires automatically generated values (multiple values may be generated for each row using the same sequence or more than one sequence)
* The process of generating a new value is independent of any reference to a table.
* Unlike SEQUENCE objects, which are more flexible, identity columns must adhere to several rigid requirements. 
For example, an IDENTITY column is always defined on a single table and each table can have at most one IDENTITY column. 

Furthermore, when you create an IDENTITY column, the data type for that column must be numeric; not so for sequences. If you used a SEQUENCE object to generate a value you could put that generated into a CHAR column, for example. Finally, when defining an IDENTITY column you cannot specify the DEFAULT clause and the column is implicitly defined as NOT NULL. Remember, DB2 automatically generates the IDENTITY column’s value, so default values and nulls are not useful concepts.
