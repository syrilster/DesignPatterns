package designPattern.command;

/**
 * Created by syrils on 5/11/16.
 */
public class RemoteControl {
    // on and off commands held in an array of Commands
    Command[] onCommands;
    Command[] offCommands;

    Command undoCommand;

    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];

        // Null Object Pattern used here : NoCommand is and example of null object - when you dont have a meaningful 
        // object to return, and yet you want to remove the responsibility for handling null from the client. 
        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        undoCommand = noCommand;
    }

    /**
     * Takes the slot position and an on and off command to be stored in that slot. It puts these commands in the on
     * and off arrays for later use.
     *
     * @param slot
     * @param onCommand
     * @param offCommand
     */
    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    //When on and off button is pressed, this method takes care of calling the corresponding execute methods.
    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
    }

    public void undoButtonWasPushed() {
        undoCommand.undo();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n ----- Remote Control ---- \n");
        for (int i = 0; i < onCommands.length; i++) {
            stringBuilder.append("[Slot " + i + "] " + onCommands[i].getClass().getName() + " " + offCommands[i]
                    .getClass().getName() + "\n");
        }

        return stringBuilder.toString();
    }
}
