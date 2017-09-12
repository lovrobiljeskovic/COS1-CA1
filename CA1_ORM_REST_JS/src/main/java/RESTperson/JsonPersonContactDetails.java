/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTperson;

import Entity.Address;
import Entity.InfoEntity;
import Entity.Person;
import Entity.Phone;
import java.util.List;

/**
 *
 * @author Dell
 */
public class JsonPersonContactDetails
{
    private String email;
    private List<Phone> phones;
    private Address address;
    
    public JsonPersonContactDetails(Person p)
    {
        this.email =  p.getEmail() ;
        this.phones = p.getPhones();
        this.address = p.getAddress();
    }
}
