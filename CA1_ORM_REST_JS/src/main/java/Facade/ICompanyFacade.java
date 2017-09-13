package Facade;

import Entity.Company;
import Entity.Hobby;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mathiasjepsen
 */
public interface ICompanyFacade {
    
    void createCompany(Company c);
    Company getCompanyByID(int id);
    Company getCompanyByCVR(String cvr);
    List<Company> getCompanies();
    List<Company> getCompaniesByZipCode(String zipCode);
    List<Company> getCompaniesByPhone(int number);
    List<Company> getCompaniesWithEmployees(int minimumNum);
    void addEntityManagerFactory(EntityManagerFactory emf);

}
