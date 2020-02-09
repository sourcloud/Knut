package knut;
import javax.swing.JFrame;

/**
 * Starts Knut application.
 * 
 * Application will be shown in a standard window of size 700x500 pixels.
 * 
 */
public class KnutApp {
    
    /**
     * Starts application and shows window.
     * @param args Will not be interpreted.
     */
	public static void main(String[] args) {
		
	    // Initializing Frame
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(700, 500);

		Knut myDisplay = new Knut();
        jFrame.add(myDisplay);
        jFrame.setVisible(true);
	}
}
