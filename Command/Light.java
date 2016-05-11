package designPattern.command;

/**
 * Created by syrils on 5/11/16.
 */
public class Light {

    private String name;

    public Light(String name) {
        this.name = name;
    }

    public void on() {
        System.out.println(name + " Light Switched on");
    }

    public void off() {
        System.out.println(name + " Light Switched off");
    }
}
