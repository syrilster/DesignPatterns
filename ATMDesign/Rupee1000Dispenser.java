package DesignPatterns.ATMDesign;

/**
 * Created by Syril on 17-05-2016.
 */
public class Rupee1000Dispenser implements CurrencyDispense {
    private CurrencyDispense chain;

    @Override
    public void setNextChain(CurrencyDispense nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void dispense(Currency currency) {
        if (currency.getAmount() >= 1000) {
            int number = currency.getAmount() / 1000;
            int remainder = currency.getAmount() % 1000;
            System.out.println("Dispensing " + number + " 1000 INR note");
            if (remainder != 0)
                this.chain.dispense(new Currency(remainder));
        } else {
            this.chain.dispense(currency);
        }

    }
}
