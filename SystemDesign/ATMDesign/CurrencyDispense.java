package ATMDesign;

/**
 * Created by Syril on 17-05-2016.
 */
public interface CurrencyDispense {
    void setNextChain(CurrencyDispense dispense);
    void dispense(Currency currency);
}
