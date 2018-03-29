package SystemDesign.VendingMachine;

/**
 * Created by Syril on 18-07-2016.
 */
public class NoCoinInsertedState implements VendingState {

    VendingMachine vendingMachine;

    public NoCoinInsertedState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amount) {
        vendingMachine.setAmountEntered(amount);
        vendingMachine.setVendingState(vendingMachine.getCoinInsertedState());
    }

    @Override
    public void dispense(int amount) {
        System.out.println("No coin inserted ..");
    }
}
