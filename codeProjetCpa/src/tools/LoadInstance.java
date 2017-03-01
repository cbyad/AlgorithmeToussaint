package tools;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 
 * @author cb_mac
 *Cette classe s'occupe de charger les instances depuis le repertoire pour le benchmarcks
 */
public class LoadInstance {

	/*
	 * parse un fichier texte , recupere les coordoonees de point et contruit une instance de point 
	 */
	public static ArrayList<Point> readFile(String fileName) {
		ArrayList<Point> instance = new ArrayList<Point>();
		
		try {
			
			InputStream flux = new FileInputStream(fileName);
			InputStreamReader lecture = new InputStreamReader(flux);
			BufferedReader buff = new BufferedReader(lecture);
			
			String ligne ;
			while ((ligne=buff.readLine())!=null ) {
				
				String[] tmp = ligne.split(" ");
				int x = Integer.parseInt(tmp[0]);
				int y = Integer.parseInt(tmp[1]);
				instance.add(new Point(x, y));
			}
			buff.close();
			
		} catch (Exception e) {
			throw new IllegalArgumentException("Input file error! ");
		}
		
		return instance ;
	}
	

	public static void main(String[] args) {
		String file = args[0];
		System.out.println(LoadInstance.readFile(file).size());
	}
	
}
