package poly;

import java.util.ArrayList;
import java.util.Scanner;

public class ma {

	public static void main(String[] args) {
		int numLoops = 0;
		ArrayList<point> points = new ArrayList<point>();
		Scanner scanner = new Scanner(System.in);
		int degree;
		while (true) {
			double p = scanner.nextDouble();
			if (p == -1)
				break;
			double p2 = scanner.nextDouble();
			points.add(new point(p, p2));
			System.out.println("Point added");
		}
		degree = scanner.nextInt();
		double ders[] = new double[degree + 1];
		double theta[] = new double[degree + 1];
		double x[] = new double[point.numP()];
		double y[] = new double[point.numP()];
		double alpha = 0.0000000000000001;
		for (int i = 0; i < ders.length; i++) {
			ders[i] = 1;
			theta[i] = 1;
		}
		for (int i = 0; i < x.length; i++) {
			x[i] = points.get(i).getX();
			y[i] = points.get(i).getY();
		}
		while (Math.abs(sumDer(ders)) > 0.00001) {
			numLoops++;
			for (int i = 0; i < degree + 1; i++) {
				theta[i] -= (ders[i] * alpha);
			}
			ders = der(x,y,ders,theta);
		}
		System.out.println(numLoops);
		System.out.print(round(theta[theta.length-1])+"x^"+(theta.length-1));
		for(int i = theta.length-2; i>=0;i--) {
			double d = round(theta[i]);
			if(d!=0) {
				if(d>0)
			System.out.print(" + "+d+"x^"+i);
				else
					System.out.print(" - "+d*-1+"x^"+i);
			}
		}
	}

	public static double sumDer(double[] der) {
		double sum = 0;
		for (double d : der)
			sum += d;
		return sum;
	}

	public static double round(double t) {
		t *= 10000;
		t += 0.0005;
		int v = (int) t;
		return v / (double) 10000;
	}

	public static double[] der(double[] x,double[] y, double[] ders, double[] theta) {
		double sum = 0;
		double[] ans = new double[ders.length];
		for (int deg = 0; deg < ans.length; deg++) {
			sum = 0;
			for (int j = 0; j < x.length; j++) {
				double[] xDot = new double[theta.length];
				for (int i = 0; i < xDot.length; i++) {
					xDot[i] = Math.pow(x[j], i);
				}
				sum += Math.pow(x[j], deg) * (dotP(theta, xDot)-y[j]);
			}
			ans[deg] = sum;
		}
		return ans;
	}
	
	public static double dotP(double [] theta, double [] dot) {
		double sum = 0;
		for(int i = 0; i<theta.length;i++) sum+=(theta[i]*dot[i]);
		return sum;
	}

}
