package ATMDesign;

/**
 * Created by Syril on 18-05-2016.
 */
public class ATMProxy implements GetATMData {
    ATMMachine atmMachine;

    public ATMProxy(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    public ATMState getATMState() {
        return atmMachine.getATMState();
    }


    public int getCashInATMMachine() {
        return atmMachine.getCashInATMMachine();
    }
}
