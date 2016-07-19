package DesignPatterns.VendingMachine;

/**
 * Created by Syril on 18-07-2016.
 */
public class DispensingState implements VendingState {
    VendingMachine vendingMachine;

    public DispensingState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amount) {
        System.out.println("Please while.. Machine is dispensing !!");
    }

    @Override
    public void dispense(int amount) {
        vendingMachine.setVendingState(vendingMachine.getNoCoinInsertedState());
    }
}
