package algorithm;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.shape.Circle;

public class Ritter {

	public Circle calculCercleMinRitter(ArrayList<Point> points) {

		ArrayList<Point> newPoints =  new ArrayList<Point>(points);

		Point dummy = newPoints.get(0);
		Point Pmax = dummy;
		Point Qmax =Pmax;
		
		for (Point p : newPoints) {
			if(dummy.distance(p)>dummy.distance(Pmax))  Pmax=p ;	  
		}

		for (Point q : newPoints) {
			if(Pmax.distance(q)>Pmax.distance(Qmax))  Qmax=q ;
		}

		Point C = new Point((Pmax.x+Qmax.x)/2,(Pmax.y+Qmax.y)/2);
		Circle CERCLE = new Circle(C.x,C.y, (int) C.distance(Pmax)); // 

		newPoints.remove(Pmax);
		newPoints.remove(Qmax);

		while(!newPoints.isEmpty()){
			Point S = newPoints.get(0);

			if (S.distance(C)< CERCLE.getRadius()) {
				newPoints.remove(S);
			}
			else {

				double cprimeX =(((C.distance(S)+ CERCLE.getRadius())/2)* C.x  + (C.distance(S)-((C.distance(S)+ CERCLE.getRadius())/2))*S.x )/C.distance(S);
				double cprimeY =(((C.distance(S)+ CERCLE.getRadius())/2)* C.y  + (C.distance(S)-((C.distance(S)+ CERCLE.getRadius())/2))*S.y )/C.distance(S);

				Point  Cprime = new Point((int)cprimeX,(int)cprimeY) ;

				CERCLE = new Circle(Cprime.x,Cprime.y, (int) Cprime.distance(S)); // Circle(x,y,radius)
				newPoints.remove(S);
			}


		}
		return CERCLE ;


	}


}
