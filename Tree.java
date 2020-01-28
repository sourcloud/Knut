package knut;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Tree {

    private final int xPos;
    private final int yPos;
    private final int size;
    private final int discount;
    
    private Polygon treeShape;
    private Polygon choppedShape;
    private boolean isChoppedDown;
    
    private Tree(int xPos, int yPos, int size) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.size = size;
        this.discount = getWeightedRandomDiscount();
        this.isChoppedDown = false;
        initializeShapes();
    }
    
    private Tree(int xPos, int yPos, int size, int discount) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.size = size;
        this.discount = discount;
        this.isChoppedDown = false;
        initializeShapes();
    }
    
    public static Tree createAtPositionWithSize(int xPos, int yPos, int size) {
        return new Tree(xPos, yPos, size);
    }
    
    public static Tree createAtPositionWithSizeAndDiscount(int xPos, int yPos, int size, int discount) {
        if (discount > 0 && discount <= 30 && discount % 5 == 0)
            return new Tree(xPos, yPos, size, discount);
        else {
            System.out.println("Oops, illegal discount detected! It will be chosen randomly!");
            return new Tree(xPos, yPos, size);
        }
    }
    
    
    public boolean isChoppedDown() {
        return isChoppedDown;
    }
    
    public boolean chopDown(int xPos, int yPos) {
        if (treeShape.contains(xPos, yPos))
            isChoppedDown = true;
        return isChoppedDown;
    }
    
    public void draw(Graphics2D g2d) {
        
        if (!isChoppedDown)
            g2d.fillPolygon(treeShape);
        else {
            Color tempColor = g2d.getColor();
            g2d.fillPolygon(choppedShape);              
            g2d.setColor(Color.black);
            g2d.drawString(discount + "%", (xPos + 5), (yPos + 5));
            g2d.setColor(tempColor);
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
            xPosNormal[i] = (int) coordinates[i].getX();
            yPosNormal[i] = (int) coordinates[i].getY();
            coordinates[i] = coordinates[i].rotateAroundPointBy(coordinates[0], -90);
            xPosChopped[i] = (int) coordinates[i].getX();
            yPosChopped[i] = (int) coordinates[i].getY();
        }
        
        treeShape = new Polygon(xPosNormal, yPosNormal, coordinates.length);
        choppedShape = new Polygon(xPosChopped, yPosChopped, coordinates.length);
        
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
  
}
