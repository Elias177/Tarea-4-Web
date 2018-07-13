package ORM;

import clases.Etiqueta;

import javax.persistence.*;
import java.util.List;

public class EtiquetaORM {

    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("pUnit");
    EntityManager em = emf.createEntityManager();

    public void guardarEtiqueta(Etiqueta etiqueta) {
        List<Etiqueta> e = checkEtiqueta();
        for(int i = 0; i < e.size(); i++){
            if(!e.get(i).getEtiqueta().equalsIgnoreCase(etiqueta.getEtiqueta())){
                em.getTransaction().begin();
                em.persist(etiqueta);
                em.getTransaction().commit();
            }else{
                editarEtiqueta(etiqueta,etiqueta.getEtiqueta());
            }

        }

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
        List<Etiqueta> etiquetas = em.createQuery(
                "select e.listaEtiqueta from Articulo e where e.id = ?1")
                .setParameter(1,idArticulo)
                .getResultList();

        return etiquetas;
    }

    public Etiqueta getEtiquetaNombre(String etiqueta){
        try{
        Query query = em.createQuery("select e from Etiqueta e where e.etiqueta = ?1")
                .setParameter(1, etiqueta);
        return (Etiqueta) query.getSingleResult();
    } catch (NoResultException e) {
        return null;
    }
    }

    public List<Etiqueta> checkEtiqueta() {
        try {
            List<Etiqueta> query = em.createQuery("select e from Etiqueta e")
                    .getResultList();
            return query;
        } catch (NoResultException e) {
            return null;
        }
    }
}