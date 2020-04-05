import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("testPersistenceUnit");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        for (int i = 0; i < 100; i++) {
            Point point = new Point();
            point.setX(i);
            point.setY(i);
            em.persist(point);
        }
        em.getTransaction().commit();
        m.getPoints(em);
        em.close();
        entityManagerFactory.close();

    }

    private void getPoints(EntityManager entityManager) {
        TypedQuery<Point> typedQuery = entityManager.createQuery("select o from Point o", Point.class);
        List<Point> points = typedQuery.getResultList();
        Logger logger
                = Logger.getLogger(
                Main.class.getName());
        for (Point p : points
        ) {
            logger.log(Level.INFO, p.toString());

        }
    }
}
