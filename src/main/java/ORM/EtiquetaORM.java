package ORM;

import clases.Etiqueta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Iterator;

public class EtiquetaORM {

    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("pUnit");
    EntityManager em = emf.createEntityManager();

    public void guardarEtiqueta(Etiqueta etiqueta) {
        em.getTransaction().begin();
        em.persist(etiqueta);
        em.getTransaction().commit();
        em.close();

    }

    public Long countEtiquetas() {
        Long count = ((Number) em.createNativeQuery("select count(Etiqueta.id) from USUARIO").getSingleResult()).longValue();
        return count;
    }

    public void editarEtiqueta(Etiqueta editar) {
        em.getTransaction().begin();
        Etiqueta u = em.find(Etiqueta.class, editar.getId_etiqueta());
        u = editar;
        em.getTransaction().commit();
        em.close();
    }

    public void borrarEtiqueta(Long id) {
        em.getTransaction().begin();
        Etiqueta u = em.find(Etiqueta.class, id);
        em.remove(u);
        em.getTransaction().commit();
        em.close();
    }
}