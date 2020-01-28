package knut;
import knut.Tree;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class Knut extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	
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
		
	public void paint(Graphics g) {
	    
	    Graphics2D g2d = (Graphics2D) g;
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    
		super.paint(g2d);		

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
		    tree.draw(g2d);
		
	}
	
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
	}
}
