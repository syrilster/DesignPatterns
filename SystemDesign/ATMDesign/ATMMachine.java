package SystemDesign.ATMDesign;

/**
 * Created by Syril on 17-05-2016.
 */
public class ATMMachine implements GetATMData{

    ATMState hasCard;
    ATMState noCard;
    ATMState hasCorrectPin;
    ATMState atmOutOfMoney;

    private ATMState atmState;
    int cashInMachine = 2000;
    boolean correctPinEntered = false;

    ATMMachine() {
        hasCard = new HasCard(this);
        noCard = new NoCard(this);
        hasCorrectPin = new HasPin(this);
        atmOutOfMoney = new NoCash(this);

        atmState = noCard;
        if (cashInMachine < 0) {
            atmState = atmOutOfMoney;
        }
    }

    public void setAtmState(ATMState atmState) {
        this.atmState = atmState;
    }

    public void setCashInMachine(int cashInMachine) {
        this.cashInMachine = cashInMachine;
    }

    public void insertCard() {
        atmState.insertCard();
    }

    public void ejectCard() {
        atmState.ejectCard();
    }

    public void insertPin(int pinEntered) {
        atmState.insertPin(pinEntered);
    }

    public void requestCash(int cashToWithDraw) {
        atmState.requestCash(cashToWithDraw);
    }

    public ATMState getHasCard() {
        return hasCard;
    }

    public ATMState getNoCard() {
        return noCard;
    }

    public ATMState getHasCorrectPin() {
        return hasCorrectPin;
    }

    public ATMState getNoCashState() {
        return atmOutOfMoney;
    }

    public void setCorrectPinEntered(boolean correctPinEntered) {
        this.correctPinEntered = correctPinEntered;
    }

    public int getCashInMachine() {
        return cashInMachine;
    }

    @Override
    public ATMState getATMState() {
        return atmState;
    }

    @Override
    public int getCashInATMMachine() {
        return cashInMachine;
    }
}
