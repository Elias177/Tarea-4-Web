package ORM;

import clases.Articulo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Iterator;

public class ArticuloORM {

    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("pUnit");
    EntityManager em = emf.createEntityManager();

    public void guardarArticulo(Articulo articulo) {
        em.getTransaction().begin();
        em.persist(articulo);
        em.getTransaction().commit();
        em.close();

    }

    public Long countArticulos() {
        Long count = ((Number) em.createNativeQuery("select count(Articulo.id) from USUARIO").getSingleResult()).longValue();
        return count;
    }

    public void editarArticulo(Articulo editar) {
        em.getTransaction().begin();
        Articulo a = em.find(Articulo.class, editar.getId());
        a = editar;
        em.getTransaction().commit();
        em.close();
    }

    public void borrarArticulo(Long id) {
        em.getTransaction().begin();
        Articulo u = em.find(Articulo.class, id);
        em.remove(u);
        em.getTransaction().commit();
        em.close();
    }
}