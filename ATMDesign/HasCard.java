package ATMDesign;

/**
 * Created by Syril on 17-05-2016.
 */
public class HasCard implements ATMState {

    ATMMachine atmMachine;

    public HasCard(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public void insertCard() {
        System.out.println("Cant insert more than one Card");
    }

    @Override
    public void ejectCard() {
        System.out.println("Card Ejected");
        atmMachine.setAtmState(atmMachine.getNoCard());
    }

    @Override
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

    @Override
    public void requestCash(int amount) {
        System.out.println("Enter Pin First");
    }
}
