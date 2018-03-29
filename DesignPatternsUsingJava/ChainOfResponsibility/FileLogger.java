package DesignPatternsUsingJava.ChainOfResponsibility;

/**
 * Created by syrils on 4/1/16.
 */
public class FileLogger extends AbstractLogger {

    FileLogger(int level) {
        this.level = level;
    }
    
    @Override
    protected void write(String message) {
        System.out.println("File :: Logger "+ message);    
    }
}
