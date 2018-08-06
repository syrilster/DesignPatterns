package ExecuteAroundMethodPattern;

/**
 * Created by Syril on 06-05-2018.
 */
public class ExecuteAroundMethod {
    public static void main(String[] args) {
        //Resource resource = new Resource();
        /* Problems: Easy to forget close and when there is an exception close wont be reached.
        resource.op1();
        resource.op2();
        resource.close();*/

        //This is more verbose
        /*try {
            resource.op1();
            resource.op2();
        } finally {
            resource.close();
        }*/

        //Resource now implements AutoCloseable marker interface(Automatic Resource Management)
        /*try (Resource resource = new Resource()) {
            resource.op1();
            resource.op2();
        }*/

        /*Caller has lot of pre and post steps to do for the code he does not handle. Let's use a execute around pattern.
        This is used when boiler plate code is there in the some method and before the user does the actual operation, some pre and post
        step is taken care of by the original author*/
        Resource.use(resource -> resource.op1()
                                         .op2());
    }
}
