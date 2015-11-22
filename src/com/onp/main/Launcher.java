package com.onp.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.onp.util.DataEntry;

public class Launcher {

	private static ArrayList<DataEntry> dataEntryList;
	private static DataEntry dataEntryObj;
	private static ArrayList<String> tmpFeatures;
	private static String[] lineArray;
	private static int counter;
	private String checkString;
	private String folderName = "resources";
	private String inputFileName = "OnlineNewsPopularity.csv";
	private String outputFileName = "OnlineNewsPopularity-" + System.currentTimeMillis() + ".csv";
	
	public static void main(String[] args) {
		System.out.println("Starting Main");
		
		Launcher objLauncher = new Launcher();
		dataEntryList = new ArrayList<DataEntry>();
		counter = 0;
		objLauncher.loadFile();
		objLauncher.check();
		//objLauncher.mergeDays();
		//objLauncher.writeCSV();
	}
	
	private void loadFile() {
		
		BufferedReader br = null;
		String dataLine = "";
		String splitBy = ", ";
		
		try {
			br = new BufferedReader (new FileReader (folderName + "/" + inputFileName));
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
		/*System.out.println(dataEntryList.get(0).getName());
		dataEntryObj = dataEntryList.get(1);
		System.out.println(dataEntryObj.getLabel());
		dataEntryObj = dataEntryList.get(dataEntryList.size()-1);
		System.out.println(dataEntryObj.getName());*/
		System.out.println(dataEntryList.get(0).getFeatures());
	}

	public void mergeDays() {
		int featureIndex = 0;
		dataEntryObj = dataEntryList.get(0);
		for(int i = 0; i < dataEntryObj.getFeatures().size(); i++) {
			if(dataEntryObj.getFeatures().get(i).contains("monday")) {
				featureIndex = i;
				System.out.println(dataEntryObj.getFeatures().get(i) + "\nFeatureIndex: " + featureIndex);
			}
		}
		
		for(int i = 1; i < dataEntryList.size(); i ++) {
			dataEntryObj = dataEntryList.get(i);
			if (featureIndex != 0) {
				// Starts on monday, ends at sunday
				for(int j = featureIndex + 1; j < featureIndex + 7; j++) {
					// for Tuesday
					if(j == featureIndex + 1) {
						checkString = dataEntryObj.getFeatures().get(j);
						if (checkString.compareTo("1.0") == 0) {
							dataEntryObj.getFeatures().set(featureIndex, "2");
						}
					}
				}
			}
		}
	}
	
	public void writeCSV() {
		String splitBy = ", ";
		FileWriter outputFile = null;
		try {
			
			outputFile = new FileWriter(folderName + "/" + outputFileName);
			System.out.println("Creating a file: " + outputFileName);
			System.out.println("Writing to file..");
			for(DataEntry tmpData : dataEntryList) {
				outputFile.append(tmpData.getName());
				outputFile.append(splitBy);
				for (String tmpString : tmpData.getFeatures()) {
					outputFile.append(tmpString);
					outputFile.append(splitBy);
				}
				outputFile.append(tmpData.getLabel());
				outputFile.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				System.out.println("Flusing out file");
				outputFile.flush();
				outputFile.close();
				System.out.println("All done");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
