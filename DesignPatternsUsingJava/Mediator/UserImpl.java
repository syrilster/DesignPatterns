package Mediator;

/**
 * Created by syrils on 5/13/16.
 */
public class UserImpl extends User {

    public UserImpl(ChatMediator chatMediator, String name) {
        super(chatMediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println(this.name + " : Sending Message = " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println(this.name + " Received Message = " + message);
    }
}
