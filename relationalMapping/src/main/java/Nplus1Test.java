import domain.Member;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Nplus1Test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();
//----------------------------------
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            for (int i = 0; i < 10; i++) {
                Team team = new Team();
                team.setName("강남"+i);
                entityManager.persist(team);

                Member member = new Member();
                member.setUsername("유저"+i);
                entityManager.persist(member);
                member.changeTeam(team);
            }

            //
            entityManager.createQuery("select t from Team t", Team.class)
                    .getResultList();

            System.out.println("------ N+1 시점 확인용.-----");

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
