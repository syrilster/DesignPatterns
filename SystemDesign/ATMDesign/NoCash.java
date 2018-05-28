package ATMDesign;

/**
 * Created by Syril on 17-05-2016.
 */
public class NoCash implements ATMState {

    ATMMachine atmMachine;

    public NoCash(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }


    public String insertCard() {
        return "ATM is out of money";
    }


    public String ejectCard() {
        return "ATM is out of money";
    }


    public String insertPin(int pinEntered) {
        return "ATM is out of money";
    }


    public String requestCash(int amount) {
        return "ATM is out of money";
    }
}
