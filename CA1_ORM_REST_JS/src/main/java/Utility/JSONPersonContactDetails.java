/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import Entity.Address;
import Entity.InfoEntity;
import Entity.Person;
import Entity.Phone;
import java.util.List;

/**
 *
 * @author Dell
 */
public class JSONPersonContactDetails
{   
    private int id;
    private String email;
    private int phone;
    private String address;
    
    public JSONPersonContactDetails(Person p)
    {
        this.id = p.getId();
        this.email =  p.getEmail() ;
        this.phone = p.getPhones().get(0).getNumber();
        this.address = p.getAddress().getStreet() + " " + p.getAddress().getAddSitionalInfo() + " " + p.getAddress().getCityInfo().getCity() + " " + p.getAddress().getCityInfo().getZipCode();
        
    }
}
