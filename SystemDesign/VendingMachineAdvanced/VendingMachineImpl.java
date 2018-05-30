package VendingMachineAdvanced;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineImpl implements VendingMachine {
    private Inventory<Coin> cashInventory = new Inventory<Coin>();
    private Inventory<Item> itemInventory = new Inventory<Item>();
    private Item currentItem;
    private long currentBalance;

    public VendingMachineImpl() {
        init();
    }

    private void init() {
        for (Coin coin : Coin.values()) {
            cashInventory.put(coin, 5);
        }

        for (Item item : Item.values()) {
            itemInventory.put(item, 5);
        }
    }

    public long selectItemAndGetPrice(Item item) {
        if (itemInventory.hasItem(item)) {
            currentItem = item;
            return currentItem.getPrice();
        }
        throw new SoldOutException("Item is no longer available");
    }

    public void insertCoin(Coin coin) {
        currentBalance = currentBalance + coin.getDenomination();
        cashInventory.add(coin);
    }

    public List<Coin> getRefund() {
        return null;
    }

    public Bucket<Item, List<Coin>> getItemAndChange() {
        if (hasSufficientFunds()) {
            if (inventoryHasSufficientChange()) {
                dispenseItem();
                List<Coin> coinList = returnChange();
            }
        }
        return null;
    }

    private boolean inventoryHasSufficientChange() {
        if (false) {
            throw new NotSufficientChangeException("Sufficient change not available in the inventory");
        }

        return true;
    }

    private List<Coin> returnChange() {
        List<Coin> changes = new ArrayList<>();
        long amount = currentItem.getPrice() - currentBalance;
        if (amount > 0) {
            long balance = amount;
            while (balance > 0) {
                if (balance >= Coin.QUARTER.getDenomination() && cashInventory.hasItem(Coin.QUARTER)) {
                    changes.add(Coin.QUARTER);
                    balance = balance - Coin.QUARTER.getDenomination();
                } else if (balance >= Coin.NICKLE.getDenomination() && cashInventory.hasItem(Coin.NICKLE)) {
                    changes.add(Coin.NICKLE);
                    balance = balance - Coin.NICKLE.getDenomination();
                } else if (balance >= Coin.PENNY.getDenomination() && cashInventory.hasItem(Coin.PENNY)) {
                    changes.add(Coin.PENNY);
                    balance = balance - Coin.PENNY.getDenomination();
                } else if (balance >= Coin.CENT.getDenomination() && cashInventory.hasItem(Coin.CENT)) {
                    changes.add(Coin.CENT);
                    balance = balance - Coin.CENT.getDenomination();
                } else {
                    throw new NotSufficientChangeException("Change not available in inventory. Please try other product");
                }
            }
        }
        return changes;
    }

    private void dispenseItem() {
        itemInventory.deduct(currentItem);
        currentBalance = currentBalance - currentItem.getPrice();
    }

    private boolean hasSufficientFunds() {
        if (currentBalance <= currentItem.getPrice())
            throw new NotFullPaidException("Funds not available", (currentItem.getPrice() - currentBalance));
        return true;
    }

    public void reset() {

    }

    public long getCurrentBalance() {
        return currentBalance;
    }
}
