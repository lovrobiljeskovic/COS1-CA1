/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Dell
 */
@Entity
public class Address implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String street;
    private String addSitionalInfo;

    public Address()
    {}
    
    public Address(int id, String street, String addSitionalInfo)
    {
        this.id = id;
        this.street = street;
        this.addSitionalInfo = addSitionalInfo;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getAddSitionalInfo()
    {
        return addSitionalInfo;
    }

    public void setAddSitionalInfo(String addSitionalInfo)
    {
        this.addSitionalInfo = addSitionalInfo;
    }
    
    
    
    
    
    
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address))
          {
            return false;
          }
        Address other = (Address) object;
        if (this.id != other.id)
          {
            return false;
          }
        return true;
    }

    @Override
    public String toString()
    {
        return "Entity.Address[ id=" + id + " ]";
    }
    
}
