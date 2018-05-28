package ATMDesign;

/**
 * Created by Syril on 17-05-2016.
 */
public class HasPin implements ATMState {

    ATMMachine atmMachine;

    public HasPin(ATMMachine atmMachine) {
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
        System.out.println("Pin Already Entered");
    }


    public void requestCash(int amount) {
        ATMDispenseChain atmDispenser = new ATMDispenseChain();

        if (amount > atmMachine.getCashInMachine()) {
            System.out.println("Amount cant be processed !!");
            System.out.println("Card Ejected");
            atmMachine.setAtmState(atmMachine.getNoCard());
        } else {
            atmDispenser.getCurrencyChain().dispense(new Currency(amount));

            atmMachine.setCashInMachine(atmMachine.getCashInMachine() - amount);
            System.out.println("Card Ejected");
            atmMachine.setAtmState(atmMachine.getNoCard());

            if (atmMachine.getCashInMachine() <= 0) {
                atmMachine.setAtmState(atmMachine.getNoCashState());
            }
        }
    }
}
