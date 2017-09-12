/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import Entity.Company;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;

public class JSONcompanyConverter {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();


        public static Company getCompanyFromJson(String js) {
            return gson.fromJson(js, Company.class);
        }

        public static String getJSONFromPet(Company c) {
            return gson.toJson(c);
        }

        public static String getJSONFromPet(List<Company> companies) {
            return gson.toJson(companies);
        }

    

}