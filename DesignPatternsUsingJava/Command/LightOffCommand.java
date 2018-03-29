package DesignPatternsUsingJava.Command;

/**
 * Created by syrils on 5/11/16.
 */
public class LightOffCommand implements Command {

    Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
    }
}
