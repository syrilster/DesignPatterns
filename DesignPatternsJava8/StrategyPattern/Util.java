package DesignPatternsJava8.StrategyPattern;

/**
 * Created by Syril on 05-05-2018.
 */
public class Util {
    static boolean isEven(int number) {
        return number % 2 == 0;
    }

    static boolean isOdd(int number) {
        return number % 2 != 0;
    }
}
