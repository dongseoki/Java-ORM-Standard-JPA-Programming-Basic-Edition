package hellojpa.ch11_jpgl2;

import hellojpa.ch10_jpql.Member;
import hellojpa.ch10_jpql.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class S03_jpqlfetchjoin2 {
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

            // 1. fetch join 과 paging  -> 너무 잘됨.
//            String query = "SELECT m FROM Member m JOIN FETCH m.team";
//            List<Member> result = entityManager.createQuery(query, Member.class)
//                .setFirstResult(0)
//                .setMaxResults(2).getResultList();
//            for (Member memberItem : result) {
//                System.out.println(memberItem.getUsername() + memberItem.getTeam().getName());
//            }

            // 2. fetch join -> 일대 다에서 사용하는 경우. 페이징? limit 안나감.
//            String query = "  SELECT t \n" +
//                "  FROM Team t JOIN FETCH t.members\n";
//            List<Team> result = entityManager.createQuery(query, Team.class)
//                .setFirstResult(0)
//                .setMaxResults(2)
//                 .getResultList();
//            for (Team teamItem : result) {
//                System.out.println(teamItem.getName() + teamItem.getMembers().size());
//            }

            // 3 fetch join -> 일대 다에서 사용하는 경우. distinct 사용.
            String query = "select t from Team t";
            List<Team> result = entityManager.createQuery(query, Team.class)
                .setFirstResult(0)
                .setMaxResults(5)
                .getResultList();
            for (Team teamItem : result) {
                System.out.println(teamItem.getName() + teamItem.getMembers().size());
                for (Member memberItem : teamItem.getMembers()
                     ) {
                    System.out.println("memberItem = " + memberItem.toString());
                }
            }

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
