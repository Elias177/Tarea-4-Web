package ORM;

import clases.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Iterator;

public class UsuarioORM {

    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("pUnit");
    EntityManager em = emf.createEntityManager();

    public void guardarUsuario(Usuario usuario){
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();

    }

    public Long countUsuarios(){
        Long count = ((Number)em.createNativeQuery("select count(Usuario.id) from USUARIO").getSingleResult()).longValue();
        return count;
    }

    public void editarUsuario(Usuario editar){
        em.getTransaction().begin();
        Usuario u = em.find(Usuario.class,editar.getId());
        u = editar;
        em.getTransaction().commit();
        em.close();
    }

    public void borrarUsuario(Long id){
        em.getTransaction().begin();
        Usuario u = em.find(Usuario.class,id);
        em.remove(u);
        em.getTransaction().commit();
        em.close();
    }

    public Usuario getSesion(String session){
        Usuario u = (Usuario) em.createNativeQuery(
                "select * from Usuario  where cookies = ?1")
                .setParameter(1, session)
                .getSingleResult();
        return u;
    }

    public Usuario getUsuario(String nombre,String pass){
        Usuario u = (Usuario) em.createNativeQuery(
                "select * from Usuario  where USERNAME = ?1 and PASSWORD = ?2")
                .setParameter(1, nombre)
                .setParameter(2,pass)
                .getSingleResult();
        return u;
    }
}
