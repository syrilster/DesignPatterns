package VendingMachineAdvanced;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class VendingMachineTest {

    @Test
    public void basicTest() {
        VendingMachineImpl vendingMachine = new VendingMachineImpl();
        System.out.println(vendingMachine.selectItemAndGetPrice(Item.COOKIE));
        vendingMachine.insertCoin(Coin.QUARTER);
        System.out.println(vendingMachine.getCurrentBalance());
        vendingMachine.selectItemAndGetPrice(Item.CHIPS);
        List<Coin> change = vendingMachine.getItemAndChange().getCoin();
        assertEquals(4, change.size());
    }
}
