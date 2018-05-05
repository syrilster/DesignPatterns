package DesignPatternsJava8;

import java.util.List;
import java.util.function.Predicate;

import static java.util.Arrays.asList;

/**
 * Created by Syril on 05-05-2018.
 */
public class StrategyPattern {
    public static void main(String[] args) {
        List<Integer> numbers = asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(totalValues(numbers, e -> true));
        //System.out.println(totalEvenValues(numbers));
        //System.out.println(totalOddValues(numbers));
        System.out.println(totalValues(numbers, e -> e % 2 == 0));
        System.out.println(totalValues(numbers, e -> e % 2 != 0));
    }

    private static int totalValues(List<Integer> numbers, Predicate<Integer> condition) {
        int result = 0;
        for (int value : numbers) {
            if (condition.test(value))
                result += value;
        }
        return result;
    }

    private static int totalEvenValues(List<Integer> numbers) {
        int result = 0;
        for (int value : numbers) {
            if (value % 2 == 0)
                result += value;
        }
        return result;
    }

    private static int totalOddValues(List<Integer> numbers) {
        int result = 0;
        for (int value : numbers) {
            if (value % 2 != 0)
                result += value;
        }
        return result;
    }
}
