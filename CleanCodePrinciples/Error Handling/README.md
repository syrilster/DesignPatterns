## Use Exceptions Rather than Return Codes
* Back in the distant past some languages did'nt have exceptions so they choose to have error flag or returned an error code.
    ```
    if(record.getStatus() != DEVICE_SUSPENDED){
        //Do something here
    } else {
        logger.log("Device Suspended.");
    }
    ```
* Above code clutter the caller. The caller must check for errors immediately after the call and its easy to forget.
* Throwing exceptions is much more easier and cleaner.

## Write your Try-Catch-Finally Statement First
* In a way, try blocks are like transactions. Your catch has to leave your program in a consistent state, no matter what happens in the try.
* It is a good practice to start with a try-catch-finally statement when you are writing code that could throw exceptions.
* This helps to define what the user of that code should expect, no matter what goes wrong with the code that is executed in the try.

```
@Test(expected = StorageException.class)
public void retrieveSectionShouldThrowOnInvalidFileName() {
    sectionStore.retrieveSection("invalid - file");
}
```

* Try to write tests like the above thats force exceptions, and then add behavior to your handler to satisfy your tests. This helps in TDD to build the logic we need.

## Use Unchecked Exceptions
* The debate is over. For years Java programmers have debated over the benefits and liabilities of checked exceptions.
* It is clear now that checked exceptions are'nt necessary for the production of robust software. C# does not have checked exceptions. Neither do C++, python and ruby.
* Checked exceptions is a violation of Open/Closed principle. Example: If you throw a checked exception from a method in your code and the catch is 3 levels above, you must declare that exception in the signature of each method between you and the catch.
* This means that a change at a low level of the software can force signature changes on many higher levels. The changed modules must be rebuilt and redeployed, even though nothing they care about changed.
* Encapsulation is broken because all functions in the path of a throw must know about details of that low-level exception.
* Checked exceptions can sometimes be useful if you are writing a critical library: you must catch them. But in general application development the dependency costs outweigh the benefits.

## Provide Context with Exceptions
* Each exception that you throw should provide enough context to determine the source and excat location of an error.
* Having stack trace is not just enough. Stack trace can't tell you the intent of the operation that failed.
* Create informative error messages and pass them along with your exceptions. Mention the operation that failed and the type of failure.

## Define the normal Flow
* Sometimes it is better to handle exceptions differently like the below code snippet:

    ```
    try {
        MealExpense expenses = expenseReportDAO.getMeals(employee.getID());
        mealTotal += expense.getTotal();
    } catch(MealExpensesNotFound e) {
        mTotal += getMealPerDiem();
    }        
    ```
* In this business, if meals are expensed, they become part of the total. If they aren't, the employee gets a meal per diem amount for that day. Handling it like below is better:

    ```
    MealExpense expenses = expenseReportDAO.getMeals(employee.getID());
    mealTotal += expense.getTotal();
    ```
* We can change the expenseReportDAO so that it always returns a MealExpense Object. If there are no meal expenses, it returns a MealExpense object that returns the per diem as its total.

    ```
    public class PerDiemMealExpenses implements MealExpenses {
        public int getTotal() {
            // return the per diem total
        }
    }
    ```
* This is called the **SPECIAL CASE PATTERN**. You create a class or configure an object so that it handles a special case for you. This helps as the client code need not handle the exceptional behavior. The behavior is encapsulated in the special case object.   

## Don't return Null

```
public void registerItem(Item item) {
    if(item != null) {
        ItemRegistery registery = peristentStore.getItemRegistery();
        if(registery != null) {
            Item existing = registery.getItem(item.getId());
            if (existing.getBillingPeriod.hasRetailOwner()) {
                existing.register(item);
            }
        }
    }
}
```

* When we return null, we are essentially creating work for ourselves and foisting problems upon callers. All it takes is one missing null check to send an application spinning out of control.
* What would have happened if peristentStore is null ? We would have had a NullPointerException at runtime.
* Its easy to say that the problem with the above code is missing a null check, but in actuality, the problem is that it has too many.
* If you are tempted to return null from a method, consider throwing an exception or returning a SPECIAL CASE object instead.
* Example: List<Employee> employees = getEmployees(); Here getEmployees() can return a null. We can use Java Collections.emptyList() instead as it returns a predefined immutable list.
    
## Don't pass null
* Returning null from methods is bad, but passing null into methods is worse.
* Avoid passing null in your code whenever possible.

    ```
    public class MetricsCalculator {
        public double xProjection(Point p1, Point p2) {
            return (p2.x - p1.x) * 1.5;
        }
    }
    ```
* What happens when someone passes null as an argument ? calculator.xProjection(null, new Point(12, 13));
* We get a NullPointerException and to fix it we can wrap it in a new exception type and throw it. Like say an InvalidArgumentException. This is a good documentation, but it does not solve the problem. If someone passes null, we'll still have a runtime error.
* A rational approach is to forbid passing null by default.
        
