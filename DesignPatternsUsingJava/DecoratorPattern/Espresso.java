package DesignPatternsUsingJava.DecoratorPattern;

/**
 * Created by syrils on 2/29/16.
 */
public class Espresso extends Beverage {
    @Override
    public double getCost() {
        return 5;
    }

    public Espresso(){
        description = "Espresso";
    }
}
