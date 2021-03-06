package DecoratorPattern;

/**
 * Created by syrils on 2/29/16.
 */
public class DecoratorTest {

    public static void main(String[] args) throws Exception {
        Beverage beverage1 = new HouseBlend();
        beverage1 = new Mocha(beverage1);
        System.out.println(beverage1.getDescription() + " costs --- " + beverage1.getCost());
        
        Beverage beverage2 = new Espresso();
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription() + " costs --- " + beverage2.getCost());
    }
}
