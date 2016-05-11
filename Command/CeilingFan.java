package designPattern.command;

/**
 * Created by syrils on 5/11/16.
 */
public class CeilingFan {
    public static final int HIGH = 3;
    public static final int MEDIUM = 2;
    public static final int LOW = 1;
    public static final int OFF = 0;
    private String location;
    int speed;

    public CeilingFan(String name) {
        this.location = name;
        speed = OFF;
    }

    public void high() {
        speed = HIGH;
        System.out.println(location + " Fan is on HIGH");
    }

    public void low() {
        speed = LOW;
        System.out.println(location + " Fan is on LOW");
    }

    public void medium() {
        speed = MEDIUM;
        System.out.println(location + " Fan is on MEDIUM");
    }

    public void off() {
        speed = OFF;
        System.out.println(location + " Fan is OFF");
    }

    public int getSpeed() {
        return speed;
    }
}
