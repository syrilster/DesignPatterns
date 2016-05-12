package designPattern.StratergyPattern;

/**
 * Created by syrils on 3/28/16.
 */
public class PaypalStrategy implements PaymentStrategy {

    private String email;
    private String password;

    PaypalStrategy(String email,
                   String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void pay(double amount) {
        System.out.println(amount + " Made payment by using Pay pal account");
    }
}
