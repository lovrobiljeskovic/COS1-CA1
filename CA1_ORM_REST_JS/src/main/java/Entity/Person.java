package Entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author Lovro
 */
@Entity
@DiscriminatorValue("Person")
public class Person extends InfoEntity  {

    private String firstName;
    private String lastName;
    @ManyToMany(mappedBy = "persons", cascade = {CascadeType.ALL})
    private List<Hobby> hobbies = new ArrayList();

    
    public Person() {
    }

    public void addHobby(Hobby hobby) {
        hobbies.add(hobby);
    }
    
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }
    
    

    
}
