package VendingMachine;

/**
 * Created by syrils on 7/19/16.
 */
public class DispensingState implements VendingState {
    VendingMachine vendingMachine;

    public DispensingState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }


    public void insertCoin(int amount) {
        System.out.println("Please wait.. Machine is dispensing !!");
    }


    public void dispense(int amount) {
        vendingMachine.setVendingState(vendingMachine.getNoCoinInsertedState());
    }
}
