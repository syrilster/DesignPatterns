package FactoryPattern;

/**
 * Created by Syril on 09-05-2016.
 */
public class ThickCrustDough implements Dough {
    @Override
    public void getName() {
        System.out.println("Topping a Thick Crust Dough");
    }

    public ThickCrustDough(){
        System.out.println("Topping a Thick Crust Dough");
    }
}
