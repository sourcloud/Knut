package knut;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Moon {
    // Der rufende
    /*interface HelloListener {
        void someoneSaidHello();
    }

    // Rufmanager
    class Initiater {
        private List<HelloListener> listeners = new ArrayList<HelloListener>();

        public void addListener(HelloListener toAdd) {
            listeners.add(toAdd);
        }

        public void sayHello() {
            System.out.println("Hello!!");

            // Notify everybody that may be interested.
            for (HelloListener hl : listeners)
                hl.someoneSaidHello();
        }
    }

    // Antwort-Rufer
    class Responder implements HelloListener {
        @Override
        public void someoneSaidHello() {
            System.out.println("Hello there...");
        }
    }*/

    // RufMain
    /*class Test {
        public static void main(String[] args) {
            Initiater initiater = new Initiater();
            Responder responder = new Responder();

            initiater.addListener(responder);

            initiater.sayHello();  // Prints "Hello!!!" and "Hello there..."
        }
    }*/

    private final int xPos;
    private final int yPos;
    private final int radius;
    private boolean dayTime;

    /* --- Event --- */
    // Eventvorlage / Rufvorlage
    // (knut.MoonDayNightListener)

    // Angewendete EventListener
    private List<MoonDayNightListener> applyedListeners;

    private void triggerEventDayNight() {
        // Durchlaufe alle wartende Listeners
        for (MoonDayNightListener listener : this.applyedListeners)
            listener.onChange(this.dayTime);
    }


    public void addDayNightListener(MoonDayNightListener toAddListener) {
        this.applyedListeners.add(toAddListener);
    }

    /* ---- ---- */
    
    private Moon(int xPos, int yPos, int radius) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;   
        this.dayTime = true;
        this.applyedListeners = new ArrayList();
    }
    
    public static Moon createAtPositionWithRadius(int xPos, int yPos, int radius) {
        return new Moon(xPos, yPos, radius);
    }
    
    public boolean isShining() {
        return !dayTime;
    }

    //public boolean switchTime(int xPos, int yPos) {
    public boolean g2dClick(int xPos, int yPos) {
        Point centerPoint = Point.createAtPosition(this.xPos, this.yPos);
        Point clickPoint = Point.createAtPosition(xPos, yPos);
        
        boolean isClicked = (centerPoint.distanceTo(clickPoint) <= radius);
        if (isClicked)
            dayTime = !dayTime;
            this.triggerEventDayNight(); // Trigger Repain @Knut

        return isClicked;
    }
    
    public void draw(Graphics2D g2d) {
        int diameter = 2 * radius,
            objDrawPointX = xPos - radius,
            objDrawPointY = yPos - radius;

        Color backgroundColor;

        if (dayTime) {
            backgroundColor = new Color(35, 100, 65);
            g2d.drawOval(objDrawPointX, objDrawPointY, diameter, diameter);     // circle with center at (xPos, yPos)
        } else {
            backgroundColor = new Color(20, 50, 30);
            g2d.fillOval(objDrawPointX, objDrawPointY, diameter, diameter);
        }

        g2d.setColor(backgroundColor);
    }
}
