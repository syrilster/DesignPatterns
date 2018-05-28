package ATMDesign;

/**
 * Created by Syril on 17-05-2016.
 */
public class NoCard implements ATMState {

    ATMMachine atmMachine;

    public NoCard(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }


    public String insertCard() {
        atmMachine.setAtmState(atmMachine.getHasCard());
        return "Card inserted";
    }


    public String ejectCard() {
        return "Card not inserted";
    }


    public String insertPin(int pinEntered) {
        return "Please insert the card first";
    }


    public String requestCash(int amount) {
        return "Please insert the card first";
    }
}
