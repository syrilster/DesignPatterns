package DecoratorPattern;

import java.util.function.Function;

public class DecoratorSample {
    public static void doWork(int value, Function<Integer, Integer> operation) {
        System.out.println(operation.apply(value));
    }

    public static void main(String[] args) {
        Function<Integer, Integer> increment = e -> e + 1;
        Function<Integer, Integer> doubleIt = e -> e * 2;

        //Increment a given value
        doWork(5, increment);
        //Double a given value
        doWork(5, doubleIt);
        //Increment and then double
        doWork(5, increment.andThen(doubleIt));
    }
}
