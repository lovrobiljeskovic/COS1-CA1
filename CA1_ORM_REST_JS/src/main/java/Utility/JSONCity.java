package Utility;

import Entity.CityInfo;

/**
 *
 * @author thomasthimothee
 */
public class JSONCity {
    
    private String zipCode;

    public JSONCity(CityInfo city) {
        this.zipCode = city.getZipCode();
    }
    
}
