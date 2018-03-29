package DesignPatternsUsingJava.Builder;

/**
 * Created by syrils on 5/10/16.
 */
public class TestBuilderPattern {
    public static void main(String[] args) {
        Computer comp = new Computer.ComputerBuilder("500 GB", "2 GB").setBluetoothEnabled(true).build();

        System.out.println(comp);
    }
}
