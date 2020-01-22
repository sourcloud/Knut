import java.awt.Graphics;
import java.awt.Polygon;

public class Star {
    
    private int xPos;
    private int yPos;
    private Polygon starShape;
 
    public Star(int xPos, int yPos) {      
        this.xPos = xPos;
        this.yPos = yPos;
        setStarShape(); 
    }
    
    private void setStarShape() {
        int[] xCoordinates = {xPos - 5, xPos + 7, xPos - 7, xPos + 5, xPos};
        int[] yCoordinates = {yPos + 8, yPos, yPos, yPos + 8, yPos - 6};        
        this.starShape = new Polygon(xCoordinates, yCoordinates, xCoordinates.length);
    }
    
    public void draw(Graphics g) {
        g.drawPolygon(starShape);
    }
    
}
