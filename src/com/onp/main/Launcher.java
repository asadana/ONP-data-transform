package com.onp.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.onp.util.DataEntry;

public class Launcher {

	private static ArrayList<DataEntry> dataEntryList;
	private static DataEntry dataEntryObj;
	
	public static void main(String[] args) {
		System.out.println("Starting Main");
		
		Launcher objLauncher = new Launcher();
		dataEntryList = new ArrayList<DataEntry>();
		dataEntryObj = new DataEntry();
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
			//while((dataLine = br.readLine()) != null) {
			dataLine = br.readLine();
			String[] lineArray = dataLine.split(splitBy);
			for (int i = 0; i<lineArray.length; i++) {
				System.out.println(lineArray[i]);
			}
			//}
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
