package DesignPatterns.FactoryPattern;

/**
 * Created by Syril on 09-05-2016.
 */
public interface PizzaIngredientFactory {
    Dough createDough();
    Sauce createSauce();

}
