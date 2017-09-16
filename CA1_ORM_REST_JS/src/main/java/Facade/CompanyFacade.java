package Facade;

import CustomExceptions.ErrorMessageBuilder;
import CustomExceptions.ExceptionBuilder;
import Entity.Address;
import Entity.CityInfo;
import Entity.Company;
import Entity.Phone;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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
    public Company getCompanyByID(String idString) {
        EntityManager em = getEntityManager();
        try {
            int id = Integer.parseInt(idString);
            Company cp = em.find(Company.class, id);
            if (cp == null) {
                throw new ExceptionBuilder(new ErrorMessageBuilder(404, "Company with id " + id + " not found"));
            }
            return cp;
        } catch (NumberFormatException e) {
            throw new ExceptionBuilder(new ErrorMessageBuilder(400, "Please enter a valid id"));
        } finally {
            em.close();
        }
    }

    @Override
    public Company getCompanyByPhone(String number) {
        EntityManager em = getEntityManager();
        try {
            int intPhone = Integer.parseInt(number);
            Company cp = (Company) em.createQuery("SELECT c FROM Company c JOIN c.phones p WHERE p.number = :number").setParameter("number", intPhone).getSingleResult();
            return cp;
        } catch (NumberFormatException e) {
            throw new ExceptionBuilder(new ErrorMessageBuilder(400, "Please enter a valid phone number"));
        } catch (NoResultException e) {
            throw new ExceptionBuilder(new ErrorMessageBuilder(404, "There is no company with the following phone number " + number));
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
            if (list.isEmpty()) {
                throw new ExceptionBuilder(new ErrorMessageBuilder(204, "There is currently no companies"));
            }
            return list;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Company> getCompaniesByZipCode(String zipCode) {
        EntityManager em = getEntityManager();
        try {
            int testFormat = Integer.parseInt(zipCode);
            List<Company> list = em.createQuery("SELECT c FROM Company c JOIN c.address e WHERE e.cityInfo.zipCode = :zipCode").setParameter("zipCode", zipCode).getResultList();
            if (list.isEmpty()) {
                throw new ExceptionBuilder(new ErrorMessageBuilder(404, "There is no company with the following zipcode " + zipCode));
            }
            System.out.println(list.get(0).getName());
            System.out.println(list.get(0).getCvr());
            return list;
        } catch (NumberFormatException e) {
            throw new ExceptionBuilder(new ErrorMessageBuilder(400, "Please enter a valid zipCode"));
        } finally {
            em.close();
        }
    }

    @Override
    public Company addCompany(Company c) {
        EntityManager em = getEntityManager();

        try {
            CityInfo city = em.find(CityInfo.class, c.getAddress().getCityInfo().getZipCode());
            Phone phone = em.find(Phone.class, c.getPhones().get(0).getNumber());

            if (phone != null) {
                throw new ExceptionBuilder(new ErrorMessageBuilder(400, "Duplicate phone number error"));
            }

            if (city != null) {
                Address a = c.getAddress();
                a.setCityInfo(city);
                c.setAddress(a);
            }

            if (c.getName().trim().isEmpty()
                    || (c.getCvr().trim().isEmpty())
                    || (c.getDescription().trim().isEmpty())
                    || (c.getEmail().trim().isEmpty())
                    || (c.getAddress().getStreet().trim().isEmpty())
                    || (c.getAddress().getAddSitionalInfo().trim().isEmpty())
                    || (c.getAddress().getCityInfo().getCity().trim().isEmpty())
                    || (c.getAddress().getCityInfo().getZipCode().trim().isEmpty())
                    || (c.getPhones().isEmpty())) {
                throw new ExceptionBuilder(new ErrorMessageBuilder(400, "Please fill up the required fields"));
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
            int testFormat = Integer.parseInt(cvr);
            Company company = (Company) em.createQuery("SELECT c FROM Company c WHERE c.cvr = :cvr").setParameter("cvr", cvr).getSingleResult();
            return company;
        } catch (NumberFormatException e) {
            throw new ExceptionBuilder(new ErrorMessageBuilder(400, "Please enter a valid cvr"));
        } catch (NoResultException e) {
            throw new ExceptionBuilder(new ErrorMessageBuilder(404, "There is no company with the following cvr " + cvr));
        } finally {
            em.close();
        }
    }

    @Override
    public void createCompany(Company c) {
        EntityManager em = getEntityManager();

        try {
            if (c == null) {
                throw new ExceptionBuilder(new ErrorMessageBuilder(400, "Please fill up the required fields"));
            }
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Company> getCompaniesWithMoreEmployees(String minimumNum) {
        EntityManager em = getEntityManager();

        try {
            int minInt = Integer.parseInt(minimumNum);
            List<Company> list = em.createQuery("SELECT c FROM Company c WHERE c.numEmployees > :min").setParameter("min", minInt).getResultList();
            System.out.println(list.size());
            if (list.isEmpty()) {
                throw new ExceptionBuilder(new ErrorMessageBuilder(404, "There are no companies with " + minimumNum + " or more employees"));
            }
            return list;
        } catch (NumberFormatException e) {
            throw new ExceptionBuilder(new ErrorMessageBuilder(400, "Please enter a valid number"));
        } finally {
            em.close();
        }
    }

    @Override
    public List<Company> getCompaniesWithLessEmployees(String maximumNum) {
        EntityManager em = getEntityManager();

        try {
            int maxInt = Integer.parseInt(maximumNum);
            List<Company> list = em.createQuery("SELECT c FROM Company c WHERE c.numEmployees < :max").setParameter("max", maxInt).getResultList();
            System.out.println(list.size());

            if (list.isEmpty()) {
                throw new ExceptionBuilder(new ErrorMessageBuilder(404, "There are no companies with " + maximumNum + " or less employees"));
            }
            return list;
        } catch (NumberFormatException e) {
            throw new ExceptionBuilder(new ErrorMessageBuilder(400, "Please enter a valid number"));
        } finally {
            em.close();
        }
    }

    @Override
    public Company editCompany(Company company) {
        EntityManager em = getEntityManager();

        try {
            Company c = em.find(Company.class, company.getId());
            if (c == null) {
                throw new ExceptionBuilder(new ErrorMessageBuilder(404, "there is no company with the following id " + company.getId()));
            }
            CityInfo city = em.find(CityInfo.class, company.getAddress().getCityInfo().getZipCode());

            if (city != null) {
                Address a = c.getAddress();
                a.setCityInfo(city);
                company.setAddress(a);
            }

            em.getTransaction().begin();
            if (!company.getName().isEmpty()) {
                c.setName(company.getName());
            }
            if (!company.getDescription().isEmpty()) {
                c.setDescription(company.getDescription());
            }
            if (!company.getCvr().isEmpty()) {
                c.setCvr(company.getCvr());
            }
            if (company.getNumEmployees() != 0) {
                c.setNumEmployees(company.getNumEmployees());
            }
            if (company.getMarketValue() != 0) {
                c.setMarketValue(company.getMarketValue());
            }
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
            List<Address> list = em.createQuery("SELECT a FROM Address a").getResultList();
            if (list.isEmpty()) {
                throw new ExceptionBuilder(new ErrorMessageBuilder(204, "There is currently no streets"));
            }
            return list;
        } finally {
            em.close();
        }
    }

    @Override
    public List<CityInfo> getAllZipCodes() {
        EntityManager em = getEntityManager();

        try {
            List<CityInfo> list = em.createQuery("SELECT c FROM CityInfo c").getResultList();
            if (list.isEmpty()) {
                throw new ExceptionBuilder(new ErrorMessageBuilder(204, "There is currently no zip codes"));
            }
            return list;
        } finally {
            em.close();
        }
    }

    public Company deleteCompany(String id) {
        EntityManager em = getEntityManager();
        try {
            int idInt = Integer.parseInt(id);
            Company company = em.find(Company.class, idInt);
            if (company == null) {
                throw new ExceptionBuilder(new ErrorMessageBuilder(404, "Company with id " + id + " not found"));
            }
            em.getTransaction().begin();
            em.remove(company);
            em.getTransaction().commit();
            return company;
        } catch (NumberFormatException e) {
            throw new ExceptionBuilder(new ErrorMessageBuilder(400, "Please enter a valid id"));

        } finally {
            em.close();
        }
    }
}
