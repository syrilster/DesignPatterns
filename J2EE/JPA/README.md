## What is JPA?
* Java Persistence API introduced in JavaEE 5, a new standard for persistence.
* Java objects to get the required SQL statements to fetch values from DB.
* Hibernate was invented by Gavin King to overcome the problem of javaee entity beans.
* Hibernate did not follow any specific standard and it was developed initially for JBoss.
* JavaEE 5 takes on Hibernate by providing standards called JPA.

## Java Data Access
* JDBC - Very low level. (Requires lot of boiler plate code)

## Implementations of JPA
* Hibernate remains a leading implementation.
* EclipseLink (used to be TopLink by Oracle).
* Apache OpenJPA

## JPA Notations
* POJO having @Entity annotation.
* Id annotation for the primary key mapping.
* No arg constructor required for JPA's new instances.
* EntityManager to manage all the different entities.
* EntityManagerFactory to boot strap JPA.
* Need to handle transactions with JPA.
* @Transient: Field will not be saved in database

## Relationship Mapping
JPA allows to define relationships between classes. Classes can have one to one, one to many, many to one, and many to many relationships with other classes.

A relationship can be bidirectional or unidirectional, e.g. in a bidirectional relationship both classes store a reference to each other while in an unidirectional case only one class has a reference to the other class. Within a bidirectional relationship you need to specify the owning side of this relationship in the other class with the attribute "mappedBy" eample: @ManyToMany(mappedBy="attributeOfTheOwningClass".

**Relationship annotations:**
* @OneToOne
* @OneToMany
* @ManyToOne
* @ManyToMany

