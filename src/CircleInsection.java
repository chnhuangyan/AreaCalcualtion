import java.util.Arrays;
import java.util.Comparator;

public class CircleInsection {

	final static double SMALL = 1e-10;

	public static double getArea(MyCircle[] circles, Stats stats) {

		Point[] intersectionPoints = getIntersectionPoints(circles);
		Point[] innerPoints = getInnerPoints(intersectionPoints, circles);

		double arcArea = 0, polygonArea = 0;
		Arc[] arcs = {};

		if (innerPoints.length > 1) {
			Point center = getCenter(innerPoints);

			for (int i = 0; i < innerPoints.length; ++i) {
				Point p = innerPoints[i];
				p.angle = Math.atan2(p.x - center.x, p.y - center.y);
			}

			Arrays.sort(innerPoints, new Comparator<Point>() {
				@Override
				public int compare(Point p1, Point p2) {
					return Double.compare(p2.angle, p1.angle);
				}
			});
			// iterate over all points, get arc between the points
			// and update the areas

			Point p2 = innerPoints[innerPoints.length - 1];
			for (int i = 0; i < innerPoints.length; ++i) {
				Point p1 = innerPoints[i];
				polygonArea += (p2.x + p1.x) * (p1.y - p2.y);
				Point midPoint = new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);

				Arc arc = null;

				for (int j = 0; j < p1.parents.length; ++j) {
					if (Arrays.binarySearch(p2.parents, p1.parents[j]) > -1) {
						MyCircle circle = circles[p1.parents[j]];
						double a1 = Math.atan2(p1.x - circle.x, p1.y - circle.y);
						double a2 = Math.atan2(p2.x - circle.x, p2.y - circle.y);

						double angleDiff = (a2 - a1);
						if (angleDiff < 0) {
							angleDiff += 2 * Math.PI;
						}
						double a = a2 - angleDiff / 2;
						double width = getDistance(midPoint, new Point(circle.x + circle.radius * Math.sin(a),
								circle.y + circle.radius * Math.cos(a)));

						if ((arc == null) || (arc.width > width)) {
							arc = new Arc(circle, width, p1, p2);
						}
					}
				}
				arcs = (Arc[])addElement(arcs, arc);
				arcArea += getCircleArea(arc.circle.radius, arc.width);
				p2 = p1;
			}
		} else {
			// no intersection points, is either disjoint - or is completely
			// overlapped. figure out which by examining the smallest circle
			MyCircle smallest = circles[0];
			for (int i = 1; i < circles.length; ++i) {
				if (circles[i].radius < smallest.radius) {
					smallest = circles[i];
				}
			}

			// make sure the smallest circle is completely contained in all
			// the other circles
			boolean disjoint = false;
			for (int i = 0; i < circles.length; ++i) {
				if (getDistance(circles[i].getPoint(), smallest.getPoint()) > Math
						.abs(smallest.radius - circles[i].radius)) {
					disjoint = true;
					break;
				}
			}
			if (disjoint) {
				arcArea = polygonArea = 0;

			} else {
				arcArea = smallest.radius * smallest.radius * Math.PI;
				arcs = (Arc[])addElement(arcs,
						new Arc(smallest, smallest.radius * 2, new Point(smallest.x, smallest.y + smallest.radius),
								new Point(smallest.x - SMALL, smallest.y + smallest.radius)));
			}
		}

		polygonArea /= 2;
//        if (stats.isStats()) {
//            stats.area = arcArea + polygonArea;
//            stats.arcArea = arcArea;
//            stats.polygonArea = polygonArea;
//            stats.arcs = arcs;
//            stats.innerPoints = innerPoints;
//            stats.intersectionPoints = intersectionPoints;
//        }

        return arcArea + polygonArea;
	}

	private static double getCircleArea(double r, double width) {
		return getCircleIntegral(r, width - r) - getCircleIntegral(r, -r);
	}

	private static double getCircleIntegral(double r, double x) {
		double y = Math.sqrt(r * r - x * x);
		return x * y + r * r * Math.atan2(x, y);
	}

	private static Point getCenter(Point[] points) {
		Point center = new Point(0, 0);
		for (int i = 0; i < points.length; ++i) {
			center.x += points[i].x;
			center.y += points[i].y;
		}
		center.x /= points.length;
		center.y /= points.length;
		return center;
	}

	private static Point[] getInnerPoints(Point[] intersectionPoints, MyCircle[] circles) {
		Point[] points = {};
		for (int i = 0; i < intersectionPoints.length; ++i) {
			if (containedInCircles(intersectionPoints[i], circles)) {
				points = (Point[])addElement(points, intersectionPoints[i]);
			}
		}
		return points;
	}

	private static boolean containedInCircles(Point p, MyCircle[] circles) {
		for (int i = 0; i < circles.length; ++i) {
			if (getDistance(p, circles[i].getPoint()) > circles[i].radius + SMALL) {
				return false;
			}
		}
		return true;
	}

	public static Point[] getIntersectionPoints(MyCircle[] circles) {
		Point[] points = {};

		for (int i = 0; i < circles.length; ++i) {
			for (int j = i + 1; j < circles.length; ++j) {
				Point[] intersectionPoints = getCircleCircleIntersection(circles[i], circles[j]);
				int[] parents = { i, j };
				if (intersectionPoints!=null){
					for (int k = 0; k < intersectionPoints.length; ++k) {
						Point p = intersectionPoints[k];
						p.setParents(parents);
						points = (Point[])addElement(points, p);
					}
				}
			}
		}
		return points;
	}

	public static Point[] getCircleCircleIntersection(MyCircle c1, MyCircle c2) {

		double d = getDistance(c1.getPoint(), c2.getPoint());
		double r1 = c1.getRadius();
		double r2 = c2.getRadius();

		if ((d >= (r1 + r2)) || (d <= Math.abs(r1 - r2))) {
			return null;
		}

		double a = (r1 * r1 - r2 * r2 + d * d) / (2 * d);
		double h = Math.sqrt(r1 * r1 - a * a);
		double x0 = c1.x + a * (c2.x - c1.x) / d;
		double y0 = c1.y + a * (c2.y - c1.y) / d;
		double rx = -(c2.y - c1.y) * (h / d);
		double ry = -(c2.x - c1.x) * (h / d);

		Point p1 = new Point(x0 + rx, y0 - ry), p2 = new Point(x0 - rx, y0 + ry);
		Point[] points = { p1, p2 };
		return points;
	}

	public static double getDistance(Point p1, Point p2) {
		return Math.sqrt(Math.pow((p1.getX() - p2.getX()), 2) + Math.pow(p1.getY() - p2.getY(), 2));
	}

	private static Object[] addElement(Object[] original, Object o) {
		// TODO Need Test
		int newLength = original.length + 1;
		Object destination[] = Arrays.copyOf(original, newLength);
		destination[newLength-1] = o;
		return destination;
	}
}
