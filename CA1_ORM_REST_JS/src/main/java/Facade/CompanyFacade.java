package Facade;

import Entity.Address;
import Entity.CityInfo;
import Entity.Company;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Lovro
 */
public class CompanyFacade implements ICompanyFacade {

    private EntityManagerFactory emf;

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Company getCompanyByID(int id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Company.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public Company getCompanyByPhone(int number) {
        EntityManager em = getEntityManager();

        try {
            return (Company) em.createQuery("SELECT c FROM Company c JOIN c.phones p WHERE p.number = :number").setParameter("number", number).getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Company> getCompanies() {
        EntityManager em = getEntityManager();
        List<Company> list = new ArrayList();
        try {
            Query q1 = em.createQuery("SELECT c FROM Company c");
            list = q1.getResultList();
            return list;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Company> getCompaniesByZipCode(String zipCode) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Company c JOIN c.address e WHERE e.cityInfo.zipCode = :zipCode").setParameter("zipCode", zipCode).getResultList();
        } finally {
            em.close();
        }
    }
    
    @Override
    public Company addCompany(Company c) {
        EntityManager em = getEntityManager();
        
        try {
            CityInfo city = em.find(CityInfo.class, c.getAddress().getCityInfo().getZipCode());
            
            if (city != null) {
                Address a = c.getAddress();
                a.setCityInfo(city);
                c.setAddress(a);
            }
            
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
        }
    }

    @Override
    public Company getCompanyByCVR(String cvr) {
        EntityManager em = getEntityManager();

        try {
            Company company = (Company) em.createQuery("SELECT c FROM Company c WHERE c.cvr = :cvr").setParameter("cvr", cvr).getSingleResult();
            return company;
        } finally {
            em.close();
        }
    }

    @Override
    public void createCompany(Company c) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Company> getCompaniesWithMoreEmployees(int minimumNum) {
        EntityManager em = getEntityManager();

        try {
            return em.createQuery("SELECT c FROM Company c WHERE c.numEmployees > :min").setParameter("min", minimumNum).getResultList();
        } finally {
            em.close();
        }
    }
    
    @Override
    public List<Company> getCompaniesWithLessEmployees(int maximumNum) {
        EntityManager em = getEntityManager();

        try {
            return em.createQuery("SELECT c FROM Company c WHERE c.numEmployees < :max").setParameter("max", maximumNum).getResultList();
        } finally {
            em.close();
        }
    }
    
    @Override
    public Company editCompany(Company company) {
        EntityManager em = getEntityManager();
        
        try {
            Company c = em.find(Company.class, company.getId());
            CityInfo city = em.find(CityInfo.class, company.getAddress().getCityInfo().getZipCode());
            
            if (city != null) {
                Address a = c.getAddress();
                a.setCityInfo(city);
                company.setAddress(a);
            }
//            if (c == null) {
//                throw new CompanyNotFoundException("Cannot edit. Company with provided id does not exist");
//            }
            em.getTransaction().begin();
            if (!company.getName().isEmpty()) c.setName(company.getName()); 
            if (!company.getDescription().isEmpty()) c.setDescription(company.getDescription()); 
            if (!company.getCvr().isEmpty()) c.setCvr(company.getCvr()); 
            if (company.getNumEmployees() != 0) c.setNumEmployees(company.getNumEmployees()); 
            if (company.getMarketValue()!= 0) c.setMarketValue(company.getMarketValue()); 
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
        }
    }
    
    @Override
    public List<Address> getAllStreets() {
        EntityManager em = getEntityManager();

        try {
            return em.createQuery("SELECT a FROM Address a").getResultList();
        } finally {
            em.close();
        } 
    }
    
    @Override
    public List<CityInfo> getAllZipCodes() {
        EntityManager em = getEntityManager();

        try {
            return em.createQuery("SELECT c FROM CityInfo c").getResultList();
        } finally {
            em.close();
        } 
    }
}
