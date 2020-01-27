import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Knut extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	
	private Moon moon = new Moon(50, 50, 25);
	
	private Star[] stars = {
	        Star.createAtPosition(100, 175), 
            Star.createAtPosition(200, 50),    
            Star.createAtPosition(325, 125), 
            Star.createAtPosition(400, 100), 
            Star.createAtPosition(475, 125), 
            Star.createAtPosition(550, 200), 
            Star.createAtPosition(600, 75), 
            Star.createAtPosition(650, 150)
            };

	private Tree[] trees = {
            Tree.createAtPositionWithSize(100, 375, 100), 
            Tree.createAtPositionWithSize(225, 300, 75), 
            Tree.createAtPositionWithSize(375, 275, 50), 
            Tree.createAtPositionWithSize(475, 350, 80), 
            Tree.createAtPositionWithSize(625, 250, 35)
            };
	
	public Knut() {
	    
		this.addMouseListener(this);
		
		// Initialisiere Baeume, Mond und Sterne ...
		
	}
	
	private void drawSky(Graphics g) {
	    g.setColor(moon.isDayTime() ? new Color(150, 230, 230) : Color.darkGray);
        g.fillRect(0, 0, getWidth(), getHeight() / 2);      
	}
	
	private void drawGround(Graphics g) {
        g.setColor(moon.isDayTime() ? new Color(65, 220, 110) : new Color(35, 100, 65));
        g.fillRect(0, getHeight() / 2, getWidth(), getHeight());  
	}
	
	private void drawHorizon(Graphics g) {
        g.setColor(moon.isDayTime() ? Color.white : Color.black);
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
	}
	
	public void paint(Graphics g) {
	    
		super.paint(g);		

        drawSky(g);
        drawGround(g);
        drawHorizon(g);
		
		g.setColor(Color.yellow);
		moon.draw(g);
		
		if (!moon.isDayTime())
		    for (Star star : stars)
		        star.draw(g);
		
		g.setColor(moon.isDayTime() ? new Color(35, 100, 65) : new Color(20, 50, 30));
		for (Tree tree : trees) {
		    tree.draw(g);
		}
		
	}
	
	public void mouseClicked(MouseEvent e) {
		int xPos, yPos;
		
		xPos = e.getX();
		yPos = e.getY();

		moon.switchTime(xPos, yPos);
		
		for (Tree tree : trees)
		    tree.chopDown(xPos, yPos);

		this.repaint();
	}
	
	/** Faengt Mouse-Event ab, ohne ihn weiter zu verarbeiten
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	
	public void mouseEntered(MouseEvent e) { }
	
	/** Faengt Mouse-Event ab, ohne ihn weiter zu verarbeiten
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	
	public void mouseExited(MouseEvent e) { }
	
	/** Faengt Mouse-Event ab, ohne ihn weiter zu verarbeiten
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	
	public void mousePressed(MouseEvent e) { }

	/** Faengt Mouse-Event ab, ohne ihn weiter zu verarbeiten
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	
	public void mouseReleased(MouseEvent e) { }
}
