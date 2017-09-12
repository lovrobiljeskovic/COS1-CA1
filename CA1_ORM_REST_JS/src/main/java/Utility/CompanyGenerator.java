/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import Entity.Address;
import Entity.CityInfo;
import Entity.Company;
import Entity.Hobby;
import Entity.Phone;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author thomasthimothee
 */
public class CompanyGenerator {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Random random = new Random();
    private List<String> names = new ArrayList();
    
    private List<String> firstParts = new ArrayList() {
        {
            add("Super");
            add("Danish");
            add("French");
            add("Awesome");
            add("Bijle");
            add("White");
            add("Black");
            add("Fly");
            add("Close");
            add("Rouge");
            add("Bleu");
            add("Blanc");
            add("Jaune");
            add("Vert");
            add("Grøn");
            add("Guld");
            add("Rød");
            add("Hvide");
            add("Violet");
            add("Orange");
            add("Mette");
            add("Wanig");
            add("Gaspard");
            add("Cosmo");
        }
    };

    private List<String> secondParts = new ArrayList() {
        {
            add("INC");
            add("Company");
            add("SA");
            add("Flight");
            add("Powder");
            add("Car");
            add("Rental");
            add("& SON");
            add("Candies");
            add("Sport");
            add("Bar");
            add("Pub");
            add("Restaurant");
            add("Franchise");
            add("Fries");
            add("Hotel");                           
        }
    };
    
        private List<String> descriptions = new ArrayList() {
        {
            add("sells car");
            add("sells paintings");
            add("sells everything");
            add("retailer");
            add("music producer");
            add("fitness club");
            add("candy shop");
            add("social network");
            add("marketing agency");
            add("telecom company");
            add("money laundring front");
        }
    };
    
    private List<CityInfo> cities = new ArrayList() {
        {
           add(new CityInfo("5932","Humble"));
           add(new CityInfo("5935", "Bagenkop"));
           add(new CityInfo("7490", "Aulum"));
           add(new CityInfo("6040", "Egtved"));
           add(new CityInfo("6051", "Almind"));
           add(new CityInfo("7300", "Jelling"));
           add(new CityInfo("6660", "Lintrup"));
           add(new CityInfo("6823", "Ansager"));
           add(new CityInfo("6855", "Outrup"));
           add(new CityInfo("7140", "Stouby"));          
        }
    };
    
    private List<String> zipcodes = new ArrayList() {
        {
            add("Jepsen");
            add("Biljeskovic");
            add("Thimothee");
            add("Catana");
            add("Fenger");
            add("Mihok");
            add("Petersen");
            add("Gadegaard");
            add("Jobs");
            add("Wozniacki");
            add("Larsen");
        }
    };
    
    private List<String> streets = new ArrayList(){
        {
            add("Kongevej");
            add("langgade");
            add("roskildevej");
            add("vesterbrogade");
            add("osterbrogade");
            add("norrebrogade");
            add("amelievej");
            add("englandvej");
            add("parisgade");
        }
    };
    
    private List<String> additionalInfo = new ArrayList(){
        {
            add("nummer 1");
            add("nummer 2");
            add("nummer 3");
            add("nummer 4");
            add("nummer 5");
            add("nummer 6");
            add("nummer 7");
            add("nummer 8");
            add("nummer 9");
            add("nummer 10");
        }
    };
    
    private List<Phone> phones = new ArrayList();

    private List<Hobby> hobbies = new ArrayList();
    
    private List<Address> addresses = new ArrayList();
    
    private void createNames(int count) {
        for (int i=0; i<count; i++){
            String name = firstParts.get(random.nextInt(firstParts.size())) + " "+ secondParts.get(random.nextInt(secondParts.size()));
            names.add(name);
        }
    }
    
    private void createPhoneNumbers(int count, int startNb){
        int number = startNb;
        for (int i = 0 ; i < count; i++){
            Phone ph = new Phone();
            ph.setNumber(number);           
            ph.setDescription("the number is:" + number);
            number++;
            phones.add(ph);
        }
    }
    
    private void createAddresses(){
        for (int i = 0; i < cities.size(); i++){
            Address ad = new Address();
            ad.setCityInfo(cities.get(random.nextInt(cities.size())));
            ad.setAddSitionalInfo(additionalInfo.get(random.nextInt(additionalInfo.size())));
            ad.setStreet(streets.get(random.nextInt(streets.size())));
            addresses.add(ad);
        }
        
    }
    
    public String generateJSON(int count, int startingPhoneNumber, int startingCVR) { 
        int cvr = startingCVR;
        createNames(count);
        createPhoneNumbers(count, startingPhoneNumber);
        createAddresses();
        List<Company> companies = new ArrayList();
        for (int i = 0; i < count; i++) {
            Company c = new Company();
            c.setName(names.get(random.nextInt(names.size())));
            c.setCvr(String.valueOf(cvr));
            c.setDescription(descriptions.get(random.nextInt(descriptions.size())));
            c.setNumEmployees(random.nextInt((1000-5)+1) + 5);
            c.setMarketValue(random.nextInt((10000000-10000)+1) + 10000);
            c.setAddress(addresses.get(i));
            cvr++;
            companies.add(c);
        }
        return gson.toJson(companies);
    }    
}
