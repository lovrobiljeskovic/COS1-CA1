package Entity;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Dell
 */
@Entity
public class Company extends InfoEntity implements Serializable {

    private String name;
    private String description;
    private String cvr;
    private int numEmployees;
    private double marketValue;

    public Company() {
    }

    public Company(String name, String description, String cvr, int numEmployees, double marketValue) {
        this.name = name;
        this.description = description;
        this.cvr = cvr;
        this.numEmployees = numEmployees;
        this.marketValue = marketValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCvr() {
        return cvr;
    }

    public void setCvr(String cvr) {
        this.cvr = cvr;
    }

    public int getNumEmployees() {
        return numEmployees;
    }

    public void setNumEmployees(int numEmployees) {
        this.numEmployees = numEmployees;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

}
