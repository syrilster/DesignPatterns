package VendingMachineAdvanced;

import org.junit.Test;

public class VendingMachineTest {

    @Test
    public void basicTest() {
        VendingMachineImpl vendingMachine = new VendingMachineImpl();
        System.out.println(vendingMachine.selectItemAndGetPrice(Item.COOKIE));
        vendingMachine.insertCoin(Coin.QUARTER);
        System.out.println(vendingMachine.getCurrentBalance());
        vendingMachine.selectItemAndGetPrice(Item.CHIPS);
        vendingMachine.getItemAndChange();
    }
}
