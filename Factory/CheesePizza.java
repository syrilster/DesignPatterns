package DesignPatterns.FactoryPattern;

/**
 * Created by Syril on 09-05-2016.
 */
public class CheesePizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;

    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }


    @Override
    void prepare() {
        System.out.println("Preparing.." + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
    }
}
