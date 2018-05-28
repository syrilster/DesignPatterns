package ATMDesign;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static org.junit.Assert.assertEquals;

/**
 * Created by Syril on 17-05-2016.
 */
public class TestATMMachine {
    private Logger logger = LoggerFactory.getLogger(TestATMMachine.class);

    private ATMMachine atmMachine;
    private GetATMData atmProxy;

    @Before
    public void setUp() {
        atmMachine = new ATMMachine();
        atmProxy = new ATMProxy(atmMachine);
    }

    @Test
    public void basicTestMethod() {
        atmMachine.insertCard();
        assertEquals("User has entered the correct pin", atmMachine.insertPin(1234));
        atmMachine.requestCash(1500);

        assertEquals(500, atmProxy.getCashInATMMachine());
    }

    @Test
    public void whenUserEntersIncorrectPinThrowError() {
        atmMachine.insertCard();
        assertEquals("Wrong Pin entered by the User", atmMachine.insertPin(1122));
    }

    @Test
    public void whenATMHasNoCash() {
        atmMachine.insertCard();
        logger.info(atmMachine.insertPin(1234));
        assertEquals("Amount cant be processed !!, Card Ejected", atmMachine.requestCash(8000));
    }

    @Test
    public void whenUserHasWrongCardDetailsAndRequestCash() {
        atmMachine.insertCard();
        logger.info(atmMachine.insertPin(11223));
        assertEquals("Please insert the card first", atmMachine.requestCash(8000));
    }
}
