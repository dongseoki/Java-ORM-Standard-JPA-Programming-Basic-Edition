package hellojpa.ch10_jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class S05_Join {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();
//----------------------------------
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {

            Team team = new Team();
            team.setName("A");
            entityManager.persist(team);

            // 0. 준비.
            Member member = new Member();
            member.setUsername("dongseok");
            member.setAge(18);
            member.setTeam(team);

            entityManager.persist(member);

            entityManager.flush();
            entityManager.clear();


//            // join test
//            List<Member> resultList = entityManager.createQuery("select m from Member m left join m.team t", Member.class)
//                    .getResultList();
////            MemberDTO memberDTO = resultList.get(0);
////            System.out.println("memberDTO = " + memberDTO);

            // 2. seta join test
//            List<Member> resultList = entityManager.createQuery("select m from Member m, Team t where m.username = t.name", Member.class)
//                    .getResultList();
//            MemberDTO memberDTO = resultList.get(0);
//            System.out.println("memberDTO = " + memberDTO);

            // 3. 기본 join 조건에 추가 on 조건 추가.
//            String query = "select m from Member m left join m.team t on t.name = 'teamA'";
//            List<Member> resultList = entityManager.createQuery(query, Member.class)
//                    .getResultList();

            // 4. 연관관계 없는 엔티티 외부 조인.
            String query = "select m from Member m left join Team t on m.username = t.name";
            List<Member> resultList = entityManager.createQuery(query, Member.class)
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
