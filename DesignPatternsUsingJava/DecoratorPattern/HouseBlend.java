package DesignPatternsUsingJava.DecoratorPattern;

public class HouseBlend extends Beverage {
    @Override
    public double getCost() {
        return 1.99;
    }
    
    public HouseBlend(){
        description = "House Blend";
    }
}
