package com.cpa.compute;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import com.cpa.algorithms.Convex;
import com.cpa.algorithms.Utils;
import com.cpa.algorithms.Ritter;
import com.cpa.algorithms.Toussaint;
import com.cpa.tools.LoadInstance;
import com.cpa.tools.RotatingCalipers;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;

public class GraphicView extends Application {

	@Override
	public void start(Stage primaryStage) {

		ArrayList<Point> points = LoadInstance.readFile("samples/test-1070.points"); // points d'entree

		Circle cercle =Ritter.calculCercleMinRitter(points);// calcul ritter

		ArrayList<Point> pointConvex = Convex.convexHull(points);

		Point2D.Double[] minimum = RotatingCalipers.getMinimumBoundingRectangle(pointConvex);

		int number = 1;
		List<Point> p = new ArrayList<>();

		for(Point2D.Double corner : minimum) {
			Point p1= new Point((int)corner.x, (int)corner.y);
			p.add(p1);

			System.out.printf("corner[%d] (%.1f, %.1f)%n", number++, corner.x, corner.y);
		}

		System.out.printf("%narea: %.1f", Utils.rectangleArea(minimum));

		Polygon rec = new Polygon();
		p.forEach(e ->{rec.getPoints().add((double) e.x);rec.getPoints().add((double)e.y);});


		rec.setStroke(Color.RED);
		rec.setStrokeLineCap(StrokeLineCap.SQUARE);
		rec.setStrokeWidth(2);



		cercle.setFill(Color.CORNFLOWERBLUE);
		//circle.setFill(Color.WHITE);
		cercle.setStroke(Color.BLACK);
		cercle.setStrokeWidth(2);


		Polygon poly  = new Polygon();

		Toussaint t = new Toussaint();
		ArrayList<Line> ligne = t.toussaint(pointConvex) ;//new Line(45, 55, 100, 200);
		//System.out.println(ligne.size());




		poly.setFill(Color.BLANCHEDALMOND);
		poly.setStroke(Color.WHITE);
		//poly.setStrokeLineCap(StrokeLineCap.SQUARE);
		poly.setStrokeWidth(1);

		pointConvex.forEach(e ->{poly.getPoints().add((double) e.x) ;poly.getPoints().add((double) e.y); });

		System.out.println(poly.getPoints().size());
		Group root = new Group();
		root.getChildren().addAll();

		root.getChildren().add(cercle);
		//root.getChildren().add(rec);
		root.getChildren().add(poly);
		root.getChildren().addAll( t.getRectangleMin(ligne, pointConvex));
		//root.getChildren().addAll(t.toussaint(pointConvex));

		root.getChildren().addAll(drawPointOnScreen(points));
		Label circleArea = new Label("Area circle= "+Utils.circleArea(cercle.getRadius()));
		Label polyArea = new Label("Area polygon= "+Utils.polygonArea(pointConvex));

		Label qualite = new Label("Qualite = "+Utils.quality(pointConvex, cercle));



		polyArea.setTranslateY(20);
		qualite.setTranslateY(40);

		circleArea.setTextFill(Color.WHITE);
		polyArea.setTextFill(Color.WHITE);
		qualite.setTextFill(Color.WHITE);
		root.getChildren().add(circleArea);
		root.getChildren().add(polyArea);
		root.getChildren().add(qualite);

		Scene scene = new Scene(root);
		scene.setFill(Color.CADETBLUE);
		primaryStage.setTitle("---Algorithm Toussaint/Ritter---");
		primaryStage.setScene(scene);
		primaryStage.setHeight(600);
		primaryStage.setWidth(800);
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> Platform.exit());
	}


	public static void main(String[] args) {

		launch(args);
	}

	/*
	 * 
	 * Cette methode permet de dessiner les points
	 */
	ArrayList<Circle> drawPointOnScreen(ArrayList<Point> points){
		ArrayList<Circle> result = new ArrayList<Circle>();

		for (Point p : points) {
			result.add(new Circle(p.x, p.y,2,Color.RED));
		}
		return result ;

	}




}