package ORM;

import clases.Comentario;

import javax.persistence.*;
import java.util.List;

@PersistenceContext(type = PersistenceContextType.EXTENDED)
public class ComentarioORM {

    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("pUnit");
    EntityManager em = emf.createEntityManager();

    public void guardarComentario(Comentario comentario) {

        em.getTransaction().begin();
        em.persist(comentario);
        em.getTransaction().commit();

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
        
    }

    public void borrarComentario(Long id) {
        em.getTransaction().begin();
        Comentario u = em.find(Comentario.class, id);
        em.remove(u);
        em.getTransaction().commit();
        
    }

    public List<Comentario> getComentario(Long idArticulo){
        Query query = em.createQuery("select c from Comentario c where articulo.id = ?1")
                .setParameter(1,idArticulo);
        return (List<Comentario>)query.getResultList();
    }

}