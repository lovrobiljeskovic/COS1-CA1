package Facade;

import Entity.Address;
import Entity.CityInfo;
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
    Company getCompanyByID(String id);
    Company getCompanyByCVR(String cvr);
    Company editCompany(Company c);
    Company addCompany(Company c);
    List<CityInfo> getAllZipCodes();
    List<Address> getAllStreets();
    List<Company> getCompanies();
    List<Company> getCompaniesByZipCode(String zipCode);
    Company getCompanyByPhone(String number);
    List<Company> getCompaniesWithMoreEmployees(String minimumNum);
    List<Company> getCompaniesWithLessEmployees(String maximumNum);
    void addEntityManagerFactory(EntityManagerFactory emf);

}
