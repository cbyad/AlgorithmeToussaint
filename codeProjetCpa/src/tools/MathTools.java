package tools;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.shape.Circle;

public class MathTools {


	public static double circleArea(double radius){
		return Math.PI*(radius)*(radius);
	}


	public static double calculAngle(Point a , Point b , Point c , Point d){
		if (a.distance(b)*c.distance(d)==0)return Double.MAX_VALUE;
		return Math.acos(((b.x-a.x)*(d.x-c.x)+(b.y-a.y)*(d.y-c.y))/(double)(a.distance(b)*c.distance(d) ));

	}



	/*
	 * Produit vectoriel (pq,pr)
	 */
	public static double crossProduct(Point p , Point q , Point r){ 
		return (q.getX()-p.getX())*(r.getY()-p.getY())-(q.getY()-p.getY())*(r.getX()-p.getX());
	}


	public static Point abscisseMin(ArrayList<Point> points){
		Point p=points.get(0);
		for (Point r : points) {
			if(r.getX()<p.getX()) p = r ;
		}
		return p;
	}

	public static double polygonArea(ArrayList<Point> points){
				
		double area = 0.0;
        for (int i = 0; i < points.size(); i++) {
        	
            if(i==points.size()-1){
            	area = area + ( points.get(i).x * points.get(0).y) - (points.get(i).y * points.get(0).x);
            }
            else{
            area = area + ( points.get(i).x * points.get(i+1).y) - (points.get(i).y * points.get(i+1).x);
            }
        
        }
        return Math.abs(0.5*area);
		
		
	}
	/*
	 * 
	 */
	public static double quality(ArrayList<Point> pointsPoly , Circle circle){ //
		return ( circleArea(circle.getRadius()) / polygonArea(pointsPoly) ) - 1 ;
	}
	
	
	public static void main(String[] args) {
		ArrayList<Point> t = new ArrayList<>();
		t.add(new Point(-4, 3));
		t.add(new Point(5, 1));
		t.add(new Point(2, 5));
		System.out.println(MathTools.polygonArea(t));
	}
	

}