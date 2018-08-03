## Entity State Transitions

![entity-lifecycle-state-tiny](https://user-images.githubusercontent.com/6800366/43621948-1b467ad0-971d-11e8-85c9-c712231160ec.png)


* If an entity is attached to the current persistence context, it has the lifecycle state managed. 
It means that it is mapped to a database record. Your persistence provider generates the required SQL INSERT and UPDATE statements
to propagate all changes. A managed entity is also **stored in the 1st level cache**.
* When you create a new entity, it’s in the transient state. It remains in this state until you attach it to the current persistence 
context. As long as an entity is in the transient state, it is not mapped to a database record and not managed by any persistence context.
* Entities in the detached lifecycle state are no longer managed by the persistence context. That can be the case because 
you closed the persistence context or you explicitly detached the entity from the current context. 
* And the last lifecycle state is removed. These entities were previously in the state managed, before you scheduled them 
for removal. You can schedule an entity for removal by calling the remove method on the EntityManager interface.
