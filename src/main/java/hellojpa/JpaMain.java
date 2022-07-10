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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam);


            Team newTeam = em.find(Team.class, 100L);
            findMember.setTeam(newTeam);


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
