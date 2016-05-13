package designPattern.TemplateMethodPattern;

/**
 * Created by syrils on 2/29/16.
 */
public class WoodenHouse extends HouseTemplate {
    @Override
    public void buildPillars() {
        System.out.println("\n Building pillars coated with wood");
    }

    @Override
    public void buildWalls() {
        System.out.println("\n Building walls coated with wood");
    }
}
