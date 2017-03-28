package com.cpa.tools;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Utilitaire pour gérer les fichiers en input. Sous forme de singleton.
 * 
 * @author Maxime Bonnet
 * 
 */
public class TestFilesManager {

	// L'instance du singleton
	private static TestFilesManager instance;
	// L'index du fichier actuel dans le dossier
	private int currentIndex;
	// Le dossier dans lequel sont situés les fichiers de test.
	private File directory;
	// Le nom du fichier actuel
	private String currentFileName;

	private TestFilesManager() {
		directory = new File("samples/");
		currentIndex = 0;

	}

	/**
	 * Retourne l'instance de la classe. Méthode principale de l'implémentation
	 * du singleton
	 * 
	 * @return l'instance de la classe.
	 */
	public static TestFilesManager getInstance() {
		if (instance == null) {
			instance = new TestFilesManager();
		}
		return instance;
	}

	/**
	 * Parcours du fichier et renvoie la liste des points dont les coordonnées
	 * sont écrites dans le fichier
	 * 
	 * @param filename
	 *            Le nom du fichier a parcourir.
	 * @return La liste des points correspondante au fichier passé en paramètre.
	 */
	public ArrayList<Point.Double> getPointsFromFile(String filename) {
		ArrayList<Point.Double> points = new ArrayList<Point.Double>();
		try {
			BufferedReader bReader = new BufferedReader(
					new FileReader(filename));
			String line;
			String[] coordinates;
			while ((line = bReader.readLine()) != null) {
				coordinates = line.split(" ");
				points.add(new Point.Double(Integer.parseInt(coordinates[0]),
						Integer.parseInt(coordinates[1])));
			}
		} catch (IOException e) {
			e.printStackTrace(System.err);

		}
		return points;
	}

	/**
	 * Retourne une liste de 256 points dont les coordonnées sont aléatoirement
	 * choisies entre 300 et 500.
	 * 
	 * @return Une liste de points aléatoires.
	 */

	public ArrayList<Point.Double> getRandomList() {
		Random r = new Random();
		ArrayList<Point.Double> resultat = new ArrayList<Point.Double>();
		for (int i = 0; i < 256; ++i) {
			resultat.add(new Point.Double(r.nextDouble() * 200 + 300, r
					.nextDouble() * 200 + 300));
		}
		return resultat;
	}

	/**
	 * Retourne la liste des points du prochain fichier dans le dossier.
	 * 
	 * @return la liste des points du prochain fichier dans le dossier
	 */
	public ArrayList<Point.Double> getNextFile() {
		currentIndex = (currentIndex + 1);
		if (currentIndex >= directory.listFiles().length)
			return null;
		while (!(directory.listFiles()[currentIndex].isFile())) {
			currentIndex = (currentIndex + 1);
			if (currentIndex >= directory.listFiles().length)
				return null;
		}
		currentFileName = directory.listFiles()[currentIndex].getName();
		return getPointsFromFile("samples/"
				+ directory.listFiles()[currentIndex].getName());
	}

	/**
	 * Retourne le nom du fichier courrant (pendant le parcours du dossier)
	 * 
	 * @return le nom du fichier courrant
	 */
	public String getCurrentFileName() {
		return currentFileName;
	}

}