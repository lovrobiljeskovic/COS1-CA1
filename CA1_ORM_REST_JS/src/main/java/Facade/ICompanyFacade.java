package Facade;

import Entity.Company;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mathiasjepsen
 */
public interface ICompanyFacade {
    
    Company getCompany(int id);
    Company getCompanyCvr(String cvr);
    List<Company> getCompanies();
    List<Company> getCompanies(String zipCode);
    void addEntityManagerFactory(EntityManagerFactory emf);

}
