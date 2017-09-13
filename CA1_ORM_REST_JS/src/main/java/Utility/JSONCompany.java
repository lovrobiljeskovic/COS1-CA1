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
public class JSONCompany {
    private String email;
    private List<Phone> phones;

    public JSONCompany(Company company) {
        this.email = company.getEmail();
        this.phones = company.getPhones();
    }
    
}
