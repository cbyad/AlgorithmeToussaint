package com.cpa.algorithms;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javafx.scene.shape.Circle;
/**
 * Cette classe fournit les outils necessaires dans les manipulations geometrique
 * @author cb_mac
 *
 */
public class Utils {

	public static double circleArea(double radius){
		return Math.PI*(radius)*(radius);
	}

	public static double calculAngle(Point a , Point b , Point c , Point d){
		if (a.distance(b)*c.distance(d)==0)return Double.MAX_VALUE;
		return Math.acos(((b.x-a.x)*(d.x-c.x)+(b.y-a.y)*(d.y-c.y))/(double)(a.distance(b)*c.distance(d) ));

	}


	public static double crossProduct(Point p, Point q, Point s, Point t){
		return ((q.x-p.x)*(t.y-s.y)-(q.y-p.y)*(t.x-s.x));
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

	public static double qualityPoly_circle(ArrayList<Point> pointsPoly , Circle circle){ //
		return ( circleArea(circle.getRadius()) / polygonArea(pointsPoly) ) - 1 ;
	}

	public static double qualityPoly_rectangle(ArrayList<Point> pointsPoly , Point2D.Double[] rectangle){ //
		return ( (rectangleArea(rectangle)) / polygonArea(pointsPoly) ) - 1 ;
	}


	/**
	 * 
	 * @param longeur
	 * @param largeur
	 * @return L'aire d'un rectangle 
	 */
	public static double rectangleArea(Point2D.Double[] rectangle) {

		double deltaXAB = rectangle[0].x - rectangle[1].x;
		double deltaYAB = rectangle[0].y - rectangle[1].y;

		double deltaXBC = rectangle[1].x - rectangle[2].x;
		double deltaYBC = rectangle[1].y - rectangle[2].y;

		double lengthAB = Math.sqrt((deltaXAB * deltaXAB) + (deltaYAB * deltaYAB));
		double lengthBC = Math.sqrt((deltaXBC * deltaXBC) + (deltaYBC * deltaYBC));

		return lengthAB * lengthBC;
	}


}
