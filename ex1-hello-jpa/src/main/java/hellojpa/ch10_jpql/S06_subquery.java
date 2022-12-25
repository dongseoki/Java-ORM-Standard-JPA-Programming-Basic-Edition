package hellojpa.ch10_jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class S06_subquery {
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

//            // 1. 나이가 평균보다 많은 회원.
//            String query = "select m from Member m where m.age > (select avg(m2.age) from Member m2)";
//            List<Member> resultList = entityManager.createQuery(query, Member.class)
//                    .getResultList();

//            // 2. 팀A 소속인 회원.
//            String query = "select m from Member m where exists (select t from m.team t where t.name='팀A')";
//            List<Member> resultList = entityManager.createQuery(query, Member.class)
//                    .getResultList();

            // 2. 팀A 소속인 회원. 2 이너 조인으로? 좀 이상하네..
//            String query = "select m from Member m where exists (select t from m inner join m.team t where t.name='팀A')";
//            List<Member> resultList = entityManager.createQuery(query, Member.class)
//                    .getResultList();

//             3. 전체 상품의 각각의 재고보다 주문량이 많은 주문들 ALL
//            String query = "select o from Order o where o.orderAmount > ALL(select p.stockAmount from Product p)";
//            List<Order> resultList = entityManager.createQuery(query, Order.class)
//                    .getResultList();

//            4. 어떤 팀이든 팀에 소속된 회원 ANY
            String query = "select m from Member m where m.team = ANY(select t from Team t)";
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
