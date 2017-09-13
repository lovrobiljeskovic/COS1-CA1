package Utility;

import Entity.Address;
import Entity.Company;
import static Entity.Company_.cvr;
import Entity.Hobby;
import Entity.Person;
import Entity.Phone;
import Facade.GeneralFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mathiasjepsen
 */
public class TestGenerator {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Random random = new Random();
    GeneralFacade gf = new GeneralFacade();

    public TestGenerator(EntityManagerFactory emf) {
        gf.addEntityManagerFactory(emf);
    }

    private List<String> names = new ArrayList();
    
    private List<Hobby> hobbies = new ArrayList();

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
    
    private void hobbiesList() {
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
        h2.setName("handball");
        h2.setDescription("another round ball but small");
        h3.setName("video game");
        h3.setDescription("nerd");
        h4.setName("painting");
        h4.setDescription("boring");
        h5.setName("theatre");
        h5.setDescription("no comment");
        h6.setName("reading");
        h6.setDescription("smart one");
        h7.setName("reality tv");
        h7.setDescription("get a life");
        h8.setName("tennis");
        h8.setDescription("get a racket");
        h9.setName("fussball");
        h9.setDescription("you are an athlete");
        h10.setName("waterpolo");
        h10.setDescription("become one with the water");
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
        
    private List<String> streets = new ArrayList() {
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

    private List<String> additionalInfo = new ArrayList() {
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

    private void createNames(int count) {
        for (int i = 0; i < count; i++) {
            String name = firstParts.get(random.nextInt(firstParts.size())) + " " + secondParts.get(random.nextInt(secondParts.size()));
            names.add(name);
        }
    }
    
    public void createPhones(int phoneNumber) {
        Phone phone = new Phone();
        phone.setNumber(phoneNumber);
        phone.setDescription("Unset description");
        gf.createPhones(phone);
    }

    public void createAddresses() {
        Address address = new Address();
        address.setStreet(streets.get(random.nextInt(streets.size())));
        address.setAddSitionalInfo(additionalInfo.get(random.nextInt(additionalInfo.size())));
        gf.createAddress(address);
    }
    
    public void createHobbies() {
        Hobby hobby = hobbies.get(random.nextInt(hobbies.size()));
        gf.createHobbies(hobby);
    }

    public void createCompanies(int phoneNumber, int cvr) {
        Company company = new Company();
        company.setDescription(descriptions.get(random.nextInt(descriptions.size())));
        company.setMarketValue(random.nextInt((10000000 - 10000) + 1) + 10000);
        company.setName(names.get(random.nextInt(names.size())));
        company.setCvr(String.valueOf(cvr));
        company.setEmail(company.getName() + "@company.com");
        List<Phone> phones = new ArrayList();
        phones.add(gf.getPhone(phoneNumber));
        company.setPhones(phones);
        company.setNumEmployees(random.nextInt((1000 - 5) + 1) + 5);
        gf.createCompany(company);
    }
    
    public void createPersons(int phoneNumber) {
        Person person = new Person();
        person.setFirstName(firstNames.get(random.nextInt(firstNames.size())));
        person.setLastName(lastNames.get(random.nextInt(lastNames.size())));
        person.setEmail(person.getFirstName() + "@person.com");
        List<Phone> phones = new ArrayList();
        phones.add(gf.getPhone(phoneNumber));
        person.setPhones(phones);
        gf.createPerson(person);
    }

    public void createTestData(int count, int startingPhoneNumber, int startingCVR) {
        int cvr = startingCVR;
        createNames(count);
        hobbiesList();
        for (int i = 0; i < count; i++) {     
            createPhones(startingPhoneNumber);
            createHobbies();
            createAddresses();
            createCompanies(startingPhoneNumber, cvr);
            startingPhoneNumber++;
            createPhones(startingPhoneNumber);
            createPersons(startingPhoneNumber);
            startingPhoneNumber++;
            cvr++;
        }
    }

}
