package Utility;

import Entity.Hobby;
import Entity.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author mathiasjepsen
 */
public class Generator {
    
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    private List<String> firstNames = new ArrayList() {
        {
            add("Mathias");
            add("Lovro");
            add("Thomas");
            add("Peter");
            add("Petru");
            add("Patrick");
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
        }
    };
    
    private List<Hobby> hobbies = new ArrayList();
        
    private void createHobbies(){
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
       
    public String generateJSON(int count) {
        createHobbies();
        Random random = new Random();
        List<Person> persons = new ArrayList();
        for (int i = 0; i < count; i++) {
            Person p = new Person(
                firstNames.get(random.nextInt(firstNames.size())), 
                lastNames.get(random.nextInt(lastNames.size())));
            
            List<Hobby> personHobby =new ArrayList();
            personHobby.add(hobbies.get(random.nextInt(hobbies.size())));
            personHobby.add(hobbies.get(random.nextInt(hobbies.size())));
            
            while(personHobby.get(0).equals(personHobby.get(1))){
                personHobby.remove(1);
                personHobby.add(hobbies.get(random.nextInt(hobbies.size())));
            }
            p.setHobbies(personHobby);
        }
        return gson.toJson(persons);
    }
    
}
