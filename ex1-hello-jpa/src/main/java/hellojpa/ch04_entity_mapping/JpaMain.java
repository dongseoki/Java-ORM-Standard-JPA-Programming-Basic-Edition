package hellojpa.ch04_entity_mapping;

import hellojpa.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try{
//            // 삽입.
            Member member = new Member();
            member.setUsername("hello");
            entityManager.persist(member);

            member = new Member();
            member.setUsername("h2");
            entityManager.persist(member);



            // 조회.
            Member findMember = entityManager.find(Member.class,2L);

            // 수정.
            findMember.setUsername("changed");

            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getUsername());

            // 삭제
//            entityManager.remove(findMember);

            // JPQL
            System.out.println("jpql start");

//            // make dummy.. failed.
//            for (int i = 10; i < 100; i++) {
//                Member dummyMember = new Member();
//                dummyMember.setId(Long.parseLong(""+i+"L"));
//                dummyMember.setName("dummydata");
//                entityManager.persist(dummyMember);
//            }

            List<Member> results = entityManager.createQuery("select m from Member as m", Member.class)
//                            .setFirstResult(0)
                                    .setMaxResults(2)
                                            .getResultList();

            for(Member result:results){
                System.out.println("result.getName() = " + result.getUsername());
            }

            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
        }
        emf.close();
    }
}
