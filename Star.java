import java.awt.Graphics;
import java.awt.Polygon;

public class Star {
    
    private final Polygon shape;
    private final int xPos;
    private final int yPos;
 
    private Star(int xPos, int yPos) {      
        this.xPos = xPos;
        this.yPos = yPos;
        this.shape = createShape(); 
    }
    
    public static Star createAtPosition(int xPos, int yPos) {
        return new Star(xPos, yPos);
    }
    
    public void draw(Graphics g) {
        g.drawPolygon(shape);
    }
    
    private Polygon createShape() {
        
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
            xCoordinates[i] = (int) coordinates[i].xPos();
            yCoordinates[i] = (int) coordinates[i].yPos();
        }
        
        return new Polygon(xCoordinates, yCoordinates, coordinates.length);
    }
    
    
}
