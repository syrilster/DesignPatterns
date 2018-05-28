package ATMDesign;

/**
 * Created by Syril on 17-05-2016.
 */
public class HasPin implements ATMState {

    ATMMachine atmMachine;

    public HasPin(ATMMachine atmMachine) {
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
        return "Pin Already Entered";
    }


    public String requestCash(int amount) {
        ATMDispenseChain atmDispenser = new ATMDispenseChain();

        if (amount > atmMachine.getCashInMachine()) {
            atmMachine.setAtmState(atmMachine.getNoCard());
            return "Amount cant be processed !!, Card Ejected";
        } else {
            atmDispenser.getCurrencyChain().dispense(new Currency(amount));

            atmMachine.setCashInMachine(atmMachine.getCashInMachine() - amount);
            atmMachine.setAtmState(atmMachine.getNoCard());

            if (atmMachine.getCashInMachine() <= 0) {
                atmMachine.setAtmState(atmMachine.getNoCashState());
            }
            return "Card Ejected";
        }
    }
}
