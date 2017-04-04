package DecoratorPattern;

/**
 * Created by syrils on 2/29/16.
 */
public class Whip extends CondimentDecorator {
    Beverage beverage;
    
    public Whip(Beverage beverage){
        this.beverage = beverage;
    }
    
    @Override
    public String getDescription() {
        return beverage.getDescription() + " and WHIP ";
    }

    @Override
    public double getCost() {
        return 3.85 + beverage.getCost();
    }
}
