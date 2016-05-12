package designPattern.StratergyPattern;

/**
 * Created by syrils on 3/28/16.
 */
public class ShoppingCartTest {

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        Item item1 = new Item(1234, 1234, 89.63d);
        Item item2 = new Item(5678, 5678, 1);

        cart.addItem(item1);
        cart.addItem(item2);

        cart.pay(new CreditCardStrategy(123456789, "Syril Sadasivan", 0700, 1234));

        cart.pay(new PaypalStrategy("Anju Ravindran", "ar@hotmail.com"));
    }
}
