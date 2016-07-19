package DesignPatterns.VendingMachine;

/**
 * Created by Syril on 19-07-2016.
 */
public class ChipsVendingCommand implements Command {
    Chips chips;

    ChipsVendingCommand(Chips chips) {
        this.chips = chips;
    }

    @Override
    public void execute() {
        chips.dispense();
    }
}
