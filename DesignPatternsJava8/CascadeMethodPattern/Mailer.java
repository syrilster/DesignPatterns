package CascadeMethodPattern;

import java.util.function.Consumer;

/**
 * Created by Syril on 06-05-2018.
 */
public class Mailer {
    public static void print(String message) {
        System.out.println(message);
    }

    public Mailer from(String address) {
        print("from");
        return this;
    }

    public Mailer to(String address) {
        print("to");
        return this;
    }

    public Mailer subject(String message) {
        print("subject");
        return this;
    }

    public Mailer body(String message) {
        print("body");
        return this;
    }

    public static void send(Consumer<Mailer> block) {
        /*Now think of this Mailer as a costly resource and the author has decided not to give a reference back to the caller.
        instead the object is going to die here and this brings in fluency as there is no burden on the caller. (Separation of concerns as well)*/
        Mailer mailer = new Mailer();
        block.accept(mailer);
        System.out.println("Sending...");
    }
}
