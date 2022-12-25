package hellojpa.ch10_jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class S07_jpqlTypeExpression {
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

//            // 1. 타입 표현 테스트.
            String query = "select m.username, 'HELLO', 10L, TRUE from Member m where m.type = hellojpa.ch10_jpql.MemberType.ADMIN and (m.age between  10 and 50)";
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
