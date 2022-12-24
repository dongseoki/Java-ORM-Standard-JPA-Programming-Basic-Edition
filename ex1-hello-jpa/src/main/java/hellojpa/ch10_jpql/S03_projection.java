package hellojpa.ch10_jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class S03_projection {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();
//----------------------------------
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            // 0. 준비.
            Member member = new Member();
            member.setUsername("dongseok");
            member.setAge(18);
            entityManager.persist(member);

            entityManager.flush();
            entityManager.clear();

            // 1. query 타입으로 조회
//            List resultList = entityManager.createQuery("select m.username, m.age from Member m").getResultList();
//            Object o = resultList.get(0);
//            Object[] result = (Object[])o;
//            System.out.println("username = " + result[0]);
//            System.out.println("age = " + result[1]);

            // 2. Object 배열 타입으로 조회
//            List<Object[]> resultList = entityManager.createQuery("select m.username, m.age from Member m").getResultList();
//            Object[] result = resultList.get(0);
//            System.out.println("username = " + result[0]);
//            System.out.println("age = " + result[1]);

            // 3 new 명령어로 조회
            List<MemberDTO> resultList = entityManager.createQuery("select new hellojpa.ch10_jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
                    .getResultList();
            MemberDTO memberDTO = resultList.get(0);
            System.out.println("memberDTO = " + memberDTO);


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
