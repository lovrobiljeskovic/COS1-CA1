package Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author mathiasjepsen
 */

@Entity
public class CityInfo implements Serializable {

    @Id
    private int zipCode;
    private String city;

    public CityInfo() {
    }
    
    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    
    
    
    
}
