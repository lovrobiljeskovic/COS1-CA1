package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author mathiasjepsen
 */

@Entity
@Inheritance
@DiscriminatorColumn(name="INFOENTITY_TYPE")
public abstract class InfoEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Phone> phones;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Address address;
    

    public InfoEntity() {
        this.phones = new ArrayList();
    }
    
    public void addPhone(Phone phone) {
        phones.add(phone);
    }
    
    public int getId() {
        return id;  
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    
    
}
