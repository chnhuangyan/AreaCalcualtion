
public class AreaCalculation {

	public static double circularTriangle(Point p1, Point p2, Point p3, int r ){
		double area;
		
		double d12 = Math.sqrt(Math.pow((p1.getX()-p2.getX()), 2) + Math.pow(p1.getY()-p2.getY(),2));
		double d13 = Math.sqrt(Math.pow((p1.getX()-p3.getX()), 2) + Math.pow(p1.getY()-p3.getY(),2));
		double d23 = Math.sqrt(Math.pow((p2.getX()-p3.getX()), 2) + Math.pow(p2.getY()-p3.getY(),2));
		
		double x12 = d12/2;
		double y12 = Math.sqrt(Math.pow(r,2)-(Math.pow(d12, 2)/4));
		
		double cos1 = (Math.pow(d12,2)+Math.pow(d13,2)-Math.pow(d23,2))/(2*d12*d13);
		double cos2 = -(Math.pow(d12,2)+Math.pow(d23,2)-Math.pow(d13,2))/(2*d12*d23);
		
		double sin1 = Math.sqrt(1-Math.pow(cos1, 2));
		double sin2 = Math.sqrt(1-Math.pow(cos2, 2));
		
		if(Math.pow(x12-d13*cos1, 2)+Math.pow(y12-d13*sin1, 2)>Math.pow(r, 2)
				|| Math.pow(x12-d13*cos1, 2)+Math.pow(y12+d13*sin1, 2)<Math.pow(r, 2)){	
			return -1;
		}
		
		double x13p = d13/2;
		double y13p = -Math.sqrt(Math.pow(r, 2)-Math.pow(d13, 2)/4);
		double x13 = x13p*cos1-y13p*sin1;
		double y13 = x13p*sin1+y13p*cos1;
		double x23p = d23/2;
		double y23p = Math.sqrt(Math.pow(r, 2)-Math.pow(d23, 2)/4);
		double x23 = x23p*cos2-y23p*sin2+d12;
		double y23 = x23p*sin2+y23p*cos2;
		
		double c1 = Math.sqrt(Math.pow(x12-x13, 2)+Math.pow(y12-y13, 2));
		double c2 = Math.sqrt(Math.pow(x12-x23, 2)+Math.pow(y12-y23, 2));
		double c3 = Math.sqrt(Math.pow(x23-x13, 2)+Math.pow(y23-y13, 2));
		
		boolean check = d13*sin1 < y13+((y23-y13)/(x23-x13))*(d13*cos1-x13);
		
		if (check){
			area = 0.25 * Math.sqrt((c1+c2+c3)*(c2+c3-c1)*(c1+c3-c2)*(c1+c2-c3))
					+ Math.pow(r, 2)*Math.asin(c1/(2*r))+Math.pow(r, 2)*Math.asin(c2/(2*r))+Math.pow(r, 2)*Math.asin(c3/(2*r))
					-(c1/4)*Math.sqrt(4*Math.pow(r, 2)-Math.pow(c1, 2))-(c2/4)*Math.sqrt(4*Math.pow(r, 2)-Math.pow(c2, 2))
					+(c3/4)*Math.sqrt(4*Math.pow(r, 2)-Math.pow(c3, 2));
		}else{
			area = 0.25 * Math.sqrt((c1+c2+c3)*(c2+c3-c1)*(c1+c3-c2)*(c1+c2-c3))
					+ Math.pow(r, 2)*Math.asin(c1/(2*r))+Math.pow(r, 2)*Math.asin(c2/(2*r))+Math.pow(r, 2)*Math.asin(c3/(2*r))
					-(c1/4)*Math.sqrt(4*Math.pow(r, 2)-Math.pow(c1, 2))-(c2/4)*Math.sqrt(4*Math.pow(r, 2)-Math.pow(c2, 2))
					-(c3/4)*Math.sqrt(4*Math.pow(r, 2)-Math.pow(c3, 2));
		}	
		return area;
	}
	
	public static double lens(Point p1, Point p2, double r ){
		double area;
		
		double d = Math.sqrt(Math.pow((p1.getX()-p2.getX()), 2) + Math.pow(p1.getY()-p2.getY(),2));
//		double a = (1/d)*Math.sqrt(d*d*(2*r-d)*(2*r+d));
		double ard = r*r*(1/Math.cos(d/r))-d*Math.sqrt(r*r-d*d);
		area = 2*ard;
		
		return area;
	}
	
	
	
}
