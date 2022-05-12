package CO2Visualizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


    /**
     * This is the main control class for the CO2 Visualizer.
     * @author Menke C. Peters
     */
@Named(value = "controllerBean")
@ApplicationScoped
public class ControllerBean implements Serializable{



    
    
    
    private String citizenSelectionCountry = "Aruba"; //
    private String citizenValue ="";
    private String politicianSelectionCountry = "Aruba";
    private String ngoSelectionYear = "1960";
    private ArrayList<String> countries = new ArrayList<String>();
    private ArrayList<String> years = new ArrayList<String>();    
    private ArrayList<Dataset> databasePolitician = new ArrayList<Dataset>();
    private ArrayList<Dataset> databaseNGO = new ArrayList<Dataset>();
    private ArrayList<ArrayList<String>> database;

    
    
    /**
     * 
     * @return years ArrayList filled with a list of all year in the database. Is used to fill the pulldown menu in the NGO Worker tab.
     *      * 
     */
    public ArrayList<String> getYears() {
        return years;
    }
    /**
     * 
     * Goes through the first row of the database and puts every year (first year starts at position 4) into the years ArrayList.
     *      * 
     */
    public void setYears() {
                for (int i = 4; i < database.get(0).size(); i++) {
            years.add(database.get(0).get(i)); 
        }
        
    }
    
    /**
     * 
     * @return politicianSelectionCountry The Country that is selected in the pulldown menu of the Politician tab. 
     */
    public String getPoliticianSelectionCountry() {
        return politicianSelectionCountry;
    }
    
    /**
     * 
     * @param politicianSelectionCountry The Country that is selected in the pulldown menu of the Politician tab. Writes it in the class attribute. Also starts the creation of the databasePolitician, matching the current politicianSelectionCountry.
     */
    public void setPoliticianSelectionCountry(String politicianSelectionCountry) {
        this.politicianSelectionCountry = politicianSelectionCountry;
        getDatabasePolitician();
    }
    
    /**
     * 
     * @return ControllerBean A List containing all Dataset objects matching the current politicianSelectionCountry. So, the elements are of one country for all years.
     */
    public ArrayList<Dataset> getDatabasePolitician() {
        
        databasePolitician.clear(); // clear old database
        
        for(int i = 1; i <database.size(); i++){ //Advance through the database rows
            
            String indexCountry = database.get(i).get(0); //get the country of the current row
            
            if(indexCountry.equals(politicianSelectionCountry)){// If the currently selected politicianSelectionCountry matches the country in the row, the correct row is reached
                
                for(int j = 4; j < database.get(i).size(); j++){ // Advance over all columns of the row starting from position 4
                
                databasePolitician.add(new Dataset(database.get(i).get(0), database.get(0).get(j), database.get(i).get(j))); // For all columns j in row i create a new Dataset object containg the attributs land, year and valuem and add it to the databasePolitician.
                }
                
            
            }
        }        
        return databasePolitician; // reture
    }

    /**
     * 
     * @return ngoSelectionYear The currently selected year in the NGO Worker tab.
     */
    public String getNgoSelectionYear() {
        return ngoSelectionYear;
    }
    
    /**
     * 
     * @param ngoSelectionYear The currently selected year in the NGO Worker tab. Also starts the creation of the databaseNGO, matching the current ngoSelectionYear.
     */
    public void setNgoSelectionYear(String ngoSelectionYear) {
        this.ngoSelectionYear = ngoSelectionYear;
        getDatabaseNGO();
    }
    
    /**
     * 
     * @return DatabaseNGO A List containing all DatasetNGO objects matching the current ngoSelectionYear. So all elements are of one year for all countries.
     */
    public ArrayList<Dataset> getDatabaseNGO() {

    databaseNGO.clear();

    for(int i = 1; i <database.size(); i++){
        for(int j = 4; j < database.get(i).size(); j++){

        String indexYear = database.get(0).get(j);

        if(indexYear.equals(ngoSelectionYear)){ 


            databaseNGO.add(new Dataset(database.get(i).get(0), database.get(0).get(j), database.get(i).get(j))); // country, year, value
            }


        }
    }        
    return databaseNGO;
    }
    
    /**
     * 
     * @return citizenSelectionCountry The country last selected in the pulldown menu of the Citizen tab.
     */

    public String getCitizenSelectionCountry() {
        return citizenSelectionCountry;
    }
    
    /**
     * 
     * @param citizenSelectionCountry The country selected in the pulldown menu of the Citizen tab.
     */
    public void setCitizenSelectionCountry(String citizenSelectionCountry) {
        this.citizenSelectionCountry = citizenSelectionCountry;
    }
    
    /**
     * 
     * @return citizenValue The latest value of the citizenSelectionCountry. 
     */
    public String getCitizenValue() {
        
            String lastValue;
            lastValue = "";
            
        for (int i = 0; i < database.size(); i++) // Advance through the database rows
        {
            String tempcountry = database.get(i).get(0); // get the country of the current row

            
            if(tempcountry.equals(citizenSelectionCountry)){ // if the current country matches the citizenSelectionCountry 
                for(int j = database.get(i).size()-1;j >0; j--){ // Advance through the database row starting at the end
                    if(!"0".equals(database.get(i).get(j))){ // If the current value is not "0"
                    lastValue = database.get(i).get(j); //get the value
                    break; // then leave the loop
                    }
                }                
            }
                       
        }
        
        if(lastValue.equals("EN.ATM.CO2E.KT")){ // if the last value matches the String, then there is no useful data in the entire row.
            lastValue = "no Data"; // replace the value with no data
        }
            return lastValue;

    }
    
    /**
     * Fills the ArrayList with all countries in the database. 
     */
    public void setCountries(){
        
        for (int i = 1; i < database.size(); i++) {
            countries.add(database.get(i).get(0)); 
        }    
    }
    
    /**
     * 
     * @return countries A list of all countries in the database. The List is used for the drop down menu in the Politician and Citizen tab. 
     */
    public List<String> getCountries(){
        return this.countries;
    }
    
   
    /**
     * Creates a new instance of Database
     */
    public ControllerBean() {
        database = DataPreparation.getDatabase(); // start the database preparation once on startup

        setYears(); // call once at startup
        setCountries(); // call once at startup
  
    }
    
}
