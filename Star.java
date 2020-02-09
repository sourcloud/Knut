package knut;
<<<<<<< HEAD
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

/**
 * Class that is able to store a position and draw star to a graphic context at that position.
 * 
 */
public class Star implements Observer {
    
    /**
     * Color to draw star with. Is the same for all instances.
     * 
     */
    private static final Color drawColor = Color.yellow;
    
    /**
     * State that determines if star is visible.  
     */
    private boolean isVisible = false;
    
    /**
     * Int values representing x- and y-coordinates in a two dimensional coordinate system.
     */
    private final int xPos;
    private final int yPos;
    
    /**
     * Subject to observe.
     */
    private Subject subject;
    
    /**
     * Polygon representing the shape of an star
     */
    private Polygon shape;
    
    public static class Builder {
        private final int xPos;
        private final int yPos;
        
        private Subject subject = null;
        
        public Builder(int xPos, int yPos) {
            this.xPos = xPos;
            this.yPos = yPos;
        }
        
        public Builder subject(Subject subject) {
            this.subject = subject;
            return this;
        }
        
        public Star build() {
            return new Star(this);
        }
    }
 
    /**
     * Constructs new star at given x- and y-position.
     * 
     * @param xPos An int representing x-coordinate.
     * @param yPos An int representing y-coordinate.
     */
    private Star(Builder builder) {      
        this.xPos = builder.xPos;
        this.yPos = builder.yPos;
        this.subject = builder.subject;
        
        if (this.subject != null)
            subscribe(this.subject);
        
        initializeShape(); 
    }
    
    /**
     * Draws star on given graphic context.
     * 
     * @param g2d Graphic context to draw on.
     */
    public void draw(Graphics2D g2d) {
        if (isVisible) {
            g2d.setColor(drawColor);
            g2d.drawPolygon(shape);
        }
    }
    
    /**
     * Initializes shape of star.
     * 
     * Initializes shape of star by creating points around x- and y-coordinates of it and creating
     * a polygon out of them.
     */
    private void initializeShape() {
=======
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Star {
    
    private final int xPos;
    private final int yPos;
    
    private Polygon shape;
 
    private Star(int xPos, int yPos) {      
        this.xPos = xPos;
        this.yPos = yPos;
        this.shape = initializeShape(); 
    }
    
    public static Star createAtPosition(int xPos, int yPos) {
        return new Star(xPos, yPos);
    }
    
    public void draw(Graphics2D g2d) {
        g2d.drawPolygon(shape);
    }
    
    private Polygon initializeShape() {
>>>>>>> bc86e3fbc8d55be6747035d3eee1feb9002d2c2d
        
        Point[] coordinates = {
                Point.createAtPosition(xPos - 5, yPos + 8),
                Point.createAtPosition(xPos + 7, yPos),
                Point.createAtPosition(xPos - 7, yPos),
                Point.createAtPosition(xPos + 5, yPos + 8),
                Point.createAtPosition(xPos, yPos - 6)
        };
        
        int[] xCoordinates = new int[coordinates.length];
        int[] yCoordinates = new int[coordinates.length];
        
        for (int i = 0; i < coordinates.length; i++) {
            xCoordinates[i] = (int) coordinates[i].getX();
            yCoordinates[i] = (int) coordinates[i].getY();
        }
        
<<<<<<< HEAD
        this.shape = new Polygon(xCoordinates, yCoordinates, coordinates.length);
    }

    @Override
    public void update() {
        isVisible = !isVisible;       
    }
    
    @Override
    public void subscribe(Subject s) {
        s.registerObserver(this);
    }

    @Override
    public void unsubscribe(Subject s) {
        s.removeObserver(this);
=======
        return new Polygon(xCoordinates, yCoordinates, coordinates.length);
>>>>>>> bc86e3fbc8d55be6747035d3eee1feb9002d2c2d
    }
    
    
}
