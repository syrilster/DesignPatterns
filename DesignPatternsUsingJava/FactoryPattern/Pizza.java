package FactoryPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Syril on 09-05-2016.
 */
public abstract class Pizza {

    String name;
    Dough dough;
    Sauce sauce;

    List<String> toppings = new ArrayList<>();

    abstract void prepare();

    public void cut() {
        System.out.println("Cutting the Pizza into equal halves");
    }

    public void bake() {
        System.out.println("Baking the pizza for 20 mins at 200 degrees");
    }

    public void pack() {
        System.out.println("Packing the pizza.. Please Wait.");
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
