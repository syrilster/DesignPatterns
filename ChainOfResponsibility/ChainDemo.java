package designPattern.ChainOfResponsibility;

/**
 * Created by syrils on 4/1/16.
 */
public class ChainDemo {

    static AbstractLogger getChainOfLoggers() {
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger logger = getChainOfLoggers();

        logger.logMessage(AbstractLogger.DEBUG, "This is a Debug log");
        logger.logMessage(AbstractLogger.ERROR, "This is a Serious Error");
    }
}
