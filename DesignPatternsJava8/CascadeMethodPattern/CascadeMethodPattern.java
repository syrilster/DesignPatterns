package CascadeMethodPattern;

/**
 * Created by Syril on 06-05-2018.
 */
public class CascadeMethodPattern {
    public static void main(String[] args) {
        /*Mailer mailer = new Mailer();
        mailer.from("java.developer.com");
        mailer.to("syril.sadasivan@gmail.com");
        mailer.subject("Your code Rocks !!");
        mailer.body(".. Here is why ..");
        mailer.send();*/

        //Cascade Method pattern form aka builder pattern (Noise has been removed)
        /*new Mailer().from("java.developer.com")
                .to("syril.sadasivan@gmail.com")
                .subject("Your code Rocks !!")
                .body(".. Here is why ..")
                .send();*/

        //Now that send has been made s static method and send method is accepting a Consumer
        //Sending a Lambda to send method which then gives back some values which is used by the consumer
        Mailer.send(mailer -> mailer.from("java.developer.com")
                .to("syril.sadasivan@gmail.com")
                .subject("Your code Rocks !!")
                .body(".. Here is why .."));

        /*Also in this line we don't even have a mailer object to be used/reused as the mailer was
        in the send method as a lambda and it did not return anything (In this example)*/
    }
}
