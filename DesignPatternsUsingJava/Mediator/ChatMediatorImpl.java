package DesignPatternsUsingJava.Mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syrils on 5/13/16.
 */
public class ChatMediatorImpl implements ChatMediator {

    List<User> users;

    ChatMediatorImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public void sendMessage(String message, User user) {
        for (User u : users) {
            if (u != user) {
                u.receive(message);
            }
        }
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }
}
