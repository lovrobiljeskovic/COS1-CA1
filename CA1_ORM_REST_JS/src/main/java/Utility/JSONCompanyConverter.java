/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import Entity.Company;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;

public class JSONCompanyConverter {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();


        public static Company getCompanyFromJson(String js) {
            return gson.fromJson(js, Company.class);
        }
        
        public static List<Company> getCompaniesFromJson(String js) {
            return gson.fromJson(js, CompanyList.class);
        }

        public static String getJSONFromCompany(Company c) {
            return gson.toJson(c);
        }

        public static String getJSONFromCompanies(List<Company> companies) {
            return gson.toJson(companies);
        }

    

}