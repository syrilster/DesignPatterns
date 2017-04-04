package VendingMachine;

/**
 * Created by Syril on 18-07-2016.
 */
public interface VendingState {
    public void insertCoin(int amount);
    public void dispense(int slot);
}
