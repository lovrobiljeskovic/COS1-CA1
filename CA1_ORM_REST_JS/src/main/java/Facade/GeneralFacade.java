/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Address;
import Entity.CityInfo;
import Entity.Company;
import Entity.Hobby;
import Entity.Person;
import Entity.Phone;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author thomasthimothee
 */
public class GeneralFacade {

    private EntityManagerFactory emf;
    Random random = new Random();

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<CityInfo> getCityInfoList() {
        EntityManager em = getEntityManager();
        List<CityInfo> list = new ArrayList();
        try {
            Query q1 = em.createQuery("SELECT ci FROM CityInfo ci");
            list = q1.getResultList();
            return list;
        } finally {
            em.close();
        }
    }

    public CityInfo getCityInfoByZipCode(CityInfo city) {
        EntityManager em = getEntityManager();

        try {
            return em.find(CityInfo.class, city.getZipCode());
        } finally {
            em.close();
        }
    }

    public Address createAddress(Address address) {
        EntityManager em = getEntityManager();
        
        List<CityInfo> cities = em.createQuery("SELECT c FROM CityInfo c").getResultList();

        try {
            address.setCityInfo(cities.get(random.nextInt(cities.size())));
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
            return address;
        } finally {
            em.close();
        }
    }
    
    public Company createCompany(Company company) {
        EntityManager em = getEntityManager();
        
        List<Address> addresses = em.createQuery("SELECT a FROM Address a").getResultList();

        try {
            company.setAddress(addresses.get(random.nextInt(addresses.size())));
            em.getTransaction().begin();
            em.persist(company);
            em.getTransaction().commit();
            return company;
        } finally {
            em.close();
        }
    }

    public Phone createPhones(Phone phone) {
        EntityManager em = getEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(phone);
            em.getTransaction().commit();
            return phone;
        } finally {
            em.close();
        }
    }
    
    public Phone getPhone(int id) {
        EntityManager em = getEntityManager();
        
        try {
            return em.find(Phone.class, id);
        } finally {
            em.close();
        }
    }

    public Hobby createHobbies(Hobby hobby) {
        EntityManager em = getEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
            return hobby;
        } finally {
            em.close();
        }
    }
    
    public Hobby getHobby(int id) {
        EntityManager em = getEntityManager();
        
        try {
            return em.find(Hobby.class, id);
        } finally {
            em.close();
        }
    }

    public Person createPerson(Person person) {
        EntityManager em = getEntityManager();
        
        List<Address> addresses = em.createQuery("SELECT a FROM Address a").getResultList();

        try {
            person.setAddress(addresses.get(random.nextInt(addresses.size())));
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            return person;
        } finally {
            em.close();
        }    
    }

}
