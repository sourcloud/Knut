package knut;
import java.awt.Graphics2D;

public class Moon {

    private final int xPos;
    private final int yPos;
    private final int radius;
    private boolean dayTime;
    
    private Moon(int xPos, int yPos, int radius) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;   
        this.dayTime = true;
    }
    
    public static Moon createAtPositionWithRadius(int xPos, int yPos, int radius) {
        return new Moon(xPos, yPos, radius);
    }
    
    public boolean isShining() {
        return !dayTime;
    }
    
    public boolean switchTime(int xPos, int yPos) {
        Point centerPoint = Point.createAtPosition(this.xPos, this.yPos);
        Point clickPoint = Point.createAtPosition(xPos, yPos);
        
        boolean isClicked = (centerPoint.distanceTo(clickPoint) <= radius);
        if (isClicked)
            dayTime = !dayTime;
        return isClicked;
    }
    
    public void draw(Graphics2D g2d) {
        int diameter = 2 * radius;
        if (dayTime)
            g2d.drawOval(xPos - radius, yPos - radius, diameter, diameter);     // circle with center at (xPos, yPos)
        else
            g2d.fillOval(xPos - radius, yPos - radius, diameter, diameter);
    }
}
