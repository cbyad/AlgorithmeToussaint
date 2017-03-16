package compute;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import algorithm.Jarvis;
import algorithm.Ritter;
import algorithm.Toussaint;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import tools.LoadInstance;
import tools.MathTools;

public class GraphicView extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		Group root = new Group();
		
		ArrayList<Point> points = LoadInstance.readFile("samples/test-1048.points");
				
		Circle circle =new Ritter().calculCercleMinRitter(points);// calcul ritter
		
		//ArrayList<Point> pointsJarvis = new Jarvis().enveloppeConvexeJarvis(points);
		ArrayList<Point> pointsJarvis = new Jarvis().tme2exercice6(points);
		circle.setFill(Color.CORNFLOWERBLUE);
		//circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
		circle.setStrokeWidth(2);
		
		
		Polygon poly  = new Polygon();
		
		Toussaint t = new Toussaint();
		ArrayList<Line> ligne = t.toussaint(pointsJarvis) ;//new Line(45, 55, 100, 200);
		//System.out.println(ligne.size());
		
		
		
		
		poly.setFill(Color.BLANCHEDALMOND);
		poly.setStroke(Color.WHITE);
		//poly.setStrokeLineCap(StrokeLineCap.SQUARE);
		poly.setStrokeWidth(1);
		
		pointsJarvis.forEach(e ->{poly.getPoints().add((double) e.x) ;
								poly.getPoints().add((double) e.y); });
		
		System.out.println(poly.getPoints().size());
		
		root.getChildren().addAll();
		
		//root.getChildren().add(circle);
		root.getChildren().add(poly);
		root.getChildren().addAll( t.getRectangleMin(ligne, pointsJarvis));
		root.getChildren().addAll(t.toussaint(pointsJarvis));
		
		root.getChildren().addAll(putPointOnScreen(points));
//		Label circleArea = new Label("Area circle= "+MathTools.circleArea(circle.getRadius()));
//		Label polyArea = new Label("Area polygon= "+MathTools.polygonArea(pointsJarvis));
//		
//		Label qualite = new Label("Qualite = "+MathTools.quality(pointsJarvis, circle));
		
		
		
//		polyArea.setTranslateY(20);
//		qualite.setTranslateY(40);
		
//		circleArea.setTextFill(Color.WHITE);
//		polyArea.setTextFill(Color.WHITE);
//		qualite.setTextFill(Color.WHITE);
//		root.getChildren().add(circleArea);
//		root.getChildren().add(polyArea);
//		root.getChildren().add(qualite);
		
		Scene scene = new Scene(root);
		scene.setFill(Color.WHITE);
		primaryStage.setTitle("---Algorithm Ritter---");
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
	ArrayList<Circle> putPointOnScreen(ArrayList<Point> points){
		ArrayList<Circle> result = new ArrayList<Circle>();
		
		for (Point p : points) {
			result.add(new Circle(p.x, p.y,2,Color.RED));
		}
		return result ;
		
	}

}
