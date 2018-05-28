package ATMDesign;

/**
 * Created by Syril on 17-05-2016.
 */
public interface ATMState {
    String insertCard();
    String ejectCard();
    String insertPin(int pinEntered);
    String requestCash(int amount);
}
