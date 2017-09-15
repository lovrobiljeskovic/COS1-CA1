/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import Entity.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author Dell
 */


public class JSONPersonConverter {
    
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

        public static String getJSONFromPerson(Person p) {
            return gson.toJson(p);
        }

}
