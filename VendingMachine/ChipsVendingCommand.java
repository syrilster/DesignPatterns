package designPattern.VendingMachine;

/**
 * Created by syrils on 7/19/16.
 */
public class ChipsVendingCommand implements Command{
    Chips chips;
    VendingMachine vendingMachine;

    ChipsVendingCommand(Chips chips, VendingMachine vendingMachine) {
        this.chips = chips;
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void execute() {
        chips.dispense();
        int capacity = this.vendingMachine.getChipsCapacity();
        this.vendingMachine.setChipsCapacity(--capacity);
    }
}
