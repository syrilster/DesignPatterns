package DesignPatterns.VendingMachine;

/**
 * Created by Syril on 18-07-2016.
 */
public class VendingMachineTest {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        Chips chips = new Chips();
        ChipsVendingCommand chipsVendingCommand = new ChipsVendingCommand(chips);
        vendingMachine.setCommand(1, chipsVendingCommand);
        vendingMachine.insertCoin(20);
        vendingMachine.dispense(1);

        vendingMachine.insertCoin(20);
        vendingMachine.dispense(2);
    }
}
