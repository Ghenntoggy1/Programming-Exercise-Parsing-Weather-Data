# Programming-Exercise-Parsing-Weather-Data
Assignments from OOP Course on Java Programming, week 3. https://www.coursera.org/learn/java-programming/supplement/wkC85/programming-exercise-parsing-weather-data.

PROJECT TITLE: "Programming Exercise: Parsing Weather Data"

PURPOSE OF PROJECT: Use of CSV Parser to manage data in different datasets that contain
                    information about weather for 2012-2015 years. Several tasks had
                    to be done: 1) find coldest temperature in the file and all the 
                    information about the coldest temperature; 2) find coldest temperature 
                    in multiple file, name of the file with lowest temperature, the value
                    of this temperature and all the temperatures in that CSV file;
                    3) find the lowest humidity in file and the time the lowest humidity 
                    occurred; 4) find the lowest humidity in multiple files and the time 
                    the lowest humidity occurred; 5) find the average temperature in the file;
                    6) find the average temperature of only those temperatures when the humidity 
                    was greater than or equal to a certain value.
                    
DATE: 17.07.2023

HOW TO START THIS PROJECT: Use BlueJ Environment to open project named "package.bluej". 
                           Find inside of this project 1 class: 
                           CVSMin - compile, create object of type CVSMin 
                           and start one of the following functions:
                           "testColdestHourInFile" for task 1;
                           "testFileWithColdestTemperature" for task 2;
                           "testLowestHumidityInFile" for task 3;
                           "testLowestHumidityInManyFiles" for task 4;
                           "testAverageTemperatureInFile" for task 5;
                           "testAverageTemperatureWithHighHumidityInFile" for task 6;
                           and select one CSV file from Datasets folder;
                           
AUTHOR: Gusev Roman

USER INSTRUCTIONS: you will need two libraries, "edu.dule.*" (simplified
                   version of different functions from Java) and
                   "org.apache.commons.csv.*" (CSV Parser)
