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

A relationship can be bidirectional or unidirectional, e.g. in a bidirectional relationship both classes store a reference to each other while in an unidirectional case only one class has a reference to the other class. Within a bidirectional relationship you need to specify the owning side of this relationship in the other class with the attribute "mappedBy" example: 

@ManyToMany(mappedBy="attributeOfTheOwningClass".

**Relationship annotations:**
* @OneToOne: JPA one to one mapping is specified in cases where an entity is mapped to only one other entity. Consider a scenario such that every a Desk is allocated to a specific Employee.
* @ManyToOne: Multiple Employee entities belong to One Department entity. i.e. There should be JPA Many to One mapping between      these two entities. Employee table contains a foreign key to Department table using column iddepartment. This means Employee    table has the ownership of Department table as it is referenced through the foreign key.

   ```
   @ManyToOne
   @JoinColumn(name = "iddepartment")
   private Department department;
   ```
   * The side having @JoinColumn annotation is called as the owning side of the relationship. Here, Employee is the owner.
   * The side which does not have @JoinColumn is the non-owning or inverse side. Here, Department is the inverse side.
   * @JoinColumn is always defined on the owning side of relationship.
   
* @OneToMany: JPA one to many mapping is just the reverse of ManyToOne. Taking the same example, i.e. multiple Employee entities belong to one department. We can say it that a Department entity can contain multiple Employee entities. 
   ```
   @OneToMany(mappedBy = "department")
	private List<Employee> employees;
   ```
* @ManyToMany: Consider a scenario that employees can belong to multiple projects. i.e. Multiple employees can work on multiple projects. Create a database such that Employee table has the primary key idemployee. Project table has primary key idproject. Employee_Project table which has columns as foreign keys to Employee and Project tables. Employee_Project table is a child table to store the mappings.

	```
	Employee.java:
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "employee_project", joinColumns = @JoinColumn(name = "idemployee"), inverseJoinColumns = @JoinColumn(name = "idproject"))
	private List<Project> projects;

	Project.java:
	@ManyToMany(mappedBy = "projects", cascade = CascadeType.PERSIST)
	private List<Employee> employees;
	```
	* @JoinColumn specifies the name of column that will refer to the Entity to be considered as owner of the association while **@inverseJoinColumn** specifies the name of inverse side of relationship. In this example we have chosen Employee as the owner so @JoinColumn refers to idemployee column in join table employee_project and @InverseJoinColumn refers to idproject which is inverse side of jpa many to many mapping.
	* @ManyToMany annotation in Project entity shows inverse relationship hence it uses mappedBy=projects to refer to the projects attribute in Employee entity. 
	
## Here's a quick cheat sheet of the JPA world:

* A Cache is a copy of data, copy meaning pulled from but living outside the database.
* Flushing a Cache is the act of putting modified data back into the database.
* A **PersistenceContext** is essentially a Cache. It also tends to have it's own non-shared database connection.
* An EntityManager represents a PersistenceContext (and therefore a Cache)
* An EntityManagerFactory creates an EntityManager (and therefore a PersistenceContext/Cache)	
	
## Cascade Operations and Relationships
Entities that use relationships often have dependencies on the existence of the other entity in the relationship. For example, a line item is part of an order; if the order is deleted, the line item also should be deleted. This is called a cascade delete relationship.

**Cascade Operation**
* ALL: All cascade operations will be applied to the parent entity’s related entity. All is equivalent to specifying cascade={DETACH, MERGE, PERSIST, REFRESH, REMOVE}
* DETACH:  If the parent entity is detached from the persistence context, the related entity will also be detached.
* MERGE: If the parent entity is merged into the persistence context, the related entity will also be merged.
* PERSIST: If the parent entity is persisted into the persistence context, the related entity will also be persisted.
* REFRESH: If the parent entity is refreshed in the current persistence context, the related entity will also be refreshed.
* REMOVE: If the parent entity is removed from the current persistence context, the related entity will also be removed.

Cascade delete relationships are specified using the cascade=REMOVE element specification for @OneToOne and @OneToMany relationships. For example:
```
@OneToMany(cascade=REMOVE, mappedBy="customer")
public Set<Order> getOrders() { return orders; }
```

## Cache Types
EclipseLink provides several different cache types which have different memory requirements. The size of the cache (in number of cached objects) can also be configured. The cache type and size to use depends on the application, the possibility of stale data, the amount of memory available in the JVM and on the machine, the garbage collection cost, and the amount of data in the database.

By default, EclipseLink uses a SOFT_WEAK with an initial size of 100 objects. The cache size is not fixed, but just the initial size, EclipseLink will never eject an object from the cache until it has been garbage collected from memory. It will eject the object if the CACHE type is used, but this is not recommended. 

**FULL Cache Type**
This option provides full caching and guaranteed identity: objects are never flushed from memory unless they are deleted. This method may be memory-intensive when many objects are read. Do not use this option on batch operations.

**WEAK Cache Type**
This option only caches objects that have not been garbage collected. Any object still referenced by the application will still be cached. The weak cache type uses less memory than full identity map but also does not provide a durable caching strategy across client/server transactions. Objects are available for garbage collection when the application no longer references them on the server side (that is, from within the server JVM).

**SOFT Cache Type**
This option is similar to the weak cache type, except that the cache uses soft references instead of weak references. Any object still referenced by the application will still be cached, and objects will only be removed from the cache when memory is low.

The soft identity map allows for optimal caching of the objects, while still allowing the JVM to garbage collect the objects if memory is low.

**SOFT_WEAK and HARD_WEAK Cache Type**
These options are similar to the weak cache except that they maintain a most frequently used sub-cache. The sub-cache uses soft or hard references to ensure that these objects are not garbage collected, or only garbage collected only if the JVM is low on memory.

The soft cache and hard cache provide more efficient memory use. They release objects as they are garbage-collected, except for a fixed number of most recently used objects. Note that weakly cached objects might be flushed if the transaction spans multiple client/server invocations. The size of the sub-cache is proportional to the size of the cache as specified by the @Cache size attribute. You should set the cache size to the number of objects you wish to hold in your transaction.

Oracle recommends using this cache in most circumstances as a means to control memory used by the cache.

Example:

```
@Cache(
  type= CacheType.SOFT_WEAK, // Cache everything until the JVM decides memory is low.
  size=100000  // Use 100,000 as the initial cache size.
)
@Table(name = "coded_element")
```

**NONE and CACHE**
NONE and CACHE options do not preserve object identity and should only be used in very specific circumstances. NONE does not cache any objects. CACHE only caches a fixed number of objects in an LRU fashion. These cache types should only be used if there are no relationships to the objects.Oracle does not recommend using these options. To disable caching, set the cache isolation to ISOLATED instead.

```
JPA:

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "document_request_type_key", discriminatorType = DiscriminatorType.INTEGER)


```

## Inheritance Strategies
JPA support three types of inheritance strategies such as:

* SINGLE_TABLE: Single-Table strategy takes all classes fields (both super and sub classes) and map them down into a single table known as SINGLE_TABLE strategy. Here discriminator value plays key role in differentiating the values of three entities in one table.
Example: In the below mapping there is only one table code and it is used by many entities depending on the change in discriminator value in the column code_type_key
```
@Table(name = "code")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "code_type_key", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(Constants.CODE_ADDRESS_TYPE + "")
```
* JOINED_TABLE: Joined table strategy is to share the referenced column which contains unique values to join the table and make easy transactions. Example: In the below code snippet there are 3 tables in the DB: document_request, outbound_document_request and inbound_document_request and we use inheritence to share the column document_request_key from document_request table. The tables outbound_document_request and inbound_document_request have document_request_key but in the java entity it is not present as this value is populated from the parent.

```
DocumentRequest.java

@Entity
@Cacheable(false)
@Table(name = "document_request")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "document_request_type_key", discriminatorType = DiscriminatorType.INTEGER)
public abstract class DocumentRequest {
}
-------------------------------------------

InboundRequest.java

@Entity
@Cacheable(false)
@Table(name = "inbound_document_request")
@DiscriminatorValue(DocumentRequestType.DOCUMENT_REQUEST_INBOUND_TYPE)
public class InboundDocumentRequest extends DocumentRequest {
}

OutboundDocumentRequest.java

@Entity
@Cacheable(false)
@Table(name = "outbound_document_request")
@DiscriminatorValue(DocumentRequestType.DOCUMENT_REQUEST_OUTBOUND_TYPE)
public class OutboundDocumentRequest extends DocumentRequest {
}

```
* TABLE_PER_CONCRETE_CLASS: Table per class strategy is to create a table for each sub entity.
