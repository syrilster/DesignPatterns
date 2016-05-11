package designPattern.command;

/**
 * Created by syrils on 5/11/16.
 */
public class CeilingFan {
    private String name;

    public CeilingFan(String name) {
        this.name = name;
    }

    public void on() {
        System.out.println(name + " Fan Switched on");
    }

    public void off() {
        System.out.println(name + " Fan Switched off");
    }
}
