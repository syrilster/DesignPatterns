package SystemDesign.ATMDesign;

/**
 * Created by Syril on 17-05-2016.
 */
public class NoCash implements ATMState {

    ATMMachine atmMachine;

    public NoCash(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public void insertCard() {
        System.out.println("ATM is out of money");
    }

    @Override
    public void ejectCard() {
        System.out.println("ATM is out of money");
    }

    @Override
    public void insertPin(int pinEntered) {
        System.out.println("ATM is out of money");
    }

    @Override
    public void requestCash(int amount) {
        System.out.println("ATM is out of money");
    }
}
