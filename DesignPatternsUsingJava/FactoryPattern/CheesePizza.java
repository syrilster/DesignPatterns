package DesignPatternsUsingJava.FactoryPattern;

/**
 * Created by syrils on 5/10/16.
 */
public class CheesePizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;

    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }


    @Override
    void prepare() {
        System.out.println("Preparing pizza ... " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();

    }

    @Override
    public void cut() {
        if (ingredientFactory instanceof ChicagoPizzaIngredientFactory) {
            System.out.println("Cutting into square slices !!!");
        } else {
            super.cut();
        }
    }
}
