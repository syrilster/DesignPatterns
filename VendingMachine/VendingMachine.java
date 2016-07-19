package DesignPatterns.VendingMachine;

/**
 * Created by Syril on 18-07-2016.
 */
public class VendingMachine {
    VendingState coinInsertedState;
    VendingState emptyState;
    VendingState noCoinInsertedState;
    VendingState dispensingState;

    private VendingState vendingState;
    private int chipsCapacity;
    private int biscuitCapacity;
    private int colaCapacity;
    private int amountEntered;

    Command[] dispenseCommand;

    public VendingMachine() {
        this.coinInsertedState = new CoinInsertedState(this);
        this.emptyState = new EmptyState(this);
        this.noCoinInsertedState = new NoCoinInsertedState(this);
        this.dispensingState = new DispensingState(this);

        this.vendingState = noCoinInsertedState;
        this.setChipsCapacity(10);
        this.setColaCapacity(10);
        this.setBiscuitCapacity(10);

        /*Command Pattern */
        dispenseCommand = new Command[7];
        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            dispenseCommand[i] = noCommand;
        }
    }

    public void setCommand(int slot, Command dispenseCommand) {
        this.dispenseCommand[slot] = dispenseCommand;
    }

    public void insertCoin(int amount) {
        vendingState.insertCoin(amount);
    }

    public void dispense(int slot) {
        vendingState.dispense(slot);
    }

    //DAO stub methods
    public int getItemCapacity(int itemCode) {
        switch (itemCode) {
            case 1:
                return chipsCapacity;
            case 2:
                return biscuitCapacity;
            case 3:
                return colaCapacity;
        }
        return 0;
    }

    public int getItemCost(int itemCode) {
        switch (itemCode) {
            case 1:
                return 20;
            case 2:
                return 10;
            case 3:
                return 25;
        }
        return 0;
    }

    public Command[] getDispenseCommand() {
        return dispenseCommand;
    }

    public void setDispenseCommand(Command[] dispenseCommand) {
        this.dispenseCommand = dispenseCommand;
    }

    public VendingState getVendingState() {
        return vendingState;
    }

    public void setVendingState(VendingState vendingState) {
        this.vendingState = vendingState;
    }

    public VendingState getCoinInsertedState() {
        return coinInsertedState;
    }

    public void setCoinInsertedState(VendingState coinInsertedState) {
        this.coinInsertedState = coinInsertedState;
    }

    public VendingState getEmptyState() {
        return emptyState;
    }

    public void setEmptyState(VendingState emptyState) {
        this.emptyState = emptyState;
    }

    public VendingState getNoCoinInsertedState() {
        return noCoinInsertedState;
    }

    public void setNoCoinInsertedState(VendingState noCoinInsertedState) {
        this.noCoinInsertedState = noCoinInsertedState;
    }

    public VendingState getDispensingState() {
        return dispensingState;
    }

    public void setDispensingState(VendingState dispensingState) {
        this.dispensingState = dispensingState;
    }

    public int getChipsCapacity() {
        return chipsCapacity;
    }

    public void setChipsCapacity(int chipsCapacity) {
        this.chipsCapacity = chipsCapacity;
    }

    public int getBiscuitCapacity() {
        return biscuitCapacity;
    }

    public void setBiscuitCapacity(int biscuitCapacity) {
        this.biscuitCapacity = biscuitCapacity;
    }

    public int getColaCapacity() {
        return colaCapacity;
    }

    public void setColaCapacity(int colaCapacity) {
        this.colaCapacity = colaCapacity;
    }

    public int getAmountEntered() {
        return amountEntered;
    }

    public void setAmountEntered(int amountEntered) {
        this.amountEntered = amountEntered;
    }
}
