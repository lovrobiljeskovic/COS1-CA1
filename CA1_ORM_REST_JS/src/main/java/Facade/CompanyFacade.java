package Facade;

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
    public List<Company> getCompaniesByPhone(int number) {
        EntityManager em = getEntityManager();

        try {
            return em.createQuery("SELECT c FROM Company c JOIN c.phones p WHERE p.number = :phone").setParameter("number", number).getResultList();
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

    public Company addCompany(Company c) {
        EntityManager em = getEntityManager();
        try {
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
    public List<Company> getCompaniesWithEmployees(int minimumNum) {
        EntityManager em = getEntityManager();

        try {
            return em.createQuery("SELECT c FROM Company c WHERE c.numEmployees > :min").setParameter("min", minimumNum).getResultList();
        } finally {
            em.close();
        }
    }

    public Company editCompany(Company company) {

        EntityManager em = getEntityManager();
        try {
            Company c = em.find(Company.class, company.getId());
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
}
