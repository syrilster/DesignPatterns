package VendingMachineAdvanced;

import java.util.List;

public interface VendingMachine {
    long selectItemAndGetPrice(Item item);

    void insertCoin(Coin coin);

    List<Coin> getRefund();

    Bucket<Item, List<Coin>> getItemAndChange();

    void reset();
}
