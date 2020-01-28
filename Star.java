package knut;
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
        
        return new Polygon(xCoordinates, yCoordinates, coordinates.length);
    }
    
    
}
