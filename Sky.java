package knut;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Is able to manage color of sky and to draw it to given graphic context.
 *
 */
public final class Sky implements Observer {

    /**
     * Specific colors for day and night.
     */    
    private final Color dayColor = new Color(150, 230, 230);
    private final Color nightColor = Color.darkGray;
   
    /**
     * Makes sure there is only one instance of the class.
     */
    private static Sky instance = new Sky();
    
    /**
     * Color to draw with.
     */
    private Color drawColor = dayColor;
    
    
    /**
     * Private constructor without funcitonality avoids instantiation.
     */
    private Sky() { };
    
    /**
     * Returns only instance of Sky class.
     * 
     * @return Only instance of Sky class.
     */
    public static Sky getInstance() {
        return instance;
    }
    
    
    /**
     * Draws sky on given graphic context.
     * 
     * @param g2d Graphic context to draw on.
     * @param fieldWidth An int representing frame width.
     * @param fieldHeight An int representing frame height.
     */
    public void draw(Graphics2D g2d, int fieldWidth, int fieldHeight) {
        g2d.setColor(drawColor);
        g2d.fillRect(0, 0, fieldWidth, fieldHeight / 2);
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
