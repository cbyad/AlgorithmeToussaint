package algorithm;

import java.awt.Point;
import java.util.ArrayList;

import tools.MathTools;

/**
 * 
 * @author cb_mac
 *Implementation de l'algo de Jarvis pour le calcul de l'enveloppe convexe
 */
public class Jarvis {

	public ArrayList<Point> enveloppeConvexeJarvis(ArrayList<Point> points){

		if (points.size()<3) {
			return null;
		}

		ArrayList<Point> enveloppe = new ArrayList<Point>();
		Point p = MathTools.abscisseMin(points);
		Point pPrime = new Point(p);

		for (Point q : points) {
			if (p.equals(q)) continue;
			boolean coteBleu=true;

			for (Point x : points) {
				if(MathTools.crossProduct(p, q, x)<0) coteBleu=false;
			}

			if(coteBleu==true) { //On a [PQ] sur l'enveloppe convexe

				boolean bolBoucle = true;
				enveloppe.add(p);
				while(bolBoucle){

					Point r = points.get(0);
					double angleMin = MathTools.calculAngle(p,q,q,r); //(pq,qr)

					for (Point rmin : points) {
						if(!rmin.equals(q) && !rmin.equals(p)){
							if( MathTools.calculAngle(p,q,q,rmin)<angleMin) 
							{
								r = rmin;
								angleMin=MathTools.calculAngle(p,q,q,rmin);
							}
						}
					}//On a trouvé R
					if(r.equals(pPrime)) bolBoucle = false;
					enveloppe.add(q);
					//enveloppe.add(r);
					p=q;
					q=r;
					//p=(Point)q.clone();
					//q=(Point)r.clone();
					
				}
				break;
			}
		}
		return enveloppe ; 
	}
	//----------------------------------
	
	
	  public static ArrayList<Point> tme2exercice6(ArrayList<Point> points){
		    if (points.size()<4) return points;
		    
		    Point ouest = points.get(0);
		    Point sud = points.get(0);
		    Point est = points.get(0);
		    Point nord = points.get(0);
		    for (Point p: points){
		      if (p.x<ouest.x) ouest=p;
		      if (p.y>sud.y) sud=p;
		      if (p.x>est.x) est=p;
		      if (p.y<nord.y) nord=p;
		    }
		    ArrayList<Point> result = new ArrayList<Point>();
		    result.add(ouest);
		    result.add(sud);
		    result.add(est);
		    result.add(nord);
		    
		    ArrayList<Point> rest = (ArrayList<Point>)points.clone();
		    for (int i=0;i<rest.size();i++) {
		      if (triangleContientPoint(ouest,sud,est,rest.get(i)) ||
		          triangleContientPoint(ouest,est,nord,rest.get(i))) {
		        rest.remove(i);
		        i--;
		      }
		    }

		    for (int i=0;i<result.size();i++) {
		      Point a = result.get(i);
		      Point b = result.get((i+1)%result.size());
		      Point ref = result.get((i+2)%result.size());

		      double signeRef = crossProduct(a,b,a,ref);
		      double maxValue = 0;
		      Point maxPoint = a;

		      for (Point p: points) {
		        double piki = crossProduct(a,b,a,p);
		        if (signeRef*piki<0 && Math.abs(piki)>maxValue) {
		          maxValue = Math.abs(piki);
		          maxPoint = p;
		        }
		      }
		      if (maxValue!=0){
		        for (int j=0;j<rest.size();j++) {
		          if (triangleContientPoint(a,b,maxPoint,rest.get(j))){
		            rest.remove(j);
		            j--;
		          }
		        }
		        result.add(i+1,maxPoint);
		        i--;
		      }
		    }
		    return result;
		  }
	
	
	
	
	
	
	  public static boolean triangleContientPoint(Point a, Point b, Point c, Point x) {
		    double l1 = ((b.y-c.y)*(x.x-c.x)+(c.x-b.x)*(x.y-c.y))/(double)((b.y-c.y)*(a.x-c.x)+(c.x-b.x)*(a.y-c.y));
		    double l2 = ((c.y-a.y)*(x.x-c.x)+(a.x-c.x)*(x.y-c.y))/(double)((b.y-c.y)*(a.x-c.x)+(c.x-b.x)*(a.y-c.y));
		    double l3 = 1-l1-l2;
		    return (0<l1 && l1<1 && 0<l2 && l2<1 && 0<l3 && l3<1);
		  }

	
	
	
	
	
	  public static double crossProduct(Point p, Point q, Point s, Point t){
		    return ((q.x-p.x)*(t.y-s.y)-(q.y-p.y)*(t.x-s.x));
		  }
	
	
	
	
	
	
	

}
