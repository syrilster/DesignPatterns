package designPattern.StratergyPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syrils on 3/28/16.
 */
public class ShoppingCart {

    private List<Item> items;

    ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item){
        this.items.add(item);
    }
    
    public void removeItem(Item item){
        this.items.remove(item);    
    }

    public double calculateTotal() {
        double sum = 0L;
        for (Item item : items) {
            sum = sum + item.getPrice();
        }
        return sum;
    }

    public void pay(PaymentStrategy paymentMethod) {
        double amount = calculateTotal();
        paymentMethod.pay(amount);
    }
}
