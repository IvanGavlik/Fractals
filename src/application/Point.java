package application;

public class Point {
	
	private int x; 
	private int y; 
	
	public Point(int x, int y) {
		this.x  = x; 
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public String toString() {
		return "x="+ x + System.lineSeparator() + "y="+ y; 
	}
}
