package VendingMachine;

/**
 * Created by syrils on 7/19/16.
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
        } else if (vendingMachine.getDispenseCommand()[slot] instanceof NoCommand) {
            System.out.println("Item Does not exist.. Please Try Again !!");
        } else if (vendingMachine.getAmountEntered() >= itemPrice) {
            vendingMachine.getDispenseCommand()[slot].execute();
            vendingMachine.setVendingState(vendingMachine.getDispensingState());
            if ((itemPrice - vendingMachine.getAmountEntered()) > 0)
                System.out.println("Tendering Change : " + (itemPrice - vendingMachine.getAmountEntered()));
            vendingMachine.setVendingState(vendingMachine.getNoCoinInsertedState());
        } else {
            System.out.println("More Credits Required. Please insert currency value " + (itemPrice - vendingMachine.getAmountEntered()));
        }
    }
}
