package ATMDesign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Syril on 17-05-2016.
 */
public class Rupee500Dispenser implements CurrencyDispense {
    private Logger logger = LoggerFactory.getLogger(Rupee500Dispenser.class);
    private CurrencyDispense chain;


    public void setNextChain(CurrencyDispense nextChain) {
        this.chain = nextChain;
    }


    public void dispense(Currency currency) {
        if (currency.getAmount() >= 500) {
            int number = currency.getAmount() / 500;
            int remainder = currency.getAmount() % 500;
            logger.info("Dispensing " + number + " 500 INR note");
            if (remainder != 0)
                this.chain.dispense(new Currency(remainder));
        } else {
            this.chain.dispense(currency);
        }

    }
}
