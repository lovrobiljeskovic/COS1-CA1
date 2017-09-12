package Utility;

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
    
    List<String> firstNames = new ArrayList() {
        {
            add("Mathias");
            add("Lovro");
            add("Thomas");
            add("Peter");
            add("Petru");
            add("Patrick");
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
        }
    };
       
    public String generateJSON(int count) {
        Random random = new Random();
        List<Person> persons = new ArrayList();
        for (int i = 0; i < count; i++) {
            persons.add(new Person(
                firstNames.get(random.nextInt(firstNames.size())), 
                lastNames.get(random.nextInt(lastNames.size()))));
            
        }
        return gson.toJson(persons);
    }
    
}
