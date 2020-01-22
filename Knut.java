import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/** 
 * Basis-Panel stellt Grundfunktionen fuer den Aufbau interaktiver Anwendungen zur
 * Verfuegung.
 *  
 * Alle Mausereignisse koennen in einzelnen Methoden verarbeitet werden. 
 *  
 * @version 1.0
 */
public class Knut extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	
	private Moon moon = new Moon(50, 50, 25);
	
	private Star[] stars = {
	        new Star(100, 175), 
            new Star(200, 50),    
            new Star(325, 125), 
            new Star(400, 100), 
            new Star(475, 125), 
            new Star(550, 200), 
            new Star(600, 75), 
            new Star(650, 150)
            };

	private Tree[] trees = {
            new Tree(125, 350, 100), 
            new Tree (250, 300, 75), 
            new Tree(400, 225, 50), 
            new Tree(500, 325, 80), 
            new Tree(650, 225, 25)
            };

	/**
	 * Initialisierung des Panels und setzen des MouseListerns
	 * fuer die Verwendung von Maus-Ereignissen
	 */
	
	public Knut() {
		
		/* registriert Panel als MouseListener, so dass die jeweilige spezialisierte 
		 * Methode aufgerufen wird, wenn ein Mausereignis innerhalb des Panels ausgeloest 
		 * wird
		 */
	    
		this.addMouseListener(this);
		
		// Initialisiere Baeume, Mond und Sterne ...
		
	}
	
	/** 
	 * Zeichnen der Landschaft.
	 * 
	 * Umsetzung der Methode
	 * @see java.awt.Component#paint(java.awt.Graphics)
	 * 
	 * @param g Graphik-Kontext, auf dem die Landschaft gezeichnet wird
	 */
	
	public void paint(Graphics g) {
	    
		super.paint(g);
		
		g.setColor(moon.isDayTime() ? new Color(85, 200, 200) : Color.darkGray);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.white);
		g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
	
		//hier wird alles gezeichnet
		
		g.setColor(Color.yellow);
		moon.draw(g);
		
		if (!moon.isDayTime())
		    for (Star star : stars)
		        star.draw(g);
		
		for (Tree tree : trees) {
		    g.setColor(tree.isChoppedDown() ? Color.black : new Color(60, 40, 10));
		    tree.draw(g);
		}
		
	}
	
	
	/** 
	 * Aufloesung der x, y-Position, an der Mausbutton betaetigt wurde.
	 * 
	 * Umsetzung der Methode
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 * 
	 * @param e Maus-Ereignis, das ausgeloest wurde 
	 */
	
	public void mouseClicked(MouseEvent e) {
		int xPos, yPos;
		
		xPos = e.getX();
		yPos = e.getY();
		
		// hier sollte dann der Maus-Event entsprechend verarbeitet werden
		
		moon.switchTime(xPos, yPos);
		
		for (Tree tree : trees)
		    tree.chopDown(xPos, yPos);

		// nach jeder Veraenderung soll der Graphik-Kontext neu gezeichnet werden
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
