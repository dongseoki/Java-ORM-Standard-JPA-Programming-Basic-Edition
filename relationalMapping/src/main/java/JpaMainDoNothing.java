import domain.Album;
import domain.Item;
import domain.Member;
import domain.Team;
import persistAndOrphan.Child;
import persistAndOrphan.Parent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMainDoNothing {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();
//----------------------------------
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            Album album = new Album("artist", "name", 1000);
            entityManager.persist(album);

//            Movie movie = new Movie();
            Parent parent = new Parent();
            Child ch1 = new Child();
            Child ch2 = new Child();
            parent.addChild(ch1);
            parent.addChild(ch2);
            entityManager.persist(parent);
            entityManager.flush();
            entityManager.clear();
            Parent findParent = entityManager.find(Parent.class, parent.getId());
            findParent.getChildList().remove(0);


            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
//----------------------------------
        entityManager.close();
        emf.close();
    }
}
