package DesignPatternsUsingJava.FactoryPattern;

/**
 * Created by Syril on 09-05-2016.
 */
public class ThinCrustDough implements Dough {
    @Override
    public void getName() {
        System.out.println("Topping a Thin Crust Dough");
    }

    public ThinCrustDough() {
        System.out.println("Topping a Thin Crust Dough");
    }
}
