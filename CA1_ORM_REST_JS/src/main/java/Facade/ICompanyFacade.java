package Facade;

import Entity.Company;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mathiasjepsen
 */
public interface ICompanyFacade {
    
    Company getCompany(int cvr);
    List<Company> getCompanies();
    List<Company> getCompanies(int zipCode);
    void addEntityManagerFactory(EntityManagerFactory emf);

}
