package DesignPatternsUsingJava.Command;

/**
 * Created by syrils on 5/11/16.
 */
public class RemoteLoader {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        // Create all the devices and their respective on and off commands and assign their slots in the remote 
        // control.
        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");

        CeilingFan livingRoomFan = new CeilingFan("Living Room");

        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);

        LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);

        CeilingFanHighCommand ceilingFanLivingHigh = new CeilingFanHighCommand(livingRoomFan);
        CeilingFanMediumCommand ceilingFanLivingMedium = new CeilingFanMediumCommand(livingRoomFan);
        CeilingFanOffCommand ceilingFanLivingOff = new CeilingFanOffCommand(livingRoomFan);


        remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remoteControl.setCommand(1, kitchenLightOn, kitchenLightOff);
        remoteControl.setCommand(2, ceilingFanLivingMedium, ceilingFanLivingOff);
        remoteControl.setCommand(3, ceilingFanLivingHigh, ceilingFanLivingOff);

        System.out.println(remoteControl);

        remoteControl.onButtonWasPushed(0);
        remoteControl.onButtonWasPushed(1);
        remoteControl.offButtonWasPushed(1);
        remoteControl.offButtonWasPushed(0);
        
        remoteControl.onButtonWasPushed(3);
        remoteControl.undoButtonWasPushed();
    }
}
