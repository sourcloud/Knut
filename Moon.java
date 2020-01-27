import java.awt.Graphics;

public class Moon {

    private final int xPos;
    private final int yPos;
    private final int radius;
    private boolean dayTime;
    
    public Moon(int xPos, int yPos, int radius) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;   
        this.dayTime = true;
    }
    
    public boolean isDayTime() {
        return dayTime;
    }
    
    public boolean switchTime(int xPos, int yPos) {
        int diameter = 2 * radius;
        boolean isClicked = (xPos - this.xPos >= 0 && xPos - this.xPos <= diameter && yPos - this.yPos >= 0 && yPos - this.yPos <= diameter);
        if (isClicked)
            dayTime = !dayTime;
        return isClicked;
    }
    
    public void draw(Graphics g) {
        int diameter = 2 * radius;
        if (dayTime)
            g.drawOval(xPos, yPos, diameter, diameter);
        else
            g.fillOval(xPos, yPos, diameter, diameter);
    }
}
