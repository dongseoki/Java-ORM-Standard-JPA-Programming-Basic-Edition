package hellojpa.ch03_persistence_context_management;

import hellojpa.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistenceContext {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try{
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("hello");
//            entityManager.persist(member);
            // 조회.
            Member findMember = entityManager.find(Member.class,2L);
            // 조회.
            Member findMember2 = entityManager.find(Member.class,2L);

            // 영속 엔티티 동일성
            System.out.println("String.valueOf(findMember2==findMember) = " + String.valueOf(findMember2==findMember));


            System.out.println("엔티티 수정 변경 감지.");
//            member.setId(3L); //
//            member.setName("testData");
            System.out.println("커밋 직전.");
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
        }
        emf.close();
    }
}
