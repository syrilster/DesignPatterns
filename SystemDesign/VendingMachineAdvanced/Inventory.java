package VendingMachineAdvanced;

import java.util.HashMap;
import java.util.Map;

/**
 * An Adapter over a HashMap to create Inventory to hold cash and
 * Items inside Vending Machine.
 *
 * @param <T>
 */
public class Inventory<T> {
    private Map<T, Integer> inventory = new HashMap<>();

    public void put(T item, int quantity) {
        inventory.put(item, quantity);
    }

    public boolean hasItem(T item) {
        return getQuantity(item) > 0;
    }

    private Integer getQuantity(T item) {
        Integer value = inventory.get(item);
        return value == null ? 0 : value;
    }

    public void add(T item) {
        int count = inventory.get(item);
        inventory.put(item, count + 1);
    }

    public void deduct(T item) {
        if (hasItem(item)) {
            int count = inventory.get(item);
            inventory.put(item, count - 1);
        }
    }
}
