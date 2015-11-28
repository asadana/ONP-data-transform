package com.onp.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.onp.util.DataEntry;

/**
 * Launcher : Main class to parse and transform our database
 * 
 * Project: Online News Popularity
 * Class: I-590 (Fall 2015)
 * @author asadana, voraj
*/
public class Launcher {

	// List to contain all data examples
	private static ArrayList<DataEntry> dataEntryList;
	// Temporary object to reference current DataEntry object
	private static DataEntry dataEntryObj;
	// Temporary object to reference current DataEntry object's features ArrayList
	private static ArrayList<String> tmpFeatures;
	// Temporary object to store and compare string
	private String checkString;
	
	// Folder name in local workspace which contains the source csv file
	// Also the folder where the new csv file will be generated
	private String folderName = "resources";
	// Source csv file
	private String inputFileName = "OnlineNewsPopularity.csv";
	// Target output csv file
	private String outputFileName = "OnlineNewsPopularity-" + System.currentTimeMillis() + ".csv";
	// Threshold to split shares into popular or not
	private int labelThreshold = 1400;
	
	// main function call
	public static void main(String[] args) {
		System.out.println("Starting Main");
		
		Launcher objLauncher = new Launcher();
		dataEntryList = new ArrayList<DataEntry>();
		
		// Loading file
		objLauncher.loadFile();
		objLauncher.printPart();
		objLauncher.check();
		objLauncher.printPart();
		// Merging weekday_is_* into weekdays
		objLauncher.mergeDays();
		objLauncher.printPart();
		objLauncher.check();
		objLauncher.printPart();
		// Merging data_channel_* into data_channel
		objLauncher.mergeDataChannel();
		objLauncher.printPart();
		objLauncher.check();
		objLauncher.printPart();
		// Replacing shares' values with yes/no
		objLauncher.splitLabels();
		// Writing the new database into a csv file
		objLauncher.writeCSV();
	}
	
	// Function to load the source csv file
	private void loadFile() {
		
		// Variable to read streams from file
		BufferedReader br = null;
		// Temporary variable to read a line at a time
		String dataLine = "";
		// Temporary variable to store dataLine in parts
		String[] lineArray;
		// Delimeter to read the file by
		String splitBy = ", ";
		// Counter variable to count number of lines read
		int counter = 0;
		
		try {
			
			br = new BufferedReader (new FileReader (folderName + "/" + inputFileName));
			System.out.println("Loading successful.\nReading file...");
			
			// Read a line at a time till end of file
			while((dataLine = br.readLine()) != null) {
				counter++;
				dataEntryObj = new DataEntry();
				tmpFeatures = new ArrayList<String>();
				lineArray = dataLine.split(splitBy);
				
				// First entry is the name
				dataEntryObj.setName(lineArray[0]);
				// Next entries before the last are the features
				for (int i = 1; i < lineArray.length - 1; i++) {
					tmpFeatures.add(lineArray[i]);
				}
				dataEntryObj.setFeatures(tmpFeatures);
				// Last entry is the label
				dataEntryObj.setLabel(lineArray[lineArray.length - 1]);
				
				// Adding the DataEntry to the list
				dataEntryList.add(dataEntryObj);
			}
			System.out.println("Reading complete.\nNumber of lines read : " + counter);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 *  Function to display,
	 		*	the size of the dataset
	 		*	the number of features 
	 		*	names of the features
	 */
	private void check() {
		System.out.println("Size of the data: " + dataEntryList.size());
		System.out.println("Number of features: " + dataEntryList.get(0).getFeatures().size());
		System.out.println(dataEntryList.get(0).getFeatures());
	}

	// Function to merge weekday_is_* into weekdays
	private void mergeDays() {
		// Variable to store feature of the index where we merge
		int featureIndex = 0;
		// Get the title entry
		dataEntryObj = dataEntryList.get(0);
		// Loops through features to find weekday_is_monday
		for(int i = 0; i < dataEntryObj.getFeatures().size(); i++) {
			if(dataEntryObj.getFeatures().get(i).contains("monday")) {
				featureIndex = i;
				System.out.println(dataEntryObj.getFeatures().get(i) + "\nFeatureIndex: " + featureIndex);
				dataEntryObj.getFeatures().set(featureIndex, "weekday");
				System.out.println("Renaming weekday_is_monday to: " + dataEntryObj.getFeatures().get(i));
			}
		}
		
		// For every 1 in weekday_* column, the name of the day is added in the first column
		for(int i = 1; i < dataEntryList.size(); i ++) {
			dataEntryObj = dataEntryList.get(i);
			if (featureIndex != 0) {
				// Starts on monday, ends at sunday
				for(int j = featureIndex; j < featureIndex + 7; j++) {
					// for Monday
					if(j == featureIndex) {
						checkString = dataEntryObj.getFeatures().get(j);
						if (checkString.compareTo("1.0") == 0) {
							dataEntryObj.getFeatures().set(featureIndex, "Monday");
						}
					}
					// for Tuesday
					if(j == featureIndex + 1) {
						checkString = dataEntryObj.getFeatures().get(j);
						if (checkString.compareTo("1.0") == 0) {
							dataEntryObj.getFeatures().set(featureIndex, "Tuesday");
						}
					}
					// for Wednesday
					if(j == featureIndex + 2) {
						checkString = dataEntryObj.getFeatures().get(j);
						if (checkString.compareTo("1.0") == 0) {
							dataEntryObj.getFeatures().set(featureIndex, "Wednesday");
						}
					}
					// for Thursday
					if(j == featureIndex + 3) {
						checkString = dataEntryObj.getFeatures().get(j);
						if (checkString.compareTo("1.0") == 0) {
							dataEntryObj.getFeatures().set(featureIndex, "Thursday");
						}
					}
					// for Friday
					if(j == featureIndex + 4) {
						checkString = dataEntryObj.getFeatures().get(j);
						if (checkString.compareTo("1.0") == 0) {
							dataEntryObj.getFeatures().set(featureIndex, "Friday");
						}
					}
					// for Saturday
					if(j == featureIndex + 5) {
						checkString = dataEntryObj.getFeatures().get(j);
						if (checkString.compareTo("1.0") == 0) {
							dataEntryObj.getFeatures().set(featureIndex, "Saturday");
						}
					}
					// for Sunday
					if(j == featureIndex + 6) {
						checkString = dataEntryObj.getFeatures().get(j);
						if (checkString.compareTo("1.0") == 0) {
							dataEntryObj.getFeatures().set(featureIndex, "Sunday");
						}
					}
				}
			}
		}
		
		// from monday, next 6 columns need to be deleted
		deleteFeatures(featureIndex, 6);
	}
	
	// Function to merge data_channel_* to data_channel
	private void mergeDataChannel() {
		// Variable to store feature of the index where we merge
		int featureIndex = 0;
		// Get the title entry 
		dataEntryObj = dataEntryList.get(0);
		// Loops through features to find data_channel_lifestyle
		for(int i = 0; i < dataEntryObj.getFeatures().size(); i++) {
			if(dataEntryObj.getFeatures().get(i).contains("lifestyle")) {
				featureIndex = i;
				System.out.println(dataEntryObj.getFeatures().get(i) + "\nFeatureIndex: " + featureIndex);
				dataEntryObj.getFeatures().set(featureIndex, "data_channel");
				System.out.println("Renaming data_channel_is_lifestyle to: " + dataEntryObj.getFeatures().get(i));
			}
		}
		
		// For every 1 in data_channel_* column, the name of the data channel is added in the first column
		for(int i = 1; i < dataEntryList.size(); i ++) {
			dataEntryObj = dataEntryList.get(i);
			if (featureIndex != 0) {
				// Starts on lifestyle, ends at world
				for(int j = featureIndex; j < featureIndex + 6; j++) {
					// for Lifestyle
					if(j == featureIndex) {
						checkString = dataEntryObj.getFeatures().get(j);
						if (checkString.compareTo("1.0") == 0) {
							dataEntryObj.getFeatures().set(featureIndex, "Lifestyle");
						}
						else if (checkString.compareTo("0.0") == 0) {
							dataEntryObj.getFeatures().set(featureIndex, "Unknown");
						}
					}
					// for Entertainment
					if(j == featureIndex + 1) {
						checkString = dataEntryObj.getFeatures().get(j);
						if (checkString.compareTo("1.0") == 0) {
							dataEntryObj.getFeatures().set(featureIndex, "Entertainment");
						}
					}
					// for Business
					if(j == featureIndex + 2) {
						checkString = dataEntryObj.getFeatures().get(j);
						if (checkString.compareTo("1.0") == 0) {
							dataEntryObj.getFeatures().set(featureIndex, "Business");
						}
					}
					// for Social Media
					if(j == featureIndex + 3) {
						checkString = dataEntryObj.getFeatures().get(j);
						if (checkString.compareTo("1.0") == 0) {
							dataEntryObj.getFeatures().set(featureIndex, "SocialMedia");
						}
					}
					// for Tech
					if(j == featureIndex + 4) {
						checkString = dataEntryObj.getFeatures().get(j);
						if (checkString.compareTo("1.0") == 0) {
							dataEntryObj.getFeatures().set(featureIndex, "Technology");
						}
					}
					// for World
					if(j == featureIndex + 5) {
						checkString = dataEntryObj.getFeatures().get(j);
						if (checkString.compareTo("1.0") == 0) {
							dataEntryObj.getFeatures().set(featureIndex, "World");
						}
					}
				}
			}
		}
		
		// from monday, next 5 columnds need to be deleted
		deleteFeatures(featureIndex, 5);
	}
	
	// Function to split shares into Yes/No
	private void splitLabels() {
		String yesString = "Yes";
		String noString = "No";
		int tmpLabel;
		
		for(int i = 1; i < dataEntryList.size() ; i++) {
			dataEntryObj = dataEntryList.get(i);
			tmpLabel = Integer.parseInt(dataEntryObj.getLabel());
			
			if (tmpLabel >= labelThreshold) {
				dataEntryObj.setLabel(yesString);
			}
			else if (tmpLabel < labelThreshold) {
				dataEntryObj.setLabel(noString);
			}
			else {
				System.out.println("Error. Label value: " + dataEntryObj.getLabel());
			}
		}
	}
	
	// Function to delete a set of columns by giving seed (featureIndex) and iterations (numberOfForwardDeletes)
	private void deleteFeatures(int featureIndex, int numberOfForwardDeletes) {
		System.out.println("Removing features");
		for(DataEntry tmpDataEntry : dataEntryList) {
			for(int i = featureIndex + 1; i <= (featureIndex + numberOfForwardDeletes); i++) {
				tmpDataEntry.getFeatures().remove(featureIndex + 1);
			}
		}
	}
	
	// Function to write the new database into csv file
	private void writeCSV() {
		String splitBy = ", ";
		FileWriter outputFile = null;
		try {
			
			outputFile = new FileWriter(folderName + "/" + outputFileName);
			System.out.println("Creating a file: " + outputFileName);
			System.out.println("Writing to file..");
			
			// Traversing the dataEntryList
			for(DataEntry tmpData : dataEntryList) {
				// commented out Name to remove URL column
				//outputFile.append(tmpData.getName());
				//outputFile.append(splitBy);
				
				// Traversing the feature list
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
				System.out.println("Flushing out file");
				outputFile.flush();
				outputFile.close();
				System.out.println("All done");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	// Function to print a divider in console
	private void printPart() {
		System.out.println("\n\n=============================================\n\n");
	}
}
