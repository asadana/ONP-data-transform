package com.onp.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Launcher {

	public static void main(String[] args) {
		System.out.println("Starting Main");
		
		Launcher objLauncher = new Launcher();
		objLauncher.run();
	}

	private void run() {
		
		String folderName = "resources";
		String fileName = "OnlineNewsPopularity.csv";
		BufferedReader br = null;
		String dataLine = "";
		String splitBy = ", ";
		
		try {
			br = new BufferedReader (new FileReader (folderName + "/" + fileName));
			dataLine = br.readLine();
			System.out.println(dataLine);
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
