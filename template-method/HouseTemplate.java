package designPattern.TemplateMethodPattern;

/**
 * Created by syrils on 2/29/16.
 */
public abstract class HouseTemplate {
    
    public final void buildHouse(){
        buildFoundation();
        buildPillars();
        buildWalls();
        buildWindows();
        System.out.println("\n House Built");
    }
    
    public final void buildFoundation(){
        System.out.println("\n Building foundation using iron, cement and sand");
    }
    
    public void buildWindows(){
        System.out.println("\n Building Glass windows");    
    }
    
    public abstract void buildPillars();
    
    public abstract void buildWalls();
    
}
