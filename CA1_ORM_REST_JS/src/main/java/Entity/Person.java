package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author Lovro
 */
@Entity
@DiscriminatorValue("Person")
public class Person extends InfoEntity implements Serializable  {

    private String firstName;
    private String lastName;
    @ManyToMany(mappedBy = "persons")
    private List<Hobby> hobbies;
    
    public Person() {
        
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
    
    

    
}
