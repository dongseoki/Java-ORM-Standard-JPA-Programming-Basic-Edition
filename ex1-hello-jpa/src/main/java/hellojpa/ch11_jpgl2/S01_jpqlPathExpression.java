package hellojpa.ch11_jpgl2;

import hellojpa.ch10_jpql.Member;
import hellojpa.ch10_jpql.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class S01_jpqlPathExpression {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();
//----------------------------------
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {

            // 0. 준비.

            // teamA. dongseok, test
            Team team = new Team();
            team.setName("A");
            entityManager.persist(team);

            Member member = new Member();
            member.setUsername("dongseok");
            member.setAge(18);
            member.setTeam(team);

            Member member2 = new Member();
            member2.setUsername("test");
            member2.setAge(23);
            member2.setTeam(team);

            // Teamb. samsung, LG
            Team team2 = new Team();
            team2.setName("B");
            entityManager.persist(team2);

            Member member3 = new Member();
            member3.setUsername("samsung");
            member3.setAge(12);
            member3.setTeam(team2);

            Member member4 = new Member();
            member4.setUsername("LG");
            member4.setAge(11);
            member4.setTeam(team2);


            entityManager.persist(team);
            entityManager.persist(team2);
            entityManager.persist(member);
            entityManager.persist(member2);
            entityManager.persist(member3);
            entityManager.persist(member4);

            entityManager.flush();
            entityManager.clear();

////            // 1. 단일 값 연관 경로 탐색.
////            String query = "select m.team from Member m";
//            String query = "select m.team from Member m join m.team t";
//            List resultList = entityManager.createQuery(query)
//                    .getResultList();

            // 2. 컬렉션 값 연관 경로 탐색.
//            String query = "select t.members from Team t";
//            String query = "select t.members.username from Team t";
            String query = "select m.username from Team t join t.members m";
            List resultList = entityManager.createQuery(query)
                                           .getResultList();



            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
//----------------------------------
        emf.close();
    }
}
