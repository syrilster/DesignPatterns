package DesignPatternsUsingJava.DecoratorPattern;

/**
 * Created by syrils on 2/29/16.
 */
public class Mocha extends CondimentDecorator {
    Beverage beverage;
    public String myDescription = "Mocha";
    
    public Mocha(Beverage beverage) throws Exception {
        if(beverage.getDescription().contains(myDescription)){
            throw new Exception();
        }
        this.beverage = beverage;
    }
    
    @Override
    public String getDescription() {
        return beverage.getDescription() + " And " + myDescription;
    }

    @Override
    public double getCost() {
        return 0.99 + beverage.getCost();
    }
}
