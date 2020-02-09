package knut;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Is able to manage color of ground and to draw it to given graphic context.
 *
 */
public final class Ground implements Observer {

    /**
     * Specific colors for day and night.
     */    
    private final Color dayColor = new Color(65, 220, 110);
    private final Color nightColor = new Color(35, 100, 65);
    
    /**
     * Makes sure there is only one instance of the class.
     */
    private static Ground instance = new Ground();
    
    /**
     * Color to draw with.
     */
    private Color drawColor = dayColor;    
  
    /**
     * Private constructor without funcitonality avoids instantiation.
     */
    private Ground() { }
    
    /**
     * Returns only instance of class.
     * @return Only instance of class.
     */
    public static Ground getInstance() {
        return instance;
    }
    
    /**
     * Draws ground on given graphic context.
     * 
     * @param g2d Graphic context to draw on.
     * @param fieldWidth An int representing frame width.
     * @param fieldHeight An int representing frame height.
     */
    public void draw(Graphics2D g2d, int fieldWidth, int fieldHeight) {
        g2d.setColor(drawColor);
        g2d.fillRect(0, fieldHeight / 2, fieldWidth, fieldHeight);
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
