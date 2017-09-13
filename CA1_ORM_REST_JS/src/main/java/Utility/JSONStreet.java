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
public class JSONStreet {
    private String street;

    public JSONStreet(Address address) {
        this.street = address.getStreet();
    }
    
}
