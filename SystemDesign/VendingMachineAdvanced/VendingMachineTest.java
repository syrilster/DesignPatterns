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
        vendingMachine.insertCoin(Coin.QUARTER);
        System.out.println(((VendingMachineImpl) vendingMachine).getCurrentBalance());
        vendingMachine.selectItemAndGetPrice(Item.CHIPS);
        List<Coin> change = vendingMachine.getItemAndChange().getCoin();
        assertEquals(4, change.size());
        assertEquals(13, getTotal(change));
        vendingMachine.printStats();
    }

    @Test
    public void testBuyItemWithExactPrice() {
        long price = vendingMachine.selectItemAndGetPrice(Item.CHIPS);

        assertEquals("Price Should be the CHIPS price", Item.CHIPS.getPrice(), price);
        vendingMachine.insertCoin(Coin.NICKLE);
        vendingMachine.insertCoin(Coin.CENT);
        vendingMachine.insertCoin(Coin.CENT);
        Knapsack<Item, List<Coin>> knapsack = vendingMachine.getItemAndChange();
        Item item = knapsack.getItem();
        List<Coin> change = knapsack.getCoin();

        assertEquals("Item should CHIPS", Item.CHIPS, item);
        assertTrue("Change should be empty", change.isEmpty());
    }

    @Test
    public void testBuyItemWithMorePrice() {
        long price = vendingMachine.selectItemAndGetPrice(Item.SODA);
        assertEquals("Price Should be the CHIPS price", Item.SODA.getPrice(), price);

        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.QUARTER);

        Knapsack<Item, List<Coin>> knapsack = vendingMachine.getItemAndChange();
        List<Coin> change = knapsack.getCoin();

        assertTrue("Change should not be empty", !change.isEmpty());
        assertEquals("Change comparision", 50 - Item.SODA.getPrice(), getTotal(change));
    }

    @Test
    public void testRefund() {
        long price = vendingMachine.selectItemAndGetPrice(Item.COOKIE);
        assertEquals("Price Should be the CHIPS price", Item.COOKIE.getPrice(), price);

        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.NICKLE);
        vendingMachine.insertCoin(Coin.PENNY);

        assertEquals(5, getTotal(vendingMachine.getRefund()));
    }

    @Test(expected = SoldOutException.class)
    public void testSoldOut() {
        for (int i = 0; i < 10; i++) {
            vendingMachine.selectItemAndGetPrice(Item.COKE);
            vendingMachine.insertCoin(Coin.QUARTER);
            vendingMachine.getItemAndChange();
        }
    }

    @Test(expected=SoldOutException.class)
    public void testReset(){
        VendingMachine vendingMachine = VendingMachineFactory.createVendingMAchine();
        vendingMachine.reset();

        vendingMachine.selectItemAndGetPrice(Item.COOKIE);
    }

    @Test(expected = NotSufficientChangeException.class)
    public void testNotSufficientChange(){
        for (int i = 0; i < 5; i++) {
            vendingMachine.selectItemAndGetPrice(Item.PEPSI);
            vendingMachine.insertCoin(Coin.QUARTER);
            vendingMachine.insertCoin(Coin.QUARTER);
            vendingMachine.insertCoin(Coin.QUARTER);
            vendingMachine.getItemAndChange();

            vendingMachine.selectItemAndGetPrice(Item.CHIPS);
            vendingMachine.insertCoin(Coin.QUARTER);
            vendingMachine.insertCoin(Coin.QUARTER);
            vendingMachine.getItemAndChange();
        }
    }


    private int getTotal(List<Coin> coins) {
        return coins.stream()
                //.mapToInt(e -> e.getDenomination())
                .mapToInt(Coin::getDenomination)
                .reduce(0, Integer::sum);

    }
}
