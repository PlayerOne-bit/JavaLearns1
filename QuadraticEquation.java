import java.lang.Math;
public class QuadraticEquation {
	
	static double[] findRoots(int a, int b, int c) {
		double[] x= new double[2];
		x[0]=(-b+Math.sqrt((b*b)-(4*a*c)))/(2*a); 
		x[1]=(-b-Math.sqrt((b*b)-(4*a*c)))/(2*a); 
		
		return x;
	}
	
	public static void main(String[] args) {
		double[] x=findRoots(4,4,-8);   //ax^2 + bx +c = 0
		System.out.println(x[0]+"\n"+x[1]);
		
	}
}
