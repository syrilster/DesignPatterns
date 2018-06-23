package VendingMachineAdvanced;

public class Container<E1, E2> {
    private E1 item;
    private E2 coin;

    public Container(E1 item, E2 coin) {
        this.item = item;
        this.coin = coin;
    }

    public E1 getItem() {
        return item;
    }

    public E2 getCoin() {
        return coin;
    }
}
