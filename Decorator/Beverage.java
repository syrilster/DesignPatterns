package designPattern.DecoratorPattern;

/**
 * Created by syrils on 2/29/16.
 */
public abstract class Beverage {
    
    public String description = "unknown Beverage";
    
    public String getDescription(){
        return description;
    }
    
    public abstract double getCost();
}
