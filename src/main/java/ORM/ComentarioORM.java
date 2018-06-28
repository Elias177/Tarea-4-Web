package ORM;

import clases.Comentario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Iterator;

public class ComentarioORM {

    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("pUnit");
    EntityManager em = emf.createEntityManager();

    public void guardarComentario(Comentario comentario) {
        em.getTransaction().begin();
        em.persist(comentario);
        em.getTransaction().commit();
        em.close();

    }

    public Long countComentarios() {
        Long count = ((Number) em.createNativeQuery("select count(Comentario.id) from USUARIO").getSingleResult()).longValue();
        return count;
    }

    public void editarComentario(Comentario editar) {
        em.getTransaction().begin();
        Comentario u = em.find(Comentario.class, editar.getId());
        u = editar;
        em.getTransaction().commit();
        em.close();
    }

    public void borrarComentario(Long id) {
        em.getTransaction().begin();
        Comentario u = em.find(Comentario.class, id);
        em.remove(u);
        em.getTransaction().commit();
        em.close();
    }

}