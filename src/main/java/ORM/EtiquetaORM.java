package ORM;

import clases.Etiqueta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class EtiquetaORM {

    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("pUnit");
    EntityManager em = emf.createEntityManager();

    public void guardarEtiqueta(Etiqueta etiqueta) {
        em.getTransaction().begin();
        em.persist(etiqueta);
        em.getTransaction().commit();

    }

    public Long countEtiquetas() {
        Long count = ((Number) em.createNativeQuery("select count(Etiqueta.id) from USUARIO").getSingleResult()).longValue();
        return count;
    }

    public void editarEtiqueta(Etiqueta editar, String etiqueta) {
        em.getTransaction().begin();
        editar.setEtiqueta(etiqueta);
        em.merge(editar);
        em.getTransaction().commit();
        
    }

    public void borrarEtiqueta(Long id) {
        em.getTransaction().begin();
        Etiqueta u = em.find(Etiqueta.class, id);
        em.remove(u);
        em.getTransaction().commit();
        
    }

    public List<Etiqueta> getEtiquetas(Long idArticulo){
        String sql = "";
        List<Etiqueta> etiquetas = em.createQuery(
                "select e.listaEtiqueta from Articulo e where e.id = ?1")
                .setParameter(1,idArticulo)
                .getResultList();

        return etiquetas;
    }
    public Etiqueta getEtiquetaNombre(String etiqueta){
        Query query = em.createQuery("select e from Etiqueta e where e.etiqueta = ?1")
                .setParameter(1, etiqueta);
        return (Etiqueta) query.getSingleResult();
    }

}