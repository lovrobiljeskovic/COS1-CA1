import Facade.GeneralFacade;
import Facade.PersonFacade;
import Utility.TestGenerator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author mathiasjepsen
 */
public class Tester {
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cos1_CA1_ORM_REST_JS_war_1.0-SNAPSHOTPU");
//        EntityManager em = emf.createEntityManager();
//        PersonFacade pf = new PersonFacade();
//        pf.addEntityManagerFactory(emf);
//        GeneralFacade gf = new GeneralFacade();
//        gf.addEntityManagerFactory(emf);  
////        try {
////            InfoEntity p = new Person();
////            Address a = new Address();
////            a.setStreet("Kongevejen");
////            a.setAddSitionalInfo("iufdngidfng");
////            CityInfo c = em.find(CityInfo.class, "2840");
////            a.setCityInfo(c);
////            p.setAddress(a);
////            em.getTransaction().begin();
////            em.persist(p);
////            em.getTransaction().commit();
////        } finally {
////            em.close();
////        
////        List<Person> list = pg.generateJSON(10, 22311255);
////        CompanyGenerator cg = new CompanyGenerator(emf);
////        cg.createTestData(1, 10000, 2490);
//        
        TestGenerator tg = new TestGenerator(emf);
        tg.createTestData(100, 264923);
    }
    
}
