package VendingMachine;

/**
 * Created by Syril on 18-07-2016.
 */
public class EmptyState implements VendingState {

    VendingMachine vendingMachine;

    public EmptyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amount) {
        System.out.println("Cannot process the request");
    }

    @Override
    public void dispense(int amount) {
        System.out.println("Invalid request");
    }
}
