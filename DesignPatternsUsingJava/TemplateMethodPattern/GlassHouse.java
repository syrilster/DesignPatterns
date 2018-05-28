package TemplateMethodPattern;

/**
 * Created by syrils on 2/29/16.
 */
public class GlassHouse extends HouseTemplate {
    @Override
    public void buildPillars() {
        System.out.println("\n Building pillars coated with Glass");
    }

    @Override
    public void buildWalls() {
        System.out.println("\n Building walls coated with Glass");
    }
}
