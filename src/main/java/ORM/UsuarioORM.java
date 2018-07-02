package ORM;

import clases.Usuario;

import javax.persistence.*;

public class UsuarioORM {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pUnit");
    EntityManager em = emf.createEntityManager();

    public void guardarUsuario(Usuario usuario){
        em.getTransaction().begin();
        usuario.setActivo(true);
        em.persist(usuario);
        em.getTransaction().commit();

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
        
    }

    public void dropUsuario(Long id){
        em.getTransaction().begin();
        Usuario u = em.find(Usuario.class,id);
        em.remove(u);
        em.getTransaction().commit();
        
    }

    public void borrarUsuario(Long id){
        em.getTransaction().begin();
        Usuario u = em.find(Usuario.class,id);
        u.setActivo(false);
        em.getTransaction().commit();
        
    }

    public Usuario getSesion(String session){
        Usuario u = (Usuario) em.createNativeQuery(
                "select * from Usuario  where cookies = ?1")
                .setParameter(1, session)
                .getSingleResult();
        return u;
    }

    public Usuario getUsuario(String nombre,String pass){

        try{
            Query query = em.createQuery("select u from Usuario u where u.username = :user AND u.password = :pass")
                    .setParameter("user", nombre)
                    .setParameter("pass", pass);
            return (Usuario)query.getSingleResult();
        } catch(NoResultException e){
            return null;
        }

    }

    public void saveCookies(Long id,String sesion){
        em.getTransaction().begin();
        Usuario u = em.find(Usuario.class,id);
        u.setCookies(sesion);
        em.getTransaction().commit();
        
    }
}
