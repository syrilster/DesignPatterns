package VendingMachineAdvanced;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class VendingMachineTest {

    private static VendingMachine vendingMachine;

    @BeforeClass
    public static void setUp() {
        vendingMachine = VendingMachineFactory.createVendingMAchine();
    }

    @AfterClass
    public static void tearDown() {
        vendingMachine = null;
    }

    @Test
    public void basicTest() {
        vendingMachine.printStats();

        System.out.println(vendingMachine.selectItemAndGetPrice(Item.COOKIE));
        vendingMachine.insertCoin(Coin.QUARTER);
        System.out.println(((VendingMachineImpl) vendingMachine).getCurrentBalance());
        vendingMachine.selectItemAndGetPrice(Item.CHIPS);
        List<Coin> change = vendingMachine.getItemAndChange().getCoin();
        assertEquals(4, change.size());
        assertEquals(13, getTotal(change));
        vendingMachine.printStats();
    }

    private int getTotal(List<Coin> coins) {
        return coins.stream()
                //.mapToInt(e -> e.getDenomination())
                .mapToInt(Coin::getDenomination)
                .reduce(0, Integer::sum);

    }
}
