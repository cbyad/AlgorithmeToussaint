package com.cpa.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class DiskFileExplorer {
	 
    private String initialpath = "";
    private Boolean recursivePath = false;
    public int filecount = 0;
    public int dircount = 0;
 
/**
 * Constructeur
 * @param path chemin du répertoire
 * @param subFolder analyse des sous dossiers
 */
    public DiskFileExplorer(String path, Boolean subFolder) {
        super();
        this.initialpath = path;
        this.recursivePath = subFolder;
    }
 
    public void list() {
        this.listDirectory(this.initialpath);
    }
 
    private void listDirectory(String dir) {
        File file = new File(dir);
        File[] files = file.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory() == true) {
                    System.out.println("Dossier: " + files[i].getAbsolutePath());
                    this.dircount++;
                } else {
                	if(files[i].getName().endsWith(".points")){
                		 System.out.println("  Fichier: " + files[i].getName());
                         this.filecount++;
                	}
                   
                }
                if (files[i].isDirectory() == true && this.recursivePath == true) {
                    this.listDirectory(files[i].getAbsolutePath());
                }
            }
        }
    }  
    
    
    public static void main(String[] args) throws IOException, InterruptedException {
		
//    	DiskFileExplorer t=new DiskFileExplorer("samples",false);
//    	t.list();
//    	System.out.println(t.filecount);
    	
    	
    	
    	

    	        String command = "pmset -g batt";

    	        Process proc = Runtime.getRuntime().exec(command);

    	        // Read the output

    	        BufferedReader reader =  
    	              new BufferedReader(new InputStreamReader(proc.getInputStream()));

    	        String line = "";
    	        while((line = reader.readLine()) != null) {
    	            System.out.print(line + "\n");
    	        }

    	        proc.waitFor();   

    	
    	
    	
	}
    
    
}
