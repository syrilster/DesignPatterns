package ATMDesign;

/**
 * Created by Syril on 18-05-2016.
 */
public class ATMProxy implements GetATMData {

    public ATMState getATMState() {
        ATMMachine atmMachine = new ATMMachine();
        return atmMachine.getATMState();
    }


    public int getCashInATMMachine() {
        ATMMachine atmMachine = new ATMMachine();
        return atmMachine.getCashInATMMachine();
    }
}
