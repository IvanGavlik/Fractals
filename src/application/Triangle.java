package application;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class Triangle {
	
	private Point a;
	private Point b;
	private Point c;
	private Fill fill;
	private Polygon triangle;
	
	public Triangle(Point a, Point b, Point c, Fill fill) {
		this.a = a;
		this.b = b;
		this.c = c;
		
		// create view
		this.fill = fill;
		triangle = new Polygon();
		Double[] points = new Double[] { 
			(double)a.getX(), (double)a.getY(),
			(double) b.getX(), (double) b.getY(),
			(double) c.getX(), (double)c.getY() 
		};
		triangle.getPoints().addAll(points);
		triangle.setFill(Paint.valueOf(fill.getValue()));
	}
	
	public Point getPointA() {
		return this.a;
	}

	public Point getPointB() {
		return this.b;
	}

	public Point getPointC() {
		return this.c;
	}
	
	public Fill getFill() {
		return this.fill;
	}
	
	public Polygon getView() {
		return this.triangle;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(a).append(System.lineSeparator());
		sb.append(b).append(System.lineSeparator());
		sb.append(c).append(System.lineSeparator());
		return sb.toString();
	}
	
}
