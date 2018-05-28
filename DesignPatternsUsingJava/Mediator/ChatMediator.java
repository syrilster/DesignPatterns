package Mediator;

/**
 * Created by syrils on 5/13/16.
 */
public interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}
