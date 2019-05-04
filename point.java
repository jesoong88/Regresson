package poly;

public class point {
private double x;
private double y;
private static int num;
public point (double x,double y) {
	this.x = x;
	this.y = y;
	num++;
}
public double getX() {
	return x;
}
public double getY() {
	return y;
}
public static int numP() {
	return num;
}
}
