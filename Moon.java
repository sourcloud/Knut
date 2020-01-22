import java.awt.Graphics;

public class Moon {

    private int xPos;
    private int yPos;
    private int radius;
    private int diameter;
    private boolean dayTime;
    
    public Moon() {
        this.dayTime = true;
    }
    
    public Moon(int xPos, int yPos, int radius) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;   
        this.diameter = this.radius * 2;
        this.dayTime = true;
    }
    
    public void setSizeAndLocation(int xPos, int yPos, int radius) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;   
        this.diameter = this.radius * 2;
    }
    
    public boolean isDayTime() {
        return dayTime;
    }
    
    public boolean switchTime(int xPos, int yPos) {
        boolean isClicked = (xPos - this.xPos >= 0 && xPos - this.xPos <= diameter && yPos - this.yPos >= 0 && yPos - this.yPos <= diameter);
        if (isClicked)
            dayTime = !dayTime;
        return isClicked;
    }
    
    public void draw(Graphics g) {
        if (dayTime)
            g.drawOval(xPos, yPos, diameter, diameter);
        else
            g.fillOval(xPos, yPos, diameter, diameter);
    }
}
