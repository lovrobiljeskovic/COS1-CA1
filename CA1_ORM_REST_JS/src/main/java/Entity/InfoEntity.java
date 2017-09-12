package Entity;

import java.io.Serializable;
import java.util.List;
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
    @OneToMany
    private List<Phone> phones;
    @ManyToOne
    private Address address;
    

    public InfoEntity() {
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
    
}
