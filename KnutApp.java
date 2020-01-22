import javax.swing.JFrame;

/**
 * Starter der Knut-Applikation.
 * 
 * Applikation wird in einem Standard-Fenster der Groesse 700 x 500 angezeigt.
 *
 *
 */
public class KnutApp {
    
	/**
	 * Starten der Applikation und Anzeige des Fensters
	 * 
	 * @param args wird nicht interpretiert
	 */
    
	public static void main(String[] args) {
		// Initialisierung des Frames
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(700, 500);
        
        /* Instanziierung der eigentlichen Anzeige, 
         * die in in der Klasse Knut definiert ist.
         * Knut ist als JPanel eine Darstellungsflaeche,
         * in der die Landschaft gezeichnet wird.
         */
        
		Knut myDisplay = new Knut();
        jFrame.add(myDisplay);
        jFrame.setVisible(true);
	}
}
