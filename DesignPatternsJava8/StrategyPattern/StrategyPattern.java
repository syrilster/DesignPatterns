package DesignPatternsJava8.StrategyPattern;

import java.util.List;
import java.util.function.Predicate;

import static java.util.Arrays.asList;

/**
 * Created by Syril on 05-05-2018.
 */
public class StrategyPattern {
    // Java 8 has lightweight strategy pattern unlike the heavy weight Java 7 way as there is a interface hierarchy.
    // i.e. Use of library of classes over a set of function usage like the Util.isEven.
    public static void main(String[] args) {
        List<Integer> numbers = asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(totalValues(numbers, e -> true));
        //System.out.println(totalEvenValues(numbers));
        //System.out.println(totalOddValues(numbers));
        System.out.println(totalValues(numbers, Util::isEven));
        System.out.println(totalValues(numbers, Util::isOdd));
    }

    private static int totalValues(List<Integer> numbers, Predicate<Integer> predicateCondition) {
        /*int result = 0;
        for (int value : numbers) {
            if (predicateCondition.test(value))
                result += value;
        }
        return result;*/

        return numbers.stream()
                .filter(predicateCondition)
                .reduce(0, Integer::sum);
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