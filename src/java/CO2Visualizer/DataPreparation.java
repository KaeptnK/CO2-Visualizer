/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CO2Visualizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * This class creates the database out of csv file and arranges it in an ArrayList.
 * @author menke
 */
public class DataPreparation {
    
        /**
         * 
         * @return database A two-dimensional ArrayList containing ArrayList elements containing Strings. In row 0 these will be headlines, in all others these will be details for every country. 
         */
	public static ArrayList<ArrayList<String>> getDatabase()
	{
            try
            {
                    ArrayList< ArrayList<String> > database = new ArrayList<>();//list of lists to store data //create the nested ArrayList
                    String sep = File.separator;
                    String file = "C:" + sep + "Users" +sep+ "menke" +sep + "OneDrive - IUBH Internationale Hochschule"+ sep + "02-Kurse" + sep + "2.Semester" +sep+ "IPWA" +sep+ "IPWA-01" + sep + "FallStudie" +sep+ "datafile.csv"; //path where the csv file is located
                    FileReader fr = new FileReader(file); // Instantiate FileReader
                    BufferedReader br = new BufferedReader(fr); // Instantiate BufferedReader

                    //Reading until running out of lines
                    String line = br.readLine();
                    while(line != null)
                    {
                            List<String> lineData = Arrays.asList(line.split(";"));//splitting lines
                            ArrayList<String> lineData2 = new ArrayList<String>(lineData); // Instantiate a new nested ArrayList for every row of the csv file.
                            database.add(lineData2); // put it in the database
                            line = br.readLine();
                    }

                    br.close(); // close the readers
                    fr.close();
                    return database;
            }
            catch(Exception e) // handle exeception
            {
                    System.out.print(e);
                    return null;
            }
                
	}
}