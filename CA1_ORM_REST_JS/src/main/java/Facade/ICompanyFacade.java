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
    Company getCompanyByID(int id);
    Company getCompanyByCVR(String cvr);
    Company editCompany(Company c);
    Company addCompany(Company c);
    List<CityInfo> getAllZipCodes();
    List<Address> getAllStreets();
    List<Company> getCompanies();
    List<Company> getCompaniesByZipCode(String zipCode);
    List<Company> getCompaniesByPhone(int number);
    List<Company> getCompaniesWithMoreEmployees(int minimumNum);
    List<Company> getCompaniesWithLessEmployees(int maximumNum);
    void addEntityManagerFactory(EntityManagerFactory emf);

}
