package ATMDesign;

/**
 * Created by Syril on 17-05-2016.
 */
public class Rupee100Dispenser implements CurrencyDispense {
    private CurrencyDispense chain;


    public void setNextChain(CurrencyDispense nextChain) {
        this.chain = nextChain;
    }


    public void dispense(Currency currency) {
        if (currency.getAmount() >= 100) {
            int number = currency.getAmount() / 100;
            int remainder = currency.getAmount() % 100;
            System.out.println("Dispensing " + number + " 100 INR note");
            if (remainder != 0)
                this.chain.dispense(new Currency(remainder));
        } else {
            this.chain.dispense(currency);
        }

    }
}
