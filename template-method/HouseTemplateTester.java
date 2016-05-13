package designPattern.TemplateMethodPattern;

/**
 * Created by syrils on 2/29/16.
 */
public class HouseTemplateTester {
    
    public static void main(String[] args){
        HouseTemplate woodenHouse = new WoodenHouse();
        HouseTemplate glassHouse = new GlassHouse();
        
        woodenHouse.buildHouse();
        glassHouse.buildHouse();
    }
}
