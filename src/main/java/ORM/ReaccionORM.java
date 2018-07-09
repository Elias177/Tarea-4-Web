package ORM;

import clases.Articulo;
import clases.Reaccion;
import clases.Usuario;

import javax.persistence.*;

public class ReaccionORM {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pUnit");
    EntityManager em = emf.createEntityManager();

    public void guardarLike(Reaccion r){
        em.getTransaction().begin();
        em.persist(r);
        em.getTransaction().commit();

    }

    public Reaccion checkLike(Usuario Usuario, Articulo Articulo){
        try{
            Query query = em.createQuery("select r from Reaccion r where r.articulo = ?1 AND r.usuario = ?2")
                    .setParameter(1, Articulo)
                    .setParameter(2, Usuario);
            return (Reaccion)query.getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }

    public void deleteLike(Reaccion r){
        em.getTransaction().begin();
        em.remove(r);
        em.getTransaction().commit();
    }

    public void updateLike(Reaccion r, boolean valor){
        em.getTransaction().begin();
        r.setReaccion(valor);
        em.merge(r);
        em.getTransaction().commit();
    }


}
