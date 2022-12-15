package hellojpa.ch10_jpql;

import javax.persistence.*;
import java.util.List;

public class S02_basicGrammer {
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

            // 1. raw jpql
//            String jpql = "select m from Member m where m.age >= 18";
//            List<Member> resultList = entityManager.createQuery(jpql, Member.class) .getResultList();

            // 2. Criteria
//            //Criteria 사용 준비
//            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//            CriteriaQuery<Member> query = cb.createQuery(Member.class);
//
//            //루트 클래스 (조회를 시작할 클래스)
//            Root<Member> m = query.from(Member.class);
//
//            //쿼리 생성
//            CriteriaQuery<Member> cq =   query.select(m).where(cb.equal(m.get("username"), "kim"));
//            List<Member> resultList = entityManager.createQuery(cq).getResultList();


            // 3 native query
//            String sql = "SELECT ID, AGE, TEAM_ID, NAME FROM MEMBER WHERE NAME = 'kim'";
//            List<Member> resultList = entityManager.createNativeQuery(sql, Member.class).getResultList();


            // 4. TypedQuery
            TypedQuery<Member> query =
                    entityManager.createQuery("SELECT m FROM Member m", Member.class);

            List<Member> resultList = query.getResultList();

            // 999. 결과 확인
            resultList.forEach(member1-> System.out.println("data = " + member1));


            

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
