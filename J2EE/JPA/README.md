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
* @OneToOne: JPA one to one mapping is specified in cases where an entity is mapped to only one other entity. Consider a scenario such that every a Desk is allocated to a specific Employee.
* @OneToMany: JPA one to many mapping is just the reverse of ManyToOne. Taking the same example, i.e. multiple Employee entities belong to one department. We can say it that a Department entity can contain multiple Employee entities. 
   ```
   @OneToMany(mappedBy = "department")
	private List<Employee> employees;
   ```
* @ManyToOne: Multiple Employee entities belong to One Department entity. i.e. There should be JPA Many to One mapping between      these two entities. Employee table contains a foreign key to Department table using column iddepartment. This means Employee    table has the ownership of Department table as it is referenced through the foreign key.

   ```
   @ManyToOne
   @JoinColumn(name = "iddepartment")
   private Department department;
   ```
   * The side having @JoinColumn annotation is called as the owning side of the relationship. Here, Employee is the owner.
   * The side which does not have @JoinColumn is the non-owning or inverse side. Here, Department is the inverse side.
   * @JoinColumn is always defined on the owning side of relationship.
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
	* @ManyToMany annotation in Project entity shows inverse relationship hence it uses mappedBy=projects to refer to the field in Employee entity. 
	
## Cascade Operations and Relationships
* Entities that use relationships often have dependencies on the existence of the other entity in the relationship. For example, a line item is part of an order; if the order is deleted, the line item also should be deleted. This is called a cascade delete relationship.

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

