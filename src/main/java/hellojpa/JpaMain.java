package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // META-INF 의 persistence.xml 의 persistence-unit 의 name 값을 준다.

        EntityManager em = emf.createEntityManager();

        em.close();

        emf.close();
    }
}
