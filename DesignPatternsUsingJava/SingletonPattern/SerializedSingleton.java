package DesignPatternsUsingJava.SingletonPattern;

import java.io.Serializable;

/**
 * Created by syrils on 2/24/16.
 */
public class SerializedSingleton implements Serializable, Cloneable {

    private static final long serialVersionUID = -7604766932017737115L;

    private SerializedSingleton() {
        if (getInstance() != null) {
            try {
                throw new Exception("Instance Already exists");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class SerializedSingletonHelper {
        private static final SerializedSingleton INSTANCE = new SerializedSingleton();
    }

    public static SerializedSingleton getInstance() {
        return SerializedSingletonHelper.INSTANCE;
    }

    protected Object readResolve() {
        return getInstance();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning is not allowed");
    }
}
