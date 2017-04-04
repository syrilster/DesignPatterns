package StrategyPattern;

/**
 * Created by syrils on 3/28/16.
 */
public class CreditCardStrategy implements PaymentStrategy {

    private int cardNumber;
    private String name;
    private int cvv;
    private int pin;

    CreditCardStrategy(int cardNumber, String name, int cvv, int pin) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.cvv = cvv;
        this.pin = pin;
    }

    @Override
    public void pay(double amount) {
        System.out.println(amount + " Made payment by using Credit Card");
    }
}
