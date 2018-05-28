package StrategyPattern;

/**
 * Created by syrils on 3/28/16.
 */
public class Item {

    private int itemId;
    private int itemCode;
    private double price;

    public Item(int itemId, int itemCode, double price) {
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.price = price;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
