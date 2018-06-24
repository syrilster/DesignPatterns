package VendingMachineAdvanced;

public enum Item {
    COKE("Coke", 25), CHIPS("Chips", 12), SODA("Soda", 15), COOKIE("Cookie", 35), PEPSI("Pepsi", 1);

    private String name;
    private long price;

    Item(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public long getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
