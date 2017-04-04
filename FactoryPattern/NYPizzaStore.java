package FactoryPattern;

/**
 * Created by Syril on 09-05-2016.
 */
public class NYPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();
        if (type.equalsIgnoreCase("cheese")) {
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("NY Style Cheese Pizza");
        }
        return pizza;
    }
}
