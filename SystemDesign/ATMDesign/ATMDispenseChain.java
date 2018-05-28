package ATMDesign;

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
        CurrencyDispense rupee500Dispenser = new Rupee500Dispenser();
        CurrencyDispense rupee100Dispenser = new Rupee100Dispenser();

        currencyChain.setNextChain(rupee500Dispenser);
        rupee500Dispenser.setNextChain(rupee100Dispenser);
    }

    public static void main(String[] args) {
        ATMDispenseChain atmDispenser = new ATMDispenseChain();
        while (true) {
            int amount;
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
