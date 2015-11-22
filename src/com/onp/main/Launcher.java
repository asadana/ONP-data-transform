package com.onp.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.onp.util.DataEntry;

public class Launcher {

	private static ArrayList<DataEntry> dataEntryList;
	private static DataEntry dataEntryObj;
	private static ArrayList<String> tmpFeatures;
	
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
			tmpFeatures = new ArrayList<String>();
			dataLine = br.readLine();
			String[] lineArray = dataLine.split(splitBy);
			dataEntryObj.setName(lineArray[0]);
			for (int i = 1; i<lineArray.length - 1; i++) {
				tmpFeatures.add(lineArray[i]);
			}
			dataEntryObj.setFeatures(tmpFeatures);
			dataEntryObj.setLabel(lineArray[lineArray.length-1]);
			System.out.println(dataEntryObj.getLabel());
			//}
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
