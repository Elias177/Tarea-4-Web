package ORM;

import clases.Articulo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ArticuloORM {

    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("pUnit");
    EntityManager em = emf.createEntityManager();

    public void guardarArticulo(Articulo articulo) {
        em.getTransaction().begin();
        em.persist(articulo);
        em.getTransaction().commit();

    }

    public float countArticulos() {
        float count = ((Number) em.createNativeQuery("select count(Articulo.id) from ARTICULO").getSingleResult()).floatValue();
        return count;
    }

    public void editarArticulo(Articulo a, String titulo, String cuerpo) {
        em.getTransaction().begin();
        a.setTitulo(titulo);
        a.setCuerpo(cuerpo);
        em.merge(a);
        em.getTransaction().commit();

    }

    public void borrarArticulo(Long id) {
        em.getTransaction().begin();
        Articulo u = em.find(Articulo.class, id);
        em.remove(u);
        em.getTransaction().commit();
        
    }

    public List<Articulo> listarArticulos(int pagina){

        Query query = em.createQuery("select a from Articulo a order by fecha desc")
                .setFirstResult(5*(pagina-1))
                .setMaxResults(5);

        return (List<Articulo>)query.getResultList();
    }

    public Articulo getArticulo(Long id){
        Query query = em.createQuery("select a from Articulo a where a.id = :id")
                .setParameter("id", id);
        return (Articulo) query.getSingleResult();
    }



    public Long countLikes(Long idArticulo){
        Long count = ((Number) em.createNativeQuery("select count(a.id) from Reaccion a WHERE a.ARTICULO_ID = ?1 AND a.reaccion = true")
                .setParameter(1, idArticulo)
                .getSingleResult()).longValue();

        return count;
    }

    public Long countDislikes(Long idArticulo){
        Long count = ((Number) em.createNativeQuery("select count(a.id) from Reaccion a WHERE a.ARTICULO_ID = ?1 AND a.reaccion = false")
                .setParameter(1, idArticulo)
                .getSingleResult()).longValue();

        return count;
    }


}