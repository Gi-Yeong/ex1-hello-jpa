package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
            // 저장 persist
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);

            // 조회 find
            Member findMember = em.find(Member.class, 1L);
            System.out.println(findMember.getId());
            System.out.println(findMember.getName());

            // 직접 쿼리를 날려야 할때 (ex: JPQL)
            // 대상이 테이블이 아니고 객체 여야 한다.
            // JPA 는 SQL 을 추상화한 JPQL 이라는 객체 지향 쿼리 언어 제공
            // JPQL 은 엔티티 객체를 대상으로 쿼리
            // SQL 은 데이터베이스 테이블을 대상으로 쿼리
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            // 삭제
//            em.remove(findMember);
            
            // 업데이트
            findMember.setName("HelloJPA");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
