
public class MyCircle {

	double x;
	double y;
	double radius;
	String lable;
	
	public MyCircle(double x, double y, double radius){
		this.x = x;
		this.y = y;
		this.radius =radius;
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public Point getPoint(){
		Point p = new Point(x,y);
		return p;
	}
	
}

