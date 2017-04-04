package ChainOfResponsibility;

/**
 * Created by syrils on 4/1/16.
 */
public class ConsoleLogger extends AbstractLogger {

    ConsoleLogger(int level) {
        this.level = level;
    }
    
    @Override
    protected void write(String message) {
        System.out.println("Standard Console :: Logger " + message);
    }
}
