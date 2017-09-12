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
    
    List<String> personFirstNames = new ArrayList() {
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
    
    List<String> lastNames = new ArrayList() {
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
    
    List<Hobby> hobbies = new ArrayList() {
        {
            
        }
    };
       
    public String generateJSON(int count) {
        Random random = new Random();
        List<Person> persons = new ArrayList();
        for (int i = 0; i < count; i++) {
            persons.add(new Person(
                personFirstNames.get(random.nextInt(personFirstNames.size())), 
                lastNames.get(random.nextInt(lastNames.size()))));
            
        }
        return gson.toJson(persons);
    }
    
}
