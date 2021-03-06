package knut;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

/**
 * Class that is able to store position, size and discount of a tree and draw it to a graphic context.
 *
 */
public class Tree implements Observer{
    
    /**
     * Specific color for day or night. Is the same for all instances.
     */
    private static final Color dayColor = new Color(35, 100, 65);
    private static final Color nightColor = new Color(20, 50, 30);   

    /**
     * Int values representing position, size and discount of a tree.
     */
    private final int xPos;
    private final int yPos;
    private final int size;
    private final int discount;
    
    /**
     * Color to draw tree with. Is the same for all instances.
     */
    private Color drawColor = dayColor;
    
    /**
     * Subject to observe.
     */
    private Subject subject;
    
    /**
     * Shapes of tree in normal and chopped-down-state and boolean representing chopped-down-state.
     */
    private Polygon treeShape;
    private Polygon choppedShape;
    private boolean isChoppedDown = false;
    
    public static class Builder {
        // Required parameters
        private final int xPos;
        private final int yPos;
        private final int size;
        
        // Optional parameters
        private int discount = getWeightedRandomDiscount();
        private Subject subject = null;
        
        public Builder(int xPos, int yPos, int size) {
            this.xPos = xPos;
            this.yPos = yPos;
            this.size = size;
        }
        
        public Builder discount(int discount) {
            if (discount % 5 == 0 && discount > 0 && discount <= 30)
                this.discount = discount;
            return this;
        }
        
        public Builder subject(Subject subject) {
            this.subject = subject;
            return this;
        }
        
        public Tree build() {
            return new Tree(this);
        }
    }
    
    private Tree(Builder builder) {
        this.xPos = builder.xPos;
        this.yPos = builder.yPos;
        this.size = builder.size;
        this.discount = builder.discount;
        this.subject = builder.subject;

        if (this.subject != null)
            subscribe(subject);
            
        initializeShapes();
    }
    
    /**
     * Tries to chop down a tree.
     * 
     * Checks if click at given position happend inside tree shape. 
     * If yes, sets chopped-down state true.
     * 
     * @param xPos An int representing x-coordinate of click.
     * @param yPos An int representing y-coordinate of click.
     * @return A boolean answering the question if a tree is chopped down.
     */
    public boolean chopDown(int xPos, int yPos) {
        boolean isClicked = treeShape.contains(xPos, yPos);
        if (isClicked)
            isChoppedDown = true;
        return isClicked;
    }
    
    /**
     * Draws tree shape on given graphic context.
     * 
     * Draws normal tree if not chopped down, chopped tree if chopped down.
     * If chopped down, reveals discount on graphic context, too.
     * 
     * @param g2d Given graphic context.
     */
    public void draw(Graphics2D g2d) {
        g2d.setColor(drawColor);
        if (!isChoppedDown)
            g2d.fillPolygon(treeShape);
        else {
            g2d.fillPolygon(choppedShape);              
            g2d.setColor(Color.black);
            g2d.drawString(discount + "%", (xPos + 5), (yPos + 5));
        }
    }
    
    /**
     * Initializes normal and chopped shape.
     * 
     * Initializes normal shape by creating points starting from the bottom-left 
     * and making a polygon of them. Chopped shape is initialized by turning the
     * points around starting point by 90 degrees to the left.
     */
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
        
        for (int i = 0; i < coordinates.length; i++) {      // scale and extract coordinates, turn, extract turned coordinates
            xPosNormal[i] = (int) coordinates[i].getX();
            yPosNormal[i] = (int) coordinates[i].getY();
            coordinates[i] = coordinates[i].rotateAroundPointBy(coordinates[0], -90);
            xPosChopped[i] = (int) coordinates[i].getX();
            yPosChopped[i] = (int) coordinates[i].getY();
        }
        
        treeShape = new Polygon(xPosNormal, yPosNormal, coordinates.length);
        choppedShape = new Polygon(xPosChopped, yPosChopped, coordinates.length);
        
    }
    
    /**
     * Chooses a valid random discount value.
     * 
     * Chances are 0.1 for 30%, 25% or 20% discount, 0.2 for 15% or 10% discount, 0.3 for 5% discount.
     * 
     * @return An int representing discount in percent.
     */
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

    @Override
    public void update() {
        drawColor = drawColor.equals(dayColor) ? nightColor : dayColor;
    }

    @Override
    public void subscribe(Subject s) {
        s.registerObserver(this);
    }

    @Override
    public void unsubscribe(Subject s) {
        s.removeObserver(this);
    }
  
}
