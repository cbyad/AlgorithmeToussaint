package com.cpa.algorithms;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.shape.Circle;

public class Ritter {

	public static Circle calculCercleMinRitter(ArrayList<Point> points) {

		ArrayList<Point> newSetPoints =  new ArrayList<Point>(points);

		//initialisation des points de depart
		int aleatoire= (int) (Math.random()*newSetPoints.size());
		Point Pinit = newSetPoints.get(aleatoire);
		Point Pmax = Pinit;
		Point Qmax =Pmax;

		for (Point p : newSetPoints) { // Point le plus loin de Pinit
			if(Pinit.distance(p)>Pinit.distance(Pmax))  Pmax=p ;	  
		}

		for (Point q : newSetPoints) { //point le plus loin de Pmax
			if(Pmax.distance(q)>Pmax.distance(Qmax))  Qmax=q ;
		}

		// Le point C milieu de [PmaxQmax] qui definit ensuite le cercle CERCLE
		Point C = new Point((Pmax.x+Qmax.x)/2,(Pmax.y+Qmax.y)/2);
		Circle CERCLE = new Circle(C.x,C.y, (int) C.distance(Pmax)); 

		newSetPoints.remove(Pmax);// On retire Pmax
		newSetPoints.remove(Qmax);// On retire Qmax

		while(!newSetPoints.isEmpty()){
			Point S = newSetPoints.get(0);

			if (S.distance(C)< CERCLE.getRadius()) {
				newSetPoints.remove(S);
			}
			else {
				//Calcul des coordoonées du point C' et mise a jour de CERCLE (C',[CS]) 
				double cprimeX =(((C.distance(S)+ CERCLE.getRadius())/2)* C.x  + (C.distance(S)-((C.distance(S)+ CERCLE.getRadius())/2))*S.x )/C.distance(S);

				double cprimeY =(((C.distance(S)+ CERCLE.getRadius())/2)* C.y  + (C.distance(S)-((C.distance(S)+ CERCLE.getRadius())/2))*S.y )/C.distance(S);

				Point  Cprime = new Point((int)cprimeX,(int)cprimeY) ;

				CERCLE = new Circle(Cprime.x,Cprime.y, (int) Cprime.distance(S)); 
				newSetPoints.remove(S); // On retire S 
			}
		}
		return CERCLE ; // On obtient le cercle de Ritter
	}

}
