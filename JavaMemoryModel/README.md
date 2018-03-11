## Components in the Java Memory Model
* Heap - Objects and references, references to class which got loaded, static variables.
* Stack - Local variables, method parameters, method flow etc.
* Method Area - Part of heap area which has information of references to class which got loaded during class loading.
* Native memory - All other language specific code. Ex: Socket connections details.
* PC Register - Sequence of instructions which needs to be executed next. One PC register per thread. Tells the thread what to do next.
