import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Tree {

    private final int xPos;
    private final int yPos;
    private final int size;
    private final int discount;
    private boolean isChoppedDown;
    private Polygon treeShape;
    private Polygon choppedShape;
    
    private Tree(int xPos, int yPos, int size) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.size = size;
        this.discount = getWeightedRandomDiscount();
        this.isChoppedDown = false;
        initializeShapes();
    }
    
    public static Tree createAtPositionWithSize(int xPos, int yPos, int size) {
        return new Tree(xPos, yPos, size);
    }
    
    private static int getWeightedRandomDiscount() {
        double randomNumber = Math.random();
        return (randomNumber < 0.1) 
                ? 30 
                : (randomNumber < 0.2) 
                    ? 25 
                    : (randomNumber < 0.3) 
                        ? 20 
                        : (randomNumber < 0.5)
                            ? 15
                            : (randomNumber < 0.7)
                                ? 10
                                : 5;
    }
    
    public boolean isChoppedDown() {
        return isChoppedDown;
    }
    
    public boolean chopDown(int xPos, int yPos) {
        if (treeShape.contains(xPos, yPos))
            isChoppedDown = true;
        return isChoppedDown;
    }
    
    public void draw(Graphics g) {
        if (!isChoppedDown)
            g.fillPolygon(treeShape);
        else {
            g.fillPolygon(choppedShape);
            
            Color color = g.getColor();
            g.setColor(Color.black);
            g.drawString(discount + "%", (xPos + 5), (yPos + 5));
            g.setColor(color);
        }
    }
    
    private void initializeShapes() {
        
        Point[] coordinates = {
                Point.createAtPosition(xPos, yPos),
                Point.createAtPosition(xPos + (size * 2 / 5), yPos - (size / 10)),
                Point.createAtPosition(xPos + (size * 2 / 5), yPos),
                Point.createAtPosition(xPos + (size * 3 / 5), yPos),
                Point.createAtPosition(xPos + (size * 3 / 5), yPos - (size / 10)),
                Point.createAtPosition(xPos + size, yPos),
                Point.createAtPosition(xPos + (size * 2 / 3), yPos - (size / 3)),
                Point.createAtPosition(xPos + (size * 19 / 20), yPos - (size / 5)),
                Point.createAtPosition(xPos + (size * 2 / 3), yPos - (size * 2 / 3)),       
                Point.createAtPosition(xPos + (size * 7 / 8), yPos - (size / 2)),
                Point.createAtPosition(xPos + (size / 2), yPos - size),
                Point.createAtPosition(xPos + (size * 1 / 8), yPos - (size / 2)),
                Point.createAtPosition(xPos + (size / 3), yPos - (size * 2 / 3)),
                Point.createAtPosition(xPos + (size * 1 / 20), yPos - (size / 5)),
                Point.createAtPosition(xPos + (size / 3), yPos - (size / 3))
        };
        
        int[] xPosNormal = new int[coordinates.length];
        int[] yPosNormal = new int[coordinates.length];
        
        int[] xPosChopped = new int[coordinates.length];
        int[] yPosChopped = new int[coordinates.length];
        
        for (int i = 0; i < coordinates.length; i++) {
            xPosNormal[i] = (int) coordinates[i].xPos();
            yPosNormal[i] = (int) coordinates[i].yPos();
            coordinates[i] = coordinates[i].rotateAroundPointBy(Point.createAtPosition(xPos, yPos), 270);
            xPosChopped[i] = (int) coordinates[i].xPos();
            yPosChopped[i] = (int) coordinates[i].yPos();
        }
        
        treeShape = new Polygon(xPosNormal, yPosNormal, coordinates.length);
        choppedShape = new Polygon(xPosChopped, yPosChopped, coordinates.length);
        
    }
  
}
