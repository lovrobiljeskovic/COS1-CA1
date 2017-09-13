/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import Entity.Person;
import Entity.Phone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thomasthimothee
 */
public class JSONPerson {
    
    private String firstName;
    private String lastName;
    private List<String> hobbies;
    private String email;
    private List<Integer> phones;
    private String address;

    public JSONPerson(Person person) {
        phones = new ArrayList();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.email = person.getEmail();
        this.address = person.getAddress().getStreet() + " " + person.getAddress().getAddSitionalInfo() + " " + person.getAddress().getCityInfo().getCity() + " "+ person.getAddress().getCityInfo().getZipCode();
            for (Phone ph : person.getPhones()){
                this.phones.add(ph.getNumber());
            }    
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getPhones() {
        return phones;
    }

    public void setPhones(List<Integer> phones) {
        this.phones = phones;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String Address) {
        this.address = Address;
    }
    
    
    
}
