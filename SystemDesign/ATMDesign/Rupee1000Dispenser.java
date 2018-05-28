package ATMDesign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Syril on 17-05-2016.
 */
public class Rupee1000Dispenser implements CurrencyDispense {
    private Logger logger = LoggerFactory.getLogger(Rupee1000Dispenser.class);
    private CurrencyDispense chain;

    public void setNextChain(CurrencyDispense nextChain) {
        this.chain = nextChain;
    }


    public void dispense(Currency currency) {
        if (currency.getAmount() >= 1000) {
            int number = currency.getAmount() / 1000;
            int remainder = currency.getAmount() % 1000;
            logger.info("Dispensing " + number + " 1000 INR note");
            if (remainder != 0)
                this.chain.dispense(new Currency(remainder));
        } else {
            this.chain.dispense(currency);
        }

    }
}
