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
public class CompanyFacade implements ICompanyFacade{
 private EntityManagerFactory emf;
    
    private EntityManager getEntityManager(){ 
        return emf.createEntityManager();
    }
    
    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }
    @Override
    public Company getCompany(int id) {
          EntityManager em = getEntityManager();
        
        try {
            return em.find(Company.class, 1);
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
    public List<Company> getCompanies(String zipCode) {
  EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Company c JOIN c.address e WHERE e.cityinfo.zipCode = :zipCode").setParameter("zipCode", zipCode).getResultList();
        } finally {
            em.close();
    }
}
    
    public Company getCompanyByCvr(String cvr) {
  EntityManager em = getEntityManager();

        try {            
            Company company = (Company) em.createQuery("SELECT c FROM Company cWHERE c.cvr = :cvr").setParameter("cvr", cvr).getSingleResult();
            return company;
        } finally {
            em.close();
    }
}
}
