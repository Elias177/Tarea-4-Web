package ORM;

import clases.Reaccion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ReaccionORM {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pUnit");
    EntityManager em = emf.createEntityManager();

    public void guardarLike(Reaccion r){
        em.getTransaction().begin();
        em.persist(r);
        em.getTransaction().commit();

    }
}
