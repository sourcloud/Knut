package knut;
<<<<<<< HEAD

=======
>>>>>>> bc86e3fbc8d55be6747035d3eee1feb9002d2c2d
/**
 * Class that stores two double values representing a point in R².
 * 
 * Point class provides immutable Objects representing points in a two-dimensional 
 * coordinate system, equipped with basic linear algebra functionality.
<<<<<<< HEAD
 * 
=======
>>>>>>> bc86e3fbc8d55be6747035d3eee1feb9002d2c2d
 */
public class Point {
	
    /**
     * Double values that represent coordinates of a point.
     */  
    private final double xPos;
    private final double yPos;
    
    /**
     * Constructs new point at a given x- and y-coordinate.
     * 
     * @param xPos A double representing x-coordinate.
     * @param yPos A double representing y-coordinate.
     */  
    private Point(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    /**
     * Calls constructor and returns new instance of point.
     * 
     * @param xPos An int representing the x-coordinate.
     * @param yPos An int representing the y-coordinate.
     * @return New instance of Point with given x- and y-coordinates.
     */    
    public static Point createAtPosition(double xPos, double yPos) {
        return new Point(xPos, yPos);
    }
    
    /**
     * Returns x-coordinate.
     * 
     * @return A double representing x-coordinate.
     */
    public double getX() { 
    	return xPos; 
    }
    
    /**
     * Returns y-coordinate.
     * 
     * @return A double representing y-coordinate.
     */   
    public double getY() { 
    	return yPos; 
    }
    
    /**
     * Calculates sum of two points.
     * 
     * Calculates sum of this and other instance of point by adding the corresponding x- and y-coordinates.
     * 
     * @param other Other instance of Point.
     * @return A new instance of Point that's x- and y-coordinates are the sum of two Point's coordinates.
     */   
    public Point plus(Point other) {
        return new Point(this.xPos + other.xPos,
                         this.yPos + other.yPos);
    }
    
    /**
     * Calculates difference of two points.
     * 
     * Calculates difference of this and other instance of point by subtracting corresponding x- and y-coordinates.
     * 
     * @param other Other instance of Point.
     * @return A new instance of Point that's x- and y-coordinates are the difference of two Point's coordinates.
     */    
    public Point minus(Point other) {
        return new Point(this.xPos - other.xPos,
                         this.yPos - other.yPos);
    }
    
    /**
     * Scales point by factor.
     * 
     * Scales this point by multplying x- and y-coordinates with a given factor.
     * 
     * @param factor A double representing scaling factor.
     * @return A new instance of Point that's x- and y-coordinates are the product of x- and y-coordinates and given factor.
     */    
    public Point timesFactor(double factor) {
        return new Point(this.xPos * factor,
                         this.yPos * factor);
    }
    
    /**
     * Rotates point around origin by a given degree. 
     * 
     * Rotates point around origin by a given degree using the R² turning matrix
     * [cos -sin]
     * [sin  cos] 
     * 
     * @param deg A double representing the degree to turn by.
     * @return A new instance of Point that's x- and y-coordinates are the product of this multiplied with R² turning matrix.
     */    
    public Point rotateAroundOriginBy(double deg) {
        double rad = Math.toRadians(deg);
        return new Point(Math.round(xPos * Math.cos(rad) - yPos * Math.sin(rad)),
                         Math.round(xPos * Math.sin(rad) + yPos * Math.cos(rad)));
    }
    
    /**
     * Rotates point around given point by given degree.
     * 
     * Rotates point around a given point by a given degree by subtracting given point, rotating and adding given point again.
     * 
     * @param other Other instance of Point.
     * @param deg A double representing the degree to turn by.
     * @return A new instance of Point that's x- and y-coordinates are the coordinates of this turned by given degree around given Point.
     */    
    public Point rotateAroundPointBy(Point other, double deg) {
        return this.minus(other).rotateAroundOriginBy(deg).plus(other);
    }    
    
    /**
     * Calculates cross-product of two points.
     * 
     * Calculates cross-product of this and other instance of point.
     * 
     * @param other Other instance of Point.
     * @return A new instance of Point that's x- and y-coordinates are the cross-product of two instances of Point.
     */    
    public double cross(Point other) {
        return this.xPos * other.yPos - this.yPos * other.xPos;
    }
    
    /**
     * Calculates scalar-product of two points.
     * 
     * Calculates scalar-product of this and other instance of point 
     * by multiplying corresponding x- and y-coordinates and adding them afterwards.
     * 
     * @param other Other instance of Point.
     * @return A new instance of Point that's x- and y-coordinates are the vector product of two instances of Point.
     */    
    public double dot(Point other) {
        return this.xPos * other.xPos + this.yPos * other.yPos;
    }
    
    /**
     * Calculates length of Point.
     * 
<<<<<<< HEAD
     * Calculates length of this by using Pythagorean theorem. Length is the representation of distance between
     * a point and origin of coordinate system.
     * 
=======
>>>>>>> bc86e3fbc8d55be6747035d3eee1feb9002d2c2d
     * @return A double representing the length of this Point.
     */    
    public double length() {
        return Math.sqrt(this.dot(this));
    }
    
    /**
     * Calculates distance between two points.
     * 
     * Calculates distance between this and other instance of point using Pythagorean theorem.
     * 
     * @param other Other instance of Point.
     * @return A double representing the distance between two Points.
     */       
    public double distanceTo(Point other) {
        return this.minus(other).length();
    }
    
<<<<<<< HEAD
    @Override 
=======
    @Override
>>>>>>> bc86e3fbc8d55be6747035d3eee1feb9002d2c2d
    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (!(other instanceof Point))
            return false;
        Point p = (Point) other;
        return Double.compare(p.xPos, this.xPos) == 0
            && Double.compare(p.xPos, this.xPos) == 0;
    }

    @Override
    public int hashCode() {
        return 31 * Double.hashCode(xPos) + Double.hashCode(yPos);
    }
    
    @Override
    public String toString() {
        return "(" + xPos + ", " + yPos + ")";
    }
    
}