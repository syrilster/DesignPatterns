package FactoryPattern;

/**
 * Created by Syril on 09-05-2016.
 */
public abstract class PizzaStore {


    public Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.pack();

        return pizza;
    }

    protected abstract Pizza createPizza(String type);
}
