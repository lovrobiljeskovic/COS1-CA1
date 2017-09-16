/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import Entity.Company;
import Entity.Phone;
import java.util.List;

/**
 *
 * @author Lovro
 */
public class JSONCompanyContactDetails {
    private String email;
    private int phone;
    private String address;
    private String name;

    public JSONCompanyContactDetails(Company company) {
        this.email = company.getEmail();
        this.phone = company.getPhones().get(0).getNumber();
        this.address = company.getAddress().getStreet() + " " + company.getAddress().getAddSitionalInfo() + " " + company.getAddress().getCityInfo().getCity() + " "+ company.getAddress().getCityInfo().getZipCode();
        this.name = company.getName();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
