package ATMDesign;

import org.junit.*;

/**
 * Created by Syril on 17-05-2016.
 */
public class TestATMMachine {

    @Test
    public void basicTestMethod() {
        ATMMachine atmMachine = new ATMMachine();
        atmMachine.insertCard();
        atmMachine.insertPin(1234);
        atmMachine.requestCash(1500);

        GetATMData atmProxy = new ATMProxy();
        System.out.println("Current ATM State " + atmProxy.getATMState());
        System.out.println(atmProxy.getCashInATMMachine());
    }
}
