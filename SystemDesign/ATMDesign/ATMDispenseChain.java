package SystemDesign.ATMDesign;

import java.util.Scanner;

/**
 * Created by Syril on 17-05-2016.
 */
public class ATMDispenseChain {
    private CurrencyDispense currencyChain;

    public CurrencyDispense getCurrencyChain() {
        return currencyChain;
    }

    public ATMDispenseChain() {
        this.currencyChain = new Rupee1000Dispenser();
        CurrencyDispense d2 = new Rupee500Dispenser();
        CurrencyDispense d3 = new Rupee100Dispenser();

        currencyChain.setNextChain(d2);
        d2.setNextChain(d3);
    }
    public static void main(String[] args) {
        ATMDispenseChain atmDispenser = new ATMDispenseChain();
        while (true) {
            int amount = 0;
            System.out.println("Enter amount to dispense");
            Scanner input = new Scanner(System.in);
            amount = input.nextInt();
            if (amount % 100 != 0) {
                System.out.println("Amount should be in multiple of 100s.");
                return;
            }
            // process the request
            atmDispenser.currencyChain.dispense(new Currency(amount));
        }

    }

}
