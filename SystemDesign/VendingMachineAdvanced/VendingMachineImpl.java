package VendingMachineAdvanced;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineImpl implements VendingMachine {
    private Inventory<Coin> cashInventory = new Inventory<>();
    private Inventory<Item> itemInventory = new Inventory<>();
    private Item currentItem;
    private long currentBalance;
    private long totalSales;

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
        List<Coin> refund = getChange();
        updateCashInventory(refund);
        currentBalance = 0;
        currentItem = null;
        return refund;
    }

    public Bucket<Item, List<Coin>> getItemAndChange() {
        Item item = null;
        List<Coin> change = new ArrayList<>();
        if (hasSufficientFunds()) {
            if (inventoryHasSufficientChange()) {
                item = dispenseItem();
                totalSales = totalSales + currentItem.getPrice();
                change = getChange();
                updateCashInventory(change);
                currentBalance = 0;
                currentItem = null;
            }
        }
        return new Bucket<>(item, change);
    }

    private void updateCashInventory(List<Coin> change) {
        for (Coin coin : change) {
            cashInventory.deduct(coin);
        }
    }

    private boolean inventoryHasSufficientChange() {
        return !getChange().contains(Coin.NO_COIN);
    }

    private List<Coin> getChange() {
        List<Coin> changes = new ArrayList<>();
        long amount = currentBalance - currentItem.getPrice();
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
                    //throw new NotSufficientChangeException("Change not available in inventory. Please try other product");
                    changes.add(Coin.NO_COIN);
                }
            }
        }
        return changes;
    }

    private Item dispenseItem() {
        itemInventory.deduct(currentItem);
        return currentItem;
    }

    private boolean hasSufficientFunds() {
        if (currentBalance <= currentItem.getPrice())
            throw new NotFullPaidException("Funds not available", (currentItem.getPrice() - currentBalance));
        return true;
    }

    public void reset() {

    }

    public void printStats() {
        System.out.println("Total Sales : " + totalSales);
        System.out.println("Current Item Inventory : " + itemInventory.getAllItems());
        System.out.println("Current Cash Inventory : " + cashInventory.getAllItems());
    }

    public long getTotalSales() {
        return totalSales;
    }

    public long getCurrentBalance() {
        return currentBalance;
    }
}
