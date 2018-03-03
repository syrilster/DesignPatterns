## Object oriented design
Indications of Bad code:
* Rigid - Too many methods depend on the same function. So a change to one method causes inconsistency across other methods too. (i.e cannot make an isolated change)
* Fragile - One code change starts breaking other functionality. (i.e code starts to break in other modules which has no relationship to your change so its unpredictable).
* Coupling - Tight coupling between high level and low level modules. A good software will have well managed dependencies.

## What is OO?
Polymorphism: Ability to create one module calling an other yet have the compile time dependency against the flow of control instead of with the flow of control. This allows absolute control over the dependency structure which helps to avoid the above mentioned conditions of bad code.

OO is about managing dependencies by selectively inverting the key dependencies in an architecture to prevent bad code (Rigid, fragile, coupling)

