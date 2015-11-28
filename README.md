ONP-data-transform
===================

Online News Popularity Project for I590
----------

This is a JAVA parser program to read and transform the [Online News Popularity Database](https://archive.ics.uci.edu/ml/datasets/Online+News+Popularity) in order to reduce and transform the feature columns to make the plots more readable and interpretable for our Project.

Need to know things:
-------------
1. The program expects to find the dataset in resources/ folder
2.  src.com.onp.main.Launcher.java is the main file to execute from.
3.  src.com.onp.util.DataEntry.java is the outline of we store data for each data entry.
4. main() contains all the essential calls.
5. Function call objLauncher.loadFile() : Loads the csv file in DataEntry obj format
6. Function call objLauncher.mergeDays() : Merges weekday_is_* into weekday feature. Replaces 1/0 with actual Day names for plot interpretability
7. Function call objLauncher.mergeDataChannel() : Merges data_channel_is_* into data_channel feature. Replaces 1/0 with actual Day names for plot interpretability
8. Function call objLauncher.splitLabels() : Transforms the numerical label column (shares) to Yes/No using the provided threshold for popularity.
9. Function call objLauncher.writeCSV() : Writes the new database after transformations into a CSV file.

How to run:
-------------
0. Ensure that the databse is in resources/
1. Comment/Uncomment any of the functin calls in Launcher.java, depending on the need for transformation
2. Execute Launcher.java
3. Transformed database will be generated in resources/ with the current milli-seconds appended to the name
