package compute;

import java.awt.Point;
import java.util.ArrayList;


import algorithm.ritter.Ritter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class GraphicView extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		Group root = new Group();
		
		ArrayList<Point> points = new ArrayList<Point>();
		
		
		points.add(new Point(44,77));
		points.add(new Point(56,66));
		points.add(new Point(56,78));
		points.add(new Point(90,65));
		points.add(new Point(3,55));
		points.add(new Point(40,9));
		
		
		Circle circle =new Ritter().calculCercleMinRitter(points);// calcul ritter
		
		circle.setFill(Color.CORNFLOWERBLUE);
		root.getChildren().add(circle);
		root.getChildren().addAll(putPointOnScreen(points));
		
		Scene scene = new Scene(root);
		scene.setFill(Color.DARKSLATEGRAY);
		primaryStage.setTitle("---Algorithm Ritter---");
		primaryStage.setScene(scene);
		primaryStage.setHeight(700);
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
			result.add(new Circle(p.x, p.y,4,Color.RED));
		}
		return result ;
		
	}
	
	
	/*
	 * parse un fichier texte , recupere les coordoonees de point et contruit une instante de point pour Ritter
	 */
	public ArrayList<Point> readFileBuildInstance(String fileName) {
		return null;
	}
	
	
	
	
	
	
}