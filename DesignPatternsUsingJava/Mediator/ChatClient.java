package Mediator;

/**
 * Created by syrils on 5/13/16.
 */
public class ChatClient {
    public static void main(String[] args) {
        ChatMediator chatMediator = new ChatMediatorImpl();
        User syril = new UserImpl(chatMediator, "Syril");
        User anju = new UserImpl(chatMediator, "Anju");
        User sandeep = new UserImpl(chatMediator, "Sandeep");
        User sadasivan = new UserImpl(chatMediator, "Sadasivan");
        chatMediator.addUser(syril);
        chatMediator.addUser(anju);
        chatMediator.addUser(sandeep);
        chatMediator.addUser(sadasivan);
        
        syril.send("Hi ALL !!!!");
    }
}
