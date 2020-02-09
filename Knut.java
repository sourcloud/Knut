package knut;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 * Base panel provides interactive application.
 * 
 * Draws scenario with moon, stars and trees. Moon and trees are interactive elements
 * that will react to clicking.
 * 
 */
public class Knut extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	
	private Sky sky;
	private Moon moon;	
	private Ground ground;
	private Horizon horizon;
	
	private Tree[] trees;
	private Star[] stars;
	
	/**
	 * Constructs new instance of knut by initializing mouse listener, moon,
	 * and arrays managing trees and stars.
	 */
	public Knut() {
	    
		this.addMouseListener(this);
		
		this.moon = Moon.createAtPositionWithRadius(75, 75, 25);
		
		this.trees = new Tree[] {
		        new Tree.Builder(100, 375, 100).subject(moon).build(),
	            new Tree.Builder(225, 300, 75).subject(moon).build(), 
	            new Tree.Builder(375, 275, 50).subject(moon).build(), 
	            new Tree.Builder(475, 350, 80).subject(moon).build(), 
	            new Tree.Builder(625, 250, 35).subject(moon).discount(20).build(),
        };
		
		this.stars = new Star[] {
	            new Star.Builder(100, 175).subject(moon).build(), 
	            new Star.Builder(200, 50).subject(moon).build(),    
	            new Star.Builder(325, 125).subject(moon).build(), 
	            new Star.Builder(400, 100).subject(moon).build(), 
	            new Star.Builder(475, 125).subject(moon).build(), 
	            new Star.Builder(550, 200).subject(moon).build(), 
	            new Star.Builder(600, 75).subject(moon).build(), 
	            new Star.Builder(650, 150).subject(moon).build(),
	    };
		
		this.sky = Sky.getInstance();
		this.ground = Ground.getInstance();
		this.horizon = Horizon.getInstance();
		
		moon.registerObserver(sky);
		moon.registerObserver(ground);
		moon.registerObserver(horizon);
		
	}
		
	/**
	 * Draws background and foreground on graphics context.
	 * 
	 * Background consists of sky, ground and horizon.
	 * Foreground consists of moon, stars and trees. 
	 */
	public void paint(Graphics g) {
	    
	    Graphics2D g2d = (Graphics2D) g;
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    
		super.paint(g2d);		

		sky.draw(g2d, getWidth(), getHeight());
        ground.draw(g2d, getWidth(), getHeight());
        horizon.draw(g2d, getWidth(), getHeight());
		
        moon.draw(g2d);

	    for (Star star : stars)
	        star.draw(g2d);
		
		for (Tree tree : trees)
		    tree.draw(g2d);
		
	}
	
	/** Intercepts mouse event. 
	 * 
	 * Checks if moon or one of the trees is clicked and triggers according events.
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */	
	public void mouseClicked(MouseEvent e) {
	    
		int xPos = e.getX();
		int yPos = e.getY();
		
		for (Tree tree : trees)
		    if (tree.chopDown(xPos, yPos))
		        break;
		
		if (moon.isClicked(xPos, yPos))
		    moon.switchShiningState();

		this.repaint();
	}
	
	/** Intercepts mouse event without further processing.
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */	
	public void mouseEntered(MouseEvent e) {
	    // left blank intentionally
	}
	
	/** Intercepts mouse event without further processing.
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */	
	public void mouseExited(MouseEvent e) { 
	    // left blank intentionally
	}
	
	/** Intercepts mouse event without further processing.
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */	
	public void mousePressed(MouseEvent e) { 
	    // left blank intentionally.
	}

	/** Intercepts mouse event without further processing.
	 * 
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */	
	public void mouseReleased(MouseEvent e) { 
	    // left blank intentionally.
	}
}
