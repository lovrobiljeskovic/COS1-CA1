package Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mathiasjepsen
 */

@Entity
@Table(name = "CITYINFO")
@XmlRootElement
public class CityInfo implements Serializable {

    @Id
    private String zipCode;
    private String city;

    public CityInfo() {
    }
    
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    
    
    
    
}
