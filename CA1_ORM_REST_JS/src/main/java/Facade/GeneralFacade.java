/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.CityInfo;
import Entity.Company;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author thomasthimothee
 */
public class GeneralFacade {
     private EntityManagerFactory emf;

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
}
