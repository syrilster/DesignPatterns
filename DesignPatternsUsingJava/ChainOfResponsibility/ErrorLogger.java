package DesignPatternsUsingJava.ChainOfResponsibility;

/**
 * Created by syrils on 4/1/16.
 */
public class ErrorLogger extends AbstractLogger {

    ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error :: Logger "+ message);
    }
}
