package SystemDesign.ATMDesign;

/**
 * Created by Syril on 18-05-2016.
 */
public class ATMProxy implements GetATMData {
    @Override
    public ATMState getATMState() {
        ATMMachine atmMachine = new ATMMachine();
        return atmMachine.getATMState();
    }

    @Override
    public int getCashInATMMachine() {
        ATMMachine atmMachine = new ATMMachine();
        return atmMachine.getCashInATMMachine();
    }
}
