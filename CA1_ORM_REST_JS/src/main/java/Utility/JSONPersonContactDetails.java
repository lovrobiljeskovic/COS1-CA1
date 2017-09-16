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
    private String firstName;
    private String lastName;
    private String email;
    private List<Phone> phones;
    private Address address;
    
    public JSONPersonContactDetails(Person p)
    {
        this.id = p.getId();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.email =  p.getEmail() ;
        this.phones = p.getPhones();
        this.address = p.getAddress();
    }
}
