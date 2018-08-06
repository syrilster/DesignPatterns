package ExecuteAroundMethodPattern;

import java.util.function.Consumer;

/**
 * Created by Syril on 06-05-2018.
 */
public class Resource implements AutoCloseable {
    private Resource() {
        System.out.println("Created..");
    }

    public Resource op1() {
        System.out.println("Operation One");
        return this;
    }

    public Resource op2() {
        System.out.println("Operation Two");
        return this;
    }

    public void close() {
        System.out.println("Cleaning up in the close method..");
    }

    public static void use(Consumer<Resource> block) {
        //This is the right way to handle exceptions and resource closure when exposed as API.
        try (Resource resource = new Resource()) {
            block.accept(resource);
        }
    }
}
