package DesignPatterns.ATMDesign;

/**
 * Created by Syril on 17-05-2016.
 */
public interface ATMState {
    void insertCard();
    void ejectCard();
    void insertPin(int pinEntered);
    void requestCash(int amount);
}
