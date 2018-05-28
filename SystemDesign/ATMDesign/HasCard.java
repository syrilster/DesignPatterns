package ATMDesign;

/**
 * Created by Syril on 17-05-2016.
 */
public class HasCard implements ATMState {

    ATMMachine atmMachine;

    public HasCard(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }


    public String insertCard() {
        return "Cant insert more than one Card";
    }


    public String ejectCard() {
        atmMachine.setAtmState(atmMachine.getNoCard());
        return "Card Ejected";
    }

    public String insertPin(int pinEntered) {
        if (pinEntered == 1234) {
            atmMachine.setCorrectPinEntered(true);
            atmMachine.setAtmState(atmMachine.getHasCorrectPin());
            return "User has entered the correct pin";
        } else {
            atmMachine.setAtmState(atmMachine.getNoCard());
            return "Wrong Pin entered by the User";
        }
    }


    public String requestCash(int amount) {
        return "Enter Pin First";
    }
}
