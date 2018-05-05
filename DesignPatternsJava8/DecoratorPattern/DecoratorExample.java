package DesignPatternsJava8.DecoratorPattern;

import java.awt.*;

/**
 * Created by Syril on 05-05-2018.
 */
@SuppressWarnings("ALL")
public class DecoratorExample {
    public static void printSnap(Camera camera) {
        System.out.println(camera.snap(new Color(125, 125, 125)));
    }

    public static void main(String[] args) {
        printSnap(new Camera());
        printSnap(new Camera(Color::brighter));
        printSnap(new Camera(Color::darker));
        // Chain things like a decorator without the scary new, new, new in Java 7 world
        printSnap(new Camera(Color::brighter, Color::darker));
    }
}
