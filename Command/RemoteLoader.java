package designPattern.command;

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

        CeilingFan bedRoom = new CeilingFan("Bed Room");
        CeilingFan livingRoomFan = new CeilingFan("Living Room");

        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);

        LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);

        CeilingFanOnCommand bedRoomFanOn = new CeilingFanOnCommand(bedRoom);
        CeilingFanOffCommand bedRoomFanOff = new CeilingFanOffCommand(bedRoom);

        CeilingFanOnCommand livingRoomFanOn = new CeilingFanOnCommand(livingRoomFan);
        CeilingFanOffCommand livingRoomFanOff = new CeilingFanOffCommand(livingRoomFan);

        remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remoteControl.setCommand(1, kitchenLightOn, kitchenLightOff);
        remoteControl.setCommand(2, bedRoomFanOn, bedRoomFanOff);
        remoteControl.setCommand(3, livingRoomFanOn, livingRoomFanOff);

        System.out.println(remoteControl);

        remoteControl.onButtonWasPushed(0);
        remoteControl.onButtonWasPushed(1);
        remoteControl.offButtonWasPushed(1);
        remoteControl.offButtonWasPushed(0);
        remoteControl.onButtonWasPushed(3);
        remoteControl.offButtonWasPushed(3);
    }
}
