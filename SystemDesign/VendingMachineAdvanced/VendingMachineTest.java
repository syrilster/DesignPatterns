package VendingMachineAdvanced;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void testBuyWithExactPrice() {
        long price = vendingMachine.selectItemAndGetPrice(Item.CHIPS);

        assertEquals("Price Should be the CHIPS price", Item.CHIPS.getPrice(), price);
        vendingMachine.insertCoin(Coin.NICKLE);
        vendingMachine.insertCoin(Coin.CENT);
        vendingMachine.insertCoin(Coin.CENT);
        Container<Item, List<Coin>> container = vendingMachine.getItemAndChange();
        Item item = container.getItem();
        List<Coin> change = container.getCoin();

        assertEquals("Item should CHIPS", Item.CHIPS, item);
        assertTrue("Change should be empty", change.isEmpty());
    }

    private int getTotal(List<Coin> coins) {
        return coins.stream()
                //.mapToInt(e -> e.getDenomination())
                .mapToInt(Coin::getDenomination)
                .reduce(0, Integer::sum);

    }
}
