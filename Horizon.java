package knut;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Is able to manage color of horizon and to draw it to given graphic context.
 *
 */
public final class Horizon implements Observer {
    
    /**
     * Specific colors for day and night.
     */    
    private final Color dayColor = Color.white;
    private final Color nightColor = Color.black;
    
    /**
     * Makes sure there is only one instance of Horizon class.
     */
    private static Horizon instance = new Horizon();
    
    /**
     * Color to draw with.
     */
    private Color drawColor = dayColor;    
    
    /**
     * Private constructor without funcitonality avoids instantiation.
     */
    private Horizon() { }
    
    /**
     * Returns only instance of class.
     * 
     * @return Only instance of class.
     */
    public static Horizon getInstance() {
        return instance;
    }
    
    /**
     * Draws horizon on given graphic context.
     * 
     * @param g2d Graphic context to draw on.
     * @param fieldWidth An int representing frame width.
     * @param fieldHeight An int representing frame height.
     */
    public void draw(Graphics2D g2d, int fieldWidth, int fieldHeight) {
        g2d.setColor(drawColor);
        g2d.drawLine(0, fieldHeight / 2, fieldWidth, fieldHeight / 2);
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
