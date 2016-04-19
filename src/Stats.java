
public class Stats{
	boolean stats = false;
	public boolean isStats() {
		return stats;
	}
	public void setStats(boolean stats) {
		this.stats = stats;
	}
	double area;
	double arcArea;
	double polygonArea;
	Arc[] arcs;
	Point[] innerPoints;
	Point[] intersectionPoints;
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public double getArcArea() {
		return arcArea;
	}
	public void setArcArea(double arcArea) {
		this.arcArea = arcArea;
	}
	public double getPolygonArea() {
		return polygonArea;
	}
	public void setPolygonArea(double polygonArea) {
		this.polygonArea = polygonArea;
	}
	public Arc[] getArcs() {
		return arcs;
	}
	public void setArcs(Arc[] arcs) {
		this.arcs = arcs;
	}
	public Point[] getInnerPoints() {
		return innerPoints;
	}
	public void setInnerPoints(Point[] innerPoints) {
		this.innerPoints = innerPoints;
	}
	public Point[] getIntersectionPoints() {
		return intersectionPoints;
	}
	public void setIntersectionPoints(Point[] intersectionPoints) {
		this.intersectionPoints = intersectionPoints;
	}
	
	
}