
public class Point {

    private final double xPos;
    private final double yPos;
    
    private Point(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    public static Point createAtPosition(double xPos, double yPos) {
        return new Point(xPos, yPos);
    }
    
    public double xPos() { return xPos; }
    public double yPos() { return yPos; }
    
    public Point plus(Point other) {
        return new Point(this.xPos + other.xPos,
                         this.yPos + other.yPos);
    }
    
    public Point minus(Point other) {
        return new Point(this.xPos - other.xPos,
                         this.yPos - other.yPos);
    }
    
    public Point timesFactor(double factor) {
        return new Point(this.xPos * factor,
                         this.yPos * factor);
    }
    
    public Point rotateAroundOriginBy(double deg) {
        double rad = Math.toRadians(deg);
        return new Point(Math.round(xPos * Math.cos(rad) - yPos * Math.sin(rad)),
                         Math.round(xPos * Math.sin(rad) + yPos * Math.cos(rad)));
    }
    
    public Point rotateAroundPointBy(Point other, double deg) {
        return this.minus(other).rotateAroundOriginBy(deg).plus(other);
    }
    
    public double times(Point other) {
        return this.xPos * other.xPos + this.yPos * other.yPos;
    }
    
    public double cross(Point other) {
        return this.xPos * other.yPos - other.xPos * this.yPos;
    }
       
    public double distanceTo(Point other) {
        return Math.sqrt(Math.pow(this.xPos - other.xPos, 2) 
                       + Math.pow(this.yPos - other.yPos, 2));
    }
    
    public double length() {
        return Math.sqrt(xPos * xPos + yPos * yPos);
    }
    
    @Override
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
