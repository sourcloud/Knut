import java.awt.Graphics;
import java.awt.Polygon;

public class Tree {

    private int xPos;
    private int yPos;
    private int size;
    private int discount;
    private boolean isChoppedDown;
    private Polygon treeShape;
    private Polygon choppedShape;
    
    public Tree(int xPos, int yPos, int size) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.size = size;
        this.discount = getWeightedRandomDiscount();
        this.isChoppedDown = false;
        initializeShapes();
    }
    
    public Tree(int xPos, int yPos, int size, int discount) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.size = size;
        this.discount = discount;
        this.isChoppedDown = false;
    }
    
    private int getWeightedRandomDiscount() {
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
            g.drawString(Integer.toString(discount) + "%", xPos, yPos);
        }
    }
    
    private void initializeShapes() {
        
        int xRight = xPos + size;
        int xMiddle = xPos + (size/2);
        int xOneThird = xPos + (size / 3);
        int xTwoThirds = xPos + (size * 2 / 3);
        int xOneFifth = xPos + (size / 5);
        int xTwoFifth = xPos + (size * 2 / 5);
        int xThreeFifth = xPos + (size * 3 / 5);
        int xOneEightth = xPos + (size * 1 / 8);
        int xSevenEightth = xPos + (size * 7 / 8);
        int xOneTenth = xPos + (size / 10);
        int xOneNinth = xPos + (size * 1 / 20);
        int xEightNinth = xPos + (size * 19 / 20);     
        
        int yTop = yPos - size;
        int yMiddle = yPos - (size / 2);
        int yOneThird = yPos - (size / 3);
        int yTwoThirds = yPos - (size * 2 / 3);
        int yOneFifth = yPos - (size / 5);
        int yTwoFifth = yPos - (size * 2 / 5);
        int yThreeFifth = yPos - (size * 3 / 5);
        int yOneEighth = yPos - (size / 8);
        int ySevenEighth = yPos - (size * 7 / 8);
        int yOneTenth = yPos - (size / 10);
        int yOneNinth = yPos - (size * 19 / 20);
        int yEightNinth = yPos - (size * 1 / 20);
        
        int[] xCoordinatesTree = {xPos, xTwoFifth, xTwoFifth, xThreeFifth, xThreeFifth, xRight, xTwoThirds, xEightNinth, xTwoThirds, xSevenEightth, xMiddle, xOneEightth, xOneThird, xOneNinth, xOneThird};
        int[] yCoordinatesTree = {yPos, yOneTenth, yPos, yPos, yOneTenth, yPos, yOneThird, yOneFifth, yTwoThirds, yMiddle, yTop, yMiddle, yTwoThirds, yOneFifth, yOneThird};

        int[] xCoordinatesChopped = {xPos, xPos, xTwoThirds, xOneThird};
        int[] yCoordinatesChopped = {yPos, yTop, yOneThird, yOneNinth,}; 
        
        treeShape = new Polygon(xCoordinatesTree, yCoordinatesTree, xCoordinatesTree.length);
        choppedShape = new Polygon(xCoordinatesChopped, yCoordinatesChopped, xCoordinatesChopped.length);
       
    }
  
}
