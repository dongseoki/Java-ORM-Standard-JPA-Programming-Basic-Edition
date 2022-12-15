package hellojpa.ch03_ch04;

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
            OldMember oldMember = new OldMember();
            oldMember.setUsername("hello");
            entityManager.persist(oldMember);

            oldMember = new OldMember();
            oldMember.setUsername("h2");
            entityManager.persist(oldMember);



            // 조회.
            OldMember findOldMember = entityManager.find(OldMember.class,2L);

            // 수정.
            findOldMember.setUsername("changed");

            System.out.println("findMember.getId() = " + findOldMember.getId());
            System.out.println("findMember.getName() = " + findOldMember.getUsername());

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

            List<OldMember> results = entityManager.createQuery("select m from OldMember as m", OldMember.class)
//                            .setFirstResult(0)
                                    .setMaxResults(2)
                                            .getResultList();

            for(OldMember result:results){
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
