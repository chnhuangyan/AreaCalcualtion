
public class Point {
	double x;
	double y;
	double angle;
	int[] parents={};
	
	public Point(double x, double y){
		this.x = x;
		this.y = y;
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

	public int[] getParents() {
		return parents;
	}

	public void setParents(int[] parents) {
		this.parents = parents;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	

}
