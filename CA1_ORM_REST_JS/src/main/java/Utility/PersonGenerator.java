package Utility;

import Entity.Address;
import Entity.CityInfo;
import Entity.Hobby;
import Entity.Person;
import Entity.Phone;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author mathiasjepsen
 */
public class PersonGenerator {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private List<String> firstNames = new ArrayList() {
        {
            add("Mathias");
            add("Lovro");
            add("Thomas");
            add("Peter");
            add("Petru");
            add("Patrick");
            add("Hanne");
            add("Flemming");
            add("Lars");
            add("Nicklas");
        }
    };

    private List<String> lastNames = new ArrayList() {
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
    
    private void createHobbies() {
        Hobby h1 = new Hobby();
        Hobby h2 = new Hobby();
        Hobby h3 = new Hobby();
        Hobby h4 = new Hobby();
        Hobby h5 = new Hobby();
        Hobby h6 = new Hobby();
        Hobby h7 = new Hobby();
        Hobby h8 = new Hobby();
        Hobby h9 = new Hobby();
        Hobby h10 = new Hobby();
        h1.setName("football");
        h1.setDescription("round ball");
        h1.setName("handball");
        h1.setDescription("another round ball but small");
        h1.setName("video game");
        h1.setDescription("nerd");
        h1.setName("painting");
        h1.setDescription("boring");
        h1.setName("theatre");
        h1.setDescription("no comment");
        h1.setName("reading");
        h1.setDescription("smart one");
        h1.setName("reality tv");
        h1.setDescription("get a life");
        h1.setName("tennis");
        h1.setDescription("get a racket");
        h1.setName("fussball");
        h1.setDescription("you are an athlete");
        hobbies.add(h1);
        hobbies.add(h2);
        hobbies.add(h3);
        hobbies.add(h4);
        hobbies.add(h5);
        hobbies.add(h6);
        hobbies.add(h7);
        hobbies.add(h8);
        hobbies.add(h9);
        hobbies.add(h10);
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
    
    private void createAddresses(int count, Random random){
        for (int i = 0; i< count; i++){
            Address ad = new Address();
            ad.setCityInfo(cities.get(random.nextInt(cities.size())));
            ad.setAddSitionalInfo(additionalInfo.get(random.nextInt(additionalInfo.size())));
            ad.setStreet(streets.get(random.nextInt(streets.size())));
            addresses.add(ad);
        }
        
    }
    
    public List<Person> generateJSON(int count, int startingPhoneNumber) {
        Random random = new Random();
        createHobbies();
        createPhoneNumbers(count, startingPhoneNumber);
        createAddresses(count, random);
        List<Person> persons = new ArrayList();
        for (int i = 0; i < count; i++) {
            Person p = new Person(
                    firstNames.get(random.nextInt(firstNames.size())),
                    lastNames.get(random.nextInt(lastNames.size())));

            List<Hobby> personHobby = new ArrayList();
            personHobby.add(hobbies.get(random.nextInt(hobbies.size())));
            personHobby.add(hobbies.get(random.nextInt(hobbies.size())));

            while (personHobby.get(0).equals(personHobby.get(1))) {
                personHobby.remove(1);
                personHobby.add(hobbies.get(random.nextInt(hobbies.size())));
            }
            p.setHobbies(personHobby);
            
            p.setAddress(addresses.get(random.nextInt(addresses.size())));
            persons.add(p);
        }
        return persons;//gson.toJson(persons);
    }
}
