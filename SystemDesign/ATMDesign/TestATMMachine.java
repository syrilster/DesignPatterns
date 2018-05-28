package ATMDesign;

import org.junit.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by Syril on 17-05-2016.
 */
public class TestATMMachine {

    @Test
    public void basicTestMethod() {
        ATMMachine atmMachine = new ATMMachine();
        GetATMData atmProxy = new ATMProxy(atmMachine);

        atmMachine.insertCard();
        assertEquals("User has entered the correct pin", atmMachine.insertPin(1234));
        atmMachine.requestCash(1500);

        assertEquals(500, atmProxy.getCashInATMMachine());
    }
}
