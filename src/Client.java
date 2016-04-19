import java.util.Random;

public class Client {
	static final double PI = 3.14115926; 
	static final double delta = 0.6;
	static final double range = 3.0;   //km  from 0.1-1.5
	static final double radius = (1+delta)*range;
	static final int runTime = 50000000;
	static final double areaO = PI*range*range;
	
	public static void main(String[] args) {
		
		double expP=0;
		double expU=0;
		double expQL=0;
		

		for (int i= 0; i<runTime;i++){
			
			MyCircle[] circlesP = {generateCircle(radius,range),generateCircle(radius,range),generateCircle(radius,range)};
			MyCircle[] circlesU = {new MyCircle(0,0,range),circlesP[0],circlesP[1],circlesP[2]};
			double areaP = CircleInsection.getArea(circlesP, null);
			double areaU = CircleInsection.getArea(circlesU, null);
			
			expP += areaP;
			expU += areaU;
			
			
			if(i%100000==0||i==runTime-1){
				double progress = (double)i/(double)runTime*100;
				System.out.println();
				System.out.println("Progress:"+progress+"%");
//				System.out.println("Time:"+i);
//				System.out.println("expP:"+expP);
//				System.out.println("expU:"+expU);
				
			}
			
		}
		expP /= runTime;
		expU /= runTime;
		expQL = areaO-expU;
		if (expQL<=0){
			expQL=0;
		}
		 
	     
	     
	     System.out.println("The Expectation Privacy is "+expP);
	     System.out.println("The Expectation Utility is "+expU);
	     System.out.println("The Expectation QL is "+expQL);
	     System.out.println("The Ration of Privacy (Privacy/Original)"+expP/areaO);
	     System.out.println("The Ration of QL (QL/Original)"+expQL/areaO);
		
	}
	
	public static MyCircle generateCircle(double radius, double range){
		Random random = new Random();
		double x = 999999999;
		double y = 999999999;
		while ((x*x+y*y)>range*range) {
			x = range-(Math.random()*2*range);
			y = range-(Math.random()*2*range);
		}
		
		return new MyCircle(x, y, radius);
	}

}
