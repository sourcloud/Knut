package knut;
<<<<<<< HEAD
=======
import knut.Tree;

import java.awt.Color;
>>>>>>> bc86e3fbc8d55be6747035d3eee1feb9002d2c2d
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

<<<<<<< HEAD
/**
 * Base panel provides interactive application.
 * 
 * Draws scenario with moon, stars and trees. Moon and trees are interactive elements
 * that will react to clicking.
 * 
 */
=======
>>>>>>> bc86e3fbc8d55be6747035d3eee1feb9002d2c2d
public class Knut extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	
<<<<<<< HEAD
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
=======
	private Moon moon;	
	private Star[] stars;
	//private Tree[] trees;
	
	public Knut() {
		Knut self = this;
		this.addMouseListener(this);
		
		this.moon = Moon.createAtPositionWithRadius(75, 75, 25);
		this.moon.addDayNightListener(new MoonDayNightListener() {
			@Override
			public void onChange(boolean newDayNightState) {
				self.repaint();
			}
		});
		
		/*this.trees = new Tree[] {
	            Tree.createAtPositionWithSize(100, 375, 100), 
	            Tree.createAtPositionWithSize(225, 300, 75), 
	            Tree.createAtPositionWithSize(375, 275, 50), 
	            Tree.createAtPositionWithSize(475, 350, 80), 
	            Tree.createAtPositionWithSize(625, 250, 35)
        };*/
		Tree.setTreeCount(6);
		
		this.stars = new Star[] {
	            Star.createAtPosition(100, 175), 
	            Star.createAtPosition(200, 50),    
	            Star.createAtPosition(325, 125), 
	            Star.createAtPosition(400, 100), 
	            Star.createAtPosition(475, 125), 
	            Star.createAtPosition(550, 200), 
	            Star.createAtPosition(600, 75), 
	            Star.createAtPosition(650, 150)
	    };
		
	}
		
>>>>>>> bc86e3fbc8d55be6747035d3eee1feb9002d2c2d
	public void paint(Graphics g) {
	    
	    Graphics2D g2d = (Graphics2D) g;
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    
		super.paint(g2d);		

<<<<<<< HEAD
		sky.draw(g2d, getWidth(), getHeight());
        ground.draw(g2d, getWidth(), getHeight());
        horizon.draw(g2d, getWidth(), getHeight());
		
        moon.draw(g2d);

	    for (Star star : stars)
	        star.draw(g2d);
		
		for (Tree tree : trees)
=======
        drawSky(g2d);
        drawGround(g2d);
        drawHorizon(g2d);
		
		g2d.setColor(Color.yellow);
		moon.draw(g2d);
		
		/*if (moon.isShining())
		    for (Star star : stars)
		        star.draw(g2d);
		
		g2d.setColor(moon.isShining()	 ? new Color(20, 50, 30) : new Color(35, 100, 65));*/

		//for (Tree tree : trees)
		for (Tree tree : Tree.instanceMemoryList)
>>>>>>> bc86e3fbc8d55be6747035d3eee1feb9002d2c2d
		    tree.draw(g2d);
		
	}
	
<<<<<<< HEAD
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
=======
	public void mouseClicked(MouseEvent e) {
		
		int xPos = e.getX();
		int yPos = e.getY();

		// Leite klick Event an Childs weiter...
		moon.g2dClick(xPos, yPos);
		//moon.switchTime(xPos, yPos);

		Tree hoveredTree = Tree.getHoveredTree(xPos, yPos);
		// #for (Tree tree : trees)
		// #    tree.chopDown(xPos, yPos);

		// Wenn Tree != null
		if (hoveredTree != null) {
			hoveredTree.chopDown(xPos, yPos);
		}

		this.repaint();
	}

	public boolean objectInBounds(int xPos, int yPos) {
		return false;
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
	
	private void drawSky(Graphics2D g2d) {
	    Color skyColor = moon.isShining() ? Color.darkGray : new Color(150, 230, 230);
	    g2d.setColor(skyColor);
	    g2d.fillRect(0, 0, getWidth(), getHeight() / 2);      
	}
	
	private void drawGround(Graphics2D g2d) {
	    Color groundColor = moon.isShining() ? new Color(35, 100, 65) : new Color(65, 220, 110);	       
	    g2d.setColor(groundColor);
	    g2d.fillRect(0, getHeight() / 2, getWidth(), getHeight());  
	}
	
	private void drawHorizon(Graphics2D g2d) {
	    Color horizonColor = moon.isShining() ? Color.black : Color.white;
	    g2d.setColor(horizonColor);
	    g2d.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
>>>>>>> bc86e3fbc8d55be6747035d3eee1feb9002d2c2d
	}
}
