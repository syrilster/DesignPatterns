package ATMDesign;

/**
 * Created by Syril on 17-05-2016.
 */
public class NoCard implements ATMState {

    ATMMachine atmMachine;

    public NoCard(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public void insertCard() {
        System.out.println("Card inserted");
        atmMachine.setAtmState(atmMachine.getHasCard());
    }

    @Override
    public void ejectCard() {
        System.out.println("Card not inserted");
    }

    @Override
    public void insertPin(int pinEntered) {
        System.out.println("Please insert the card first");
    }

    @Override
    public void requestCash(int amount) {
        System.out.println("Please insert the card first");
    }
}
