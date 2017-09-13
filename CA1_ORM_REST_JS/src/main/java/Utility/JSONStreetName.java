/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import Entity.Address;

/**
 *
 * @author thomasthimothee
 */
public class JSONStreetName {
    private String street;

    public JSONStreetName(Address address) {
        this.street = address.getStreet();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    
    
}
