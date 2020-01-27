import javax.swing.JFrame;

public class KnutApp {
    
	public static void main(String[] args) {
		
	    // Initialisierung des Frames
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(700, 500);
        
		Knut myDisplay = new Knut();
        jFrame.add(myDisplay);
        jFrame.setVisible(true);
	}
}
