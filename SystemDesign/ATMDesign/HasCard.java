package ATMDesign;

/**
 * Created by Syril on 17-05-2016.
 */
public class HasCard implements ATMState {

    ATMMachine atmMachine;

    public HasCard(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }


    public void insertCard() {
        System.out.println("Cant insert more than one Card");
    }


    public void ejectCard() {
        System.out.println("Card Ejected");
        atmMachine.setAtmState(atmMachine.getNoCard());
    }

    public void insertPin(int pinEntered) {
        if (pinEntered == 1234) {
            System.out.println("Correct Pin");
            atmMachine.setCorrectPinEntered(true);
            atmMachine.setAtmState(atmMachine.getHasCorrectPin());
        } else {
            System.out.println("Wrong Pin");
            atmMachine.setAtmState(atmMachine.getNoCard());
        }
    }


    public void requestCash(int amount) {
        System.out.println("Enter Pin First");
    }
}
