package com.cpa.compute;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.cpa.algorithms.Convex;
import com.cpa.algorithms.Ritter;
import com.cpa.algorithms.Toussaint;
import com.cpa.algorithms.Utils;
import com.cpa.tools.LoadInstance;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;



/**
 * Cette classe realise le benchmark ,cree un fichier de sorti au format csv a partir de la base Varamous
 * @author cb_mac
 *
 */
public class Benchmark{

	public static void main(String[] args) throws IOException {

		String outputFileName = args[0];

		LoadInstance currInstance = LoadInstance.getInstance();
		int i = 0;


		ArrayList<Point> points = null;
		ArrayList<Point> enveloppeConvex = null;
		ArrayList<Line> paireAntiPodales =null ;
		Point2D.Double[] rect = null;
		Circle cercle = null;
		double QualiteRect = 0.0, QualiteCercle = 0.0;
		double sum = 0;
		double sumCircle = 0;
		double rectArea, hullArea, circleArea;
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName));
		long timeStartRectangle, timeEndRectangle;
		long timeStartCircle, timeEndCircle;
		long timeRectangle = 0, timeCircle = 0;

		bw.write("Instance,AireRect,AirePoly,AireCercle,QualiteRect,TempsRect,QualiteCercle,TempsCercle\n");
		while ((points = currInstance.getNextFile()) != null) {
			enveloppeConvex = Convex.graham(points);
			paireAntiPodales = Convex.calculPairesAntipodales(enveloppeConvex) ; // paires antipodales
			timeStartRectangle = System.nanoTime();

			rect = Toussaint.AlgorithmeRectangleMinimun(paireAntiPodales, enveloppeConvex);
			timeEndRectangle = System.nanoTime();

			timeStartCircle = System.nanoTime();

			cercle =Ritter.calculCercleMinRitter(points);
			timeEndCircle = System.nanoTime();

			circleArea = Utils.circleArea(cercle.getRadius());
			rectArea = Utils.rectangleArea(rect);
			hullArea =Utils.polygonArea(enveloppeConvex);

			QualiteRect = (rectArea / hullArea)-1;
			QualiteCercle = (circleArea / hullArea)-1;

			bw.write(currInstance.getCurrentFileName());
			bw.write(",");
			bw.write(String.valueOf(rectArea));
			bw.write(",");
			bw.write(String.valueOf(hullArea));
			bw.write(",");
			bw.write(String.valueOf(circleArea));
			bw.write(",");
			bw.write(String.valueOf(QualiteRect));
			bw.write(",");
			bw.write(String.valueOf(timeEndRectangle - timeStartRectangle));
			bw.write(",");
			bw.write(String.valueOf(QualiteCercle)); 
			bw.write(",");
			bw.write(String.valueOf(timeEndCircle - timeStartCircle));
			bw.write("\n");
			sumCircle += QualiteCercle;
			sum += QualiteRect;
			timeRectangle += (timeEndRectangle - timeStartRectangle);
			timeCircle += (timeEndCircle - timeStartCircle);
			i++;
		}
		System.out.println("Moyenne QualiteRectangle : " + sum / i + " " + ((double) timeRectangle / i) + " nanoseconds");
		System.out.println("Moyenne QualiteCercle : " + sumCircle / i + " " + ((double) timeCircle / i) + " nanoseconds");

		bw.close();
	}

}