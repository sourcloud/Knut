package knut;
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
    public static Moon createAtPositionWithRadius(int xPos, int yPos, int radius) {
        return new Moon(xPos, yPos, radius);
    }
    
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
    }
}
