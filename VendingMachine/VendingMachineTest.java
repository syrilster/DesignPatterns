package designPattern.VendingMachine;

/**
 * Created by syrils on 7/19/16.
 */
public class VendingMachineTest {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        Chips chips = new Chips();
        ChipsVendingCommand chipsVendingCommand = new ChipsVendingCommand(chips, vendingMachine);
        vendingMachine.setCommand(1, chipsVendingCommand);
        vendingMachine.insertCoin(20);
        vendingMachine.dispense(1);

        //To illustrate Item out of stock
        vendingMachine.insertCoin(20);
        vendingMachine.dispense(1);
        
        //To illustrate Item not available
        vendingMachine.insertCoin(20);
        vendingMachine.dispense(2);
    }
}
