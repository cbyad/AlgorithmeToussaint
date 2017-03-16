package algorithm;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Toussaint {

	
	public  ArrayList<Line>  toussaint (ArrayList<Point> points ){
		
		ArrayList<Point> enlevoppe = Jarvis.tme2exercice6(points);
		
		ArrayList<Line> line = Jarvis.calculPairesAntipodales(enlevoppe);
		
		return Jarvis.calculPairesAntipodales(enlevoppe);
		

	}
	
	
	public 	ArrayList<Line>  getRectangleMin (ArrayList<Line> pointsAntiPodal, ArrayList<Point> enveloppe){
		//Point p1Suiv =enveloppe.get(enveloppe.indexOf(p1)%enveloppe.size());
		 ArrayList<Line> result = new ArrayList<>();
		
		 for (Point p : enveloppe)
			 System.out.println(p);
		
		for (Point p : getPointsFromLine(pointsAntiPodal)) {
			
			Point p1Suiv = enveloppe.get((enveloppe.indexOf(p) + 1 )%enveloppe.size());

			System.out.println(89489489);
			System.out.println(89489489);
			double a = (1.0*(p1Suiv.y-p.y))/(p1Suiv.x-p.x);
			double b = p.y- (a*p.x);
			
//			result.add(new Line(p.x, p.y, p1Suiv.x, p1Suiv.y));
//			result.add(new Line(p.x,p.y,100,a*100+b));
			double newB, bestB = Integer.MAX_VALUE;
			Point bestP = null;
			ArrayList<Point> pairesCommunes = pointsAntiPodalCommun(p, pointsAntiPodal);
			for (Point pAntiPod : pairesCommunes) {
				newB = pAntiPod.y - (a * pAntiPod.x);
				if (newB > b) {
					if (bestB == Integer.MAX_VALUE || newB > bestB) {
						bestB = newB;
						bestP = pAntiPod;
					}
				} else {
					if (bestB > newB) {
						bestB = newB;
						bestP = pAntiPod;
					}
				}
			}
			result.add(new Line(p.x,p.y,100,a*100+b));
			System.out.println(bestP);
			result.add(new Line(bestP.x,bestP.y,100,a*100+bestB));
			System.out.println(result);
//			return result;
		}
		
		

	return result ;
		
		
	}
	
	public ArrayList<Point> pointsAntiPodalCommun(Point p1 , ArrayList<Line> antipodales){
		ArrayList<Point> listePoints =  new ArrayList<>();
		ArrayList<Point> pairePodale = getPointsFromLine(antipodales);
		
		for (int i = 0 ; i < pairePodale.size() ; i += 2) { // l (x1,x2,y1,y2)
			
			if(p1.equals(pairePodale.get(i))){
				listePoints.add(pairePodale.get(i + 1));
			}
			else if (p1.equals(pairePodale.get(i + 1))){
				listePoints.add(pairePodale.get(i));
			}
		}
		
		return listePoints ;
	}
	
	
	
	
	
	
	
	
	
	public ArrayList<Point> getPointsFromLine(ArrayList<Line> line ){
		ArrayList<Point> listePoints =  new ArrayList<>();
		
		for (Line l : line) {
				double x1 = l.getStartX();
				double y1= l.getStartY();
				double x2 = l.getEndX();
				double y2= l.getEndY();
				
			listePoints.add(new Point((int)x1,(int) y1) );
			listePoints.add(new Point((int)x2,(int)y2) );
		}
		return listePoints ;
		
	}
	
	
}
