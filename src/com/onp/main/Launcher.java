package com.onp.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.onp.util.DataEntry;

public class Launcher {

	private static ArrayList<DataEntry> dataEntryList;
	private static DataEntry dataEntryObj;
	private static ArrayList<String> tmpFeatures;
	private static String[] lineArray;
	private static int counter;
	
	public static void main(String[] args) {
		System.out.println("Starting Main");
		
		Launcher objLauncher = new Launcher();
		dataEntryList = new ArrayList<DataEntry>();
		counter = 0;
		objLauncher.loadFile();
		objLauncher.check();
	}

	private void loadFile() {
		
		String folderName = "resources";
		String fileName = "OnlineNewsPopularity.csv";
		BufferedReader br = null;
		String dataLine = "";
		String splitBy = ", ";
		
		try {
			br = new BufferedReader (new FileReader (folderName + "/" + fileName));
			System.out.println("Loading successful \n\nReading file...");
			while((dataLine = br.readLine()) != null) {
				counter++;
				dataEntryObj = new DataEntry();
				tmpFeatures = new ArrayList<String>();
				lineArray = dataLine.split(splitBy);
				dataEntryObj.setName(lineArray[0]);
				for (int i = 1; i < lineArray.length - 1; i++) {
					tmpFeatures.add(lineArray[i]);
				}
				dataEntryObj.setFeatures(tmpFeatures);
				dataEntryObj.setLabel(lineArray[lineArray.length - 1]);
				dataEntryList.add(dataEntryObj);
			}
			System.out.println("Reading complete. Number of lines read : " + counter);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void check() {
		System.out.println(dataEntryList.get(0).getName());
		dataEntryObj = dataEntryList.get(1);
		System.out.println(dataEntryObj.getLabel());
		dataEntryObj = dataEntryList.get(dataEntryList.size()-1);
		System.out.println(dataEntryObj.getName());
	}

}
