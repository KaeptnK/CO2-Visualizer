/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CO2Visualizer;

/**
 * This class creates the objects for the databaseNGO and databasePolitician.
 * @author menke
 */
public class Dataset {
        
    private String land;
    private String year;
    private String value;

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
    public Dataset(String land, String year, String value){
        
        this.land = land;
        this.year = year;
        this.value = value;
    }
    
    
}
