package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        // EntityManagerFactory : 애플리케이션 로딩시점에 최초에만 생성하여 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // EntityManager : 일괄적인 트랜잭션 단위의 행위 별로 EntityManager 생성
        // DB Connection 하나 받았다고 생각하면 됨
        // 쓰레드간 절대 공유하면 안되고 사용하고 반드시 버려야한다.
        EntityManager em = emf.createEntityManager();

        // 트랜잭션 얻기
        // ** JPA의 모든 데이터 변경은 트랜잭션 안에서 실행되어야 한다.
        EntityTransaction tx = em.getTransaction();
        // 트랜잭션 시작
        tx.begin();

        // JPA는 트랜잭션 안에서 항상 이루어져야함
        try {
            // JPA와는 상관없이 객체를 생성한 상태로 비영속 상태
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");

            // 영속상태 => em을 통해 관리가 된다. 객체가 저장 이때 DB에 저장되는 것 같지만 DB에 저장되지 않는다.
            // 1차 캐시 저장
//            em.persist(member);

            // 1차 캐시에 저장된 값을 가져온다.
//            Member findMember = em.find(Member.class, 101L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());

            // 회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
//            em.detach(member);
            // 객체를 삭제한 상태
//            em.remove(member);


//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//            em.persist(member1);
//            em.persist(member2);
//            System.out.println("=================");

            Member member = em.find(Member.class, 150L);
            member.setName("hi_JPA");

            // 커밋되는 시점에 영속성컨텍스트에 있는 것들이 쿼리로 날아감
            tx.commit();
        } catch (Exception e) {
            // 오류 났을 경우 rollback
            tx.rollback();
        } finally {
            // EntityManager 종료
            // DB 커넥션을 물고있어서 꼭 닫아주어야함
            em.close();
        }
        // EntityManagerFactory 종료
        emf.close();
    }
}
