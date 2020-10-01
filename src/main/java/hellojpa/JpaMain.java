package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // META-INF 의 persistence.xml 의 persistence-unit 의 name 값을 준다.
        // EntityManagerFactory 어플리케이션 로딩 시점에 딱 한번만 만들어야 한다.

        EntityManager em = emf.createEntityManager();
        // DB 에 커넥션을 받아서 날리고 하는 일관적인 단위를 할때 마다 EntityManager 를 만들어야 한다.
        // 간단히 이야기 하면 DB Connection 을 하나 받았다고 생각하자
        // 이용할 때에는 Java Collection 이라고 생각하자
        // EntityManager 쓰레드간 절대 공유해서는 안된다. (사용하고 버려야 한다)
        // JPA 의 모든 데이터 변경은 트랜잭션 안에서 실행 (단순 조회는 모르겠지만 그 이외의 변경은 트랜잭션 안에서 실행)

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setName("name");

            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
