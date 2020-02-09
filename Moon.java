package knut;
<<<<<<< HEAD
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Class that is able to store position and radius of moon and draw it to a graphic context.
 *
 */
public class Moon implements Subject {

    /**
     * Int values representing position and radius of moon in a 2D coordinate system.
     */
    private final int xPos;
    private final int yPos;
    private final int radius;
    private final Color color;
    
    /**
     * Boolean value answering the question if moon is shining.
     */
    private boolean isShining;
    
    /**
     * ArrayList of objects observing moon behaviour.
     */
    private ArrayList<Observer> observers;
    
    /**
     * Constructs moon at given x- and y-coordinates with given radius.
     * 
     * @param xPos An int representing x-coordinate.
     * @param yPos An int representing y-coordinate.
     * @param radius An int representing radius of moon.
     */
    private Moon(int xPos, int yPos, int radius) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;  
        this.color = Color.yellow;
        this.isShining = false;
        this.observers = new ArrayList<Observer>();
    }
    
    /**
     * Calls constructor and returns new instance of moon.
     * 
     * @param xPos An int representing x-coordinate.
     * @param yPos An int representing y-coordinate.
     * @param radius An int representing radius of moon.
     * @return New instance of moon with given radius and position.
     */
=======
import java.awt.*;
import java.util.*;
import java.util.List;

public class Moon {
    // Der rufende
    /*interface HelloListener {
        void someoneSaidHello();
    }

    // Rufmanager
    class Initiater {
        private List<HelloListener> listeners = new ArrayList<HelloListener>();

        public void addListener(HelloListener toAdd) {
            listeners.add(toAdd);
        }

        public void sayHello() {
            System.out.println("Hello!!");

            // Notify everybody that may be interested.
            for (HelloListener hl : listeners)
                hl.someoneSaidHello();
        }
    }

    // Antwort-Rufer
    class Responder implements HelloListener {
        @Override
        public void someoneSaidHello() {
            System.out.println("Hello there...");
        }
    }*/

    // RufMain
    /*class Test {
        public static void main(String[] args) {
            Initiater initiater = new Initiater();
            Responder responder = new Responder();

            initiater.addListener(responder);

            initiater.sayHello();  // Prints "Hello!!!" and "Hello there..."
        }
    }*/

    private final int xPos;
    private final int yPos;
    private final int radius;
    private boolean dayTime;

    /* --- Event --- */
    // Eventvorlage / Rufvorlage
    // (knut.MoonDayNightListener)

    // Angewendete EventListener
    private List<MoonDayNightListener> applyedListeners;

    private void triggerEventDayNight() {
        // Durchlaufe alle wartende Listeners
        for (MoonDayNightListener listener : this.applyedListeners)
            listener.onChange(this.dayTime);
    }


    public void addDayNightListener(MoonDayNightListener toAddListener) {
        this.applyedListeners.add(toAddListener);
    }

    /* ---- ---- */
    
    private Moon(int xPos, int yPos, int radius) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;   
        this.dayTime = true;
        this.applyedListeners = new ArrayList();
    }
    
>>>>>>> bc86e3fbc8d55be6747035d3eee1feb9002d2c2d
    public static Moon createAtPositionWithRadius(int xPos, int yPos, int radius) {
        return new Moon(xPos, yPos, radius);
    }
    
<<<<<<< HEAD
    /**
     * Checks if moon is clicked on graphics context.
     * 
     * Checks if moon is clicked by checking if distance between center of moon and
     * point where click happened is smaller than moon radius.
     * 
     * @param xPos An int representing x-coordinate of click.
     * @param yPos An int representing y-coordinate of click.
     * @return Boolean answering if click happened inside moon shape.
     */
    public boolean isClicked(int xPos, int yPos) {
        Point centerPoint = Point.createAtPosition(this.xPos, this.yPos);
        Point clickPoint = Point.createAtPosition(xPos, yPos);       
        return (centerPoint.distanceTo(clickPoint) <= radius);
    }
    
    /**
     * Switches isShining from true to false and vice versa.
     * 
     */
    public void switchShiningState() {
        isShining = !isShining;
        notifyObservers();
    }
    
    
    /**
     * Draws moon on given graphic context.
     * 
     * @param g2d Graphic context to draw on.
     */
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        int diameter = 2 * radius;
        int xOffset = xPos - radius;    // Offset so that (xPos, yPos) is center.
        int yOffset = yPos - radius;
        if (isShining)
            g2d.fillOval(xOffset, yOffset, diameter, diameter);
        else
            g2d.drawOval(xOffset, yOffset, diameter, diameter);
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0)
            observers.remove(i);      
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers)
            observer.update();      
=======
    public boolean isShining() {
        return !dayTime;
    }

    //public boolean switchTime(int xPos, int yPos) {
    public boolean g2dClick(int xPos, int yPos) {
        Point centerPoint = Point.createAtPosition(this.xPos, this.yPos);
        Point clickPoint = Point.createAtPosition(xPos, yPos);
        
        boolean isClicked = (centerPoint.distanceTo(clickPoint) <= radius);
        if (isClicked)
            dayTime = !dayTime;
            this.triggerEventDayNight(); // Trigger Repain @Knut

        return isClicked;
    }
    
    public void draw(Graphics2D g2d) {
        int diameter = 2 * radius,
            objDrawPointX = xPos - radius,
            objDrawPointY = yPos - radius;

        Color backgroundColor;

        if (dayTime) {
            backgroundColor = new Color(35, 100, 65);
            g2d.drawOval(objDrawPointX, objDrawPointY, diameter, diameter);     // circle with center at (xPos, yPos)
        } else {
            backgroundColor = new Color(20, 50, 30);
            g2d.fillOval(objDrawPointX, objDrawPointY, diameter, diameter);
        }

        g2d.setColor(backgroundColor);
>>>>>>> bc86e3fbc8d55be6747035d3eee1feb9002d2c2d
    }
}
