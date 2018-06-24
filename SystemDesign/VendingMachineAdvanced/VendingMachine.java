package VendingMachineAdvanced;

import java.util.List;

public interface VendingMachine {
    long selectItemAndGetPrice(Item item);

    void insertCoin(Coin coin);

    List<Coin> getRefund();

    Knapsack<Item, List<Coin>> getItemAndChange();

    void reset();

    void printStats();
}
