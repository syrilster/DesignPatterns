package DesignPatterns.VendingMachine;

/**
 * Created by Syril on 18-07-2016.
 */
public class CoinInsertedState implements VendingState {

    VendingMachine vendingMachine;

    public CoinInsertedState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amount) {
        System.out.println("Coin already inserted");
    }

    @Override
    public void dispense(int slot) {
        int itemCapacity = vendingMachine.getItemCapacity(slot);
        int itemPrice = vendingMachine.getItemCost(slot);
        if (itemCapacity == 0) {
            System.out.println("Item Out of Stock");
        } else if (vendingMachine.getAmountEntered() >= itemPrice) {
            vendingMachine.getDispenseCommand()[slot].execute();
            vendingMachine.setVendingState(vendingMachine.getDispensingState());
            System.out.println("Tendering Change : " + (itemPrice - vendingMachine.getAmountEntered()));
            vendingMachine.setVendingState(vendingMachine.getNoCoinInsertedState());
        } else {
            System.out.println("More Credits Required. Please insert currency value " + (itemPrice - vendingMachine.getAmountEntered()));
        }
    }
}
