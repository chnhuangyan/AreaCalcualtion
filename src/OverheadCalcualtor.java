
public class OverheadCalcualtor {

	static final double PI = 3.14115926; 
	static final double delta = 0.6;
	static final double range = 1.0;   //km  from 0.1-1.5
	static double radius = (1+delta)*range;
	static final int runTime = 50000000;
	static final double areaO = PI*range*range;
	
	public static void main(String[] args) {
		
		for(double i = 0.0 ; i <= 1.2; i=i+0.1){
			radius = (1+i)*range;
			System.out.print(i+" ");
			System.out.println(3*getArea(radius)-getArea(range));
		}
		
	}
	
	private static double getArea(double r){
		return PI*r*r;
	}

	
}
