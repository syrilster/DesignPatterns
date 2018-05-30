package VendingMachine;

/**
 * Created by Syril on 18-07-2016.
 */
public class EmptyState implements VendingState {

    VendingMachine vendingMachine;

    public EmptyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }


    public void insertCoin(int amount) {
        System.out.println("Cannot process the request");
    }


    public void dispense(int amount) {
        System.out.println("Invalid request");
    }
}
