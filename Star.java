import java.awt.Graphics;
import java.awt.Polygon;

public class Star {
    
    private int xPos;
    private int yPos;
    private Polygon starShape;
 
    public Star(int xPos, int yPos) {      
        this.xPos = xPos;
        this.yPos = yPos;
        initializeShape(); 
    }
    
    private void initializeShape() {
        
        Point[] coordinates = {
                Point.valueOf(xPos - 5, yPos + 8),
                Point.valueOf(xPos + 7, yPos),
                Point.valueOf(xPos - 7, yPos),
                Point.valueOf(xPos + 5, yPos + 8),
                Point.valueOf(xPos, yPos - 6)
        };
        
        int[] xCoordinates = new int[coordinates.length];
        int[] yCoordinates = new int[coordinates.length];
        
        for (int i = 0; i < coordinates.length; i++) {
            xCoordinates[i] = (int) coordinates[i].xPos();
            yCoordinates[i] = (int) coordinates[i].yPos();
        }
        
        this.starShape = new Polygon(xCoordinates, yCoordinates, xCoordinates.length);
    }
    
    public void draw(Graphics g) {
        g.drawPolygon(starShape);
    }
    
}
