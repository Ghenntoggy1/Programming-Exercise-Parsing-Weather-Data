import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMin {
    public CSVRecord getSmallestOfTwo (CSVRecord currRow, CSVRecord smallestSoFar) {
        if (smallestSoFar == null) {
            smallestSoFar = currRow;
        }
        else {
            double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if (currTemp < smallestTemp && currTemp != -9999) {
                smallestSoFar = currRow;
            }
        }
        return smallestSoFar;
    }
    
    public CSVRecord coldestHourInFile (CSVParser parser) {
        CSVRecord smallestSoFar = null;
        for (CSVRecord currRow : parser) {
            smallestSoFar = getSmallestOfTwo(currRow, smallestSoFar);
        }
        return smallestSoFar;
    }
    
    public String fileWithColdestTemperature () {
        CSVRecord smallestSoFar = null;
        String name = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currRow = coldestHourInFile(fr.getCSVParser());
            if (smallestSoFar == null) {
                smallestSoFar = currRow;
            }
            else {
                double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                if (currTemp < smallestTemp && currTemp != -9999 && smallestTemp != -9999) {
                    smallestSoFar = currRow;
                    name = f.getAbsolutePath();
                 }
            }
        }
        return name;
    }
    
    public CSVRecord checkLowestHumidity (CSVRecord currRow, CSVRecord lowestSoFar) {
        if (lowestSoFar == null) {
            lowestSoFar = currRow;
        }
        else {
            if (!currRow.get("Humidity").contains("N/A")) {
                double currHum = Double.parseDouble(currRow.get("Humidity"));
                double lowestHum = Double.parseDouble(lowestSoFar.get("Humidity"));
                if (currHum < lowestHum) {
                    lowestSoFar = currRow;
                }
            }
        }
        return lowestSoFar;
    }
    
    public CSVRecord lowestHumidityInFile (CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for (CSVRecord currRow : parser) {
            lowestSoFar = checkLowestHumidity(currRow, lowestSoFar);
        }
        return lowestSoFar;
    }
    
    public CSVRecord lowestHumidityInManyFiles () {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currRow = lowestHumidityInFile(fr.getCSVParser());
            if (smallestSoFar == null) {
                smallestSoFar = currRow;
            }
            else {
                double currHum = Double.parseDouble(currRow.get("Humidity"));
                double smallestHum = Double.parseDouble(smallestSoFar.get("Humidity"));
                if (currHum < smallestHum) {
                    smallestSoFar = currRow;
                 }
            }
        }
        return smallestSoFar;
    }
    
    public double averageTemperatureInFile (CSVParser parser) {
        double sum = 0;
        int numDays = 0;
        for (CSVRecord currRow : parser) {
            double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
            if (currTemp != -9999) {
                sum += currTemp;
                numDays++;
            }
        }
        return sum / numDays;
    }
    
    public double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value) {
        double sum = 0;
        int numDays = 0;
        for (CSVRecord currRow : parser) {
            if (currRow.get("Humidity") != "N/A") {
                double currHum = Double.parseDouble(currRow.get("Humidity"));
                if (currHum >= value) {
                    double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
                    sum += currTemp;
                    numDays++;
                }
            }
        }
        if (numDays == 0) {
            return -9999;
        }
        return sum / numDays;
    }
    
    public void testColdestHourInFile () {
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        
        System.out.println("Coldest Temperature was " + smallest.get("TemperatureF") +
        " at " + smallest.get("DateUTC"));
    }
    
    public void testFileWithColdestTemperature () {
        String name = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + name.substring(name.lastIndexOf('\\') + 1));
        FileResource fr = new FileResource(name);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        FileResource fr2 = new FileResource(name);
        CSVParser parser2 = fr2.getCSVParser();
        for (CSVRecord currRow : parser2) {
            System.out.println(currRow.get("DateUTC") + ": " + currRow.get("TemperatureF"));
        }
    }
    
    public void testLowestHumidityInFile () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public void testLowestHumidityInManyFiles () {
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public void testAverageTemperatureInFile () {
        FileResource fr = new FileResource();
        double average = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is " + average);
    }
    
    public void testAverageTemperatureWithHighHumidityInFile () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int value = 80;
        double result = averageTemperatureWithHighHumidityInFile(parser, value);
        if (result == -9999) {
            System.out.println("No temperature with that humidity");
        }
        else {
            System.out.println("Average temperature in file is " + result);
        }
    }
}
