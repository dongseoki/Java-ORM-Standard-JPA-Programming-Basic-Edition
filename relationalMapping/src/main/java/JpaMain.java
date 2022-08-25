import domain.Member;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();
//----------------------------------
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            Team team = new Team();
            team.setName("TeamA");
            entityManager.persist(team);

            Member member = new Member();
            member.setUsername("member1");

            member.setTeam(team);
            entityManager.persist(member);

            //중간 캐시 비우면 join 쿼리 날라가는 것을 볼수 있다.
//            entityManager.flush();
//            entityManager.clear();

            Member findMember = entityManager.find(Member.class, member.getId());

            Team findTeam = member.getTeam();

            // 새로운 팀B 설정.
            Team teamB = new Team();
            teamB.setName("TeamB");
            entityManager.persist(teamB);

            member.setTeam(teamB);


            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
//----------------------------------
        entityManager.close();
        emf.close();
    }
}
