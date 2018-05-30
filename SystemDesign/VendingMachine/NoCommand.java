package VendingMachine;

/**
 * Created by Syril on 19-07-2016.
 */
public class NoCommand implements Command {

    public void execute() {
        try {
            throw new Exception("Invalid Request");
        } catch (Exception e) {
            System.out.println("Invalid Request.. Please try again !!");
        }
    }
}
