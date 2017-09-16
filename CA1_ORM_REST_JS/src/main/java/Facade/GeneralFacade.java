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
import Utility.JPAUtil;
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

    Random random = new Random();

    public List<CityInfo> getCityInfoList() {
        EntityManager em = JPAUtil.getEntityManager();
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
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(CityInfo.class, city.getZipCode());
        } finally {
            em.close();
        }
    }

    public Address createAddress(Address address) {
        EntityManager em = JPAUtil.getEntityManager();
        
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
    
    public Company createCompany(Company company, int addressIndex, int phoneIndex) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(company);
            Company c = (Company) em.createQuery("SELECT c FROM Company c where c= :company").setParameter("company", company).getSingleResult();
            c.setAddress(em.find(Address.class, addressIndex));
            c.addPhone(em.find(Phone.class, phoneIndex));
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
        }
    }
    
        public Person createPerson(Person person, int addressIndex, int phoneIndex, int hobbyId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(person);
            Person p = (Person) em.createQuery("SELECT p FROM Person p where p= :person").setParameter("person", person).getSingleResult();
            p.setAddress(em.find(Address.class, addressIndex));
            p.addPhone(em.find(Phone.class, phoneIndex));
            p.addHobby(em.find(Hobby.class, hobbyId));
            Hobby hobby = em.find(Hobby.class, hobbyId);
            hobby.addPerson(person);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    public Phone createPhone(Phone phone) {
        EntityManager em = JPAUtil.getEntityManager();
        
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
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            return em.find(Phone.class, id);
        } finally {
            em.close();
        }
    }

    public Hobby createHobby(Hobby hobby) {
        EntityManager em = JPAUtil.getEntityManager();
        
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
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            return em.find(Hobby.class, id);
        } finally {
            em.close();
        }
    }

    public Person createPerson(Person person) {
        EntityManager em = JPAUtil.getEntityManager();
        
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
