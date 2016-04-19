
public class Arc {

	MyCircle circle;  
    double width;
    Point p1;
    Point p2;
	public Arc(MyCircle c, double w, Point p1, Point p2) {
		this.circle = c;
		this.width = w;
		this.p1 = p1;
		this.p2 = p2;
	}
	public MyCircle getCircle() {
		return circle;
	}
	public void setCircle(MyCircle circle) {
		this.circle = circle;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public Point getP1() {
		return p1;
	}
	public void setP1(Point p1) {
		this.p1 = p1;
	}
	public Point getP2() {
		return p2;
	}
	public void setP2(Point p2) {
		this.p2 = p2;
	}

    
}
