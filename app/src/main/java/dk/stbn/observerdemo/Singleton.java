package dk.stbn.observerdemo;

import java.util.ArrayList;
import java.util.Random;

public class Singleton {

    //Singleton stuff here
    private Singleton (){}

    private static Singleton instance;

    public static Singleton getInstance(){
        if (instance == null) instance = new Singleton();
        return instance;
    }
    //End og singleton stuff

    //Observer related stuff here
    private ArrayList<MyCustomObserver> observable = new ArrayList();

    public void observeOrSubscribe (MyCustomObserver o) {
        observable.add(o);
    }

    public void unsubscribe(MyCustomObserver o){
        observable.remove(o);
    }

    public void anEventHappened(){
        for (MyCustomObserver o : observable){
            o.itHappened();
        }
    }

    //Test/demo functionality: If a random value is above 95, then notify the observers

    private int value;

    public void generateTestValues(){
        Random random = new Random();
        value = random.nextInt(100);
        if (value > 75) anEventHappened(); //!!! This check could be anywhere
        System.out.println("Value is: " + value);
    }

    public int getValue(){
        return value;
    }
    //End test/demo
}
