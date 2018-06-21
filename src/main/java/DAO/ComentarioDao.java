package DAO;

import clases.Comentario;
import clases.Etiqueta;
import org.sql2o.Sql2o;

import java.util.List;

public class ComentarioDao {

    private Sql2o conexion = null;

    public void insertarComentario(Comentario comentario) {
        String sql = "insert into comentario (id, comentario, id_autor, activo) values(:id, :comentario, :id_autor, :activo)";
        Conexion con = new Conexion();
        conexion = con.getConexion();
        conexion.open();
        String lastId = "select top 1 * from comentario order by id desc";
        Long id = lastComentario();

        conexion.createQuery(sql)
                .addParameter("id",id)
                .addParameter("comentario",comentario.getComentario())
                .addParameter("id_autor",comentario.getAutor())
                .addParameter("activo",true)
                .executeUpdate();

    }
    public int countComentario(){
        String sql = "select count(id) from comentario";
        Conexion con = new Conexion();
        conexion = con.getConexion();
        conexion.open();
        int count = conexion.createQuery(sql).executeScalar(Integer.class);
        return count;
    }

    public void borrarComentario(Long id){
        String sql = "update comentario set activo=false where id="+id;
        Conexion con = new Conexion();
        conexion = con.getConexion();
        conexion.open();
        conexion.createQuery(sql).executeUpdate();
    }

    public List<Comentario> getComentario(Long idArticulo){
        Conexion con = new Conexion();
        conexion = con.getConexion();
        conexion.open();

        String sql = "select distinct id_comentario,  comentario from comentario, articulo_comentarios, articulo where id_articulo = '"+idArticulo+"' and id_comentario = comentario.id and comentario.activo = true order by id_comentario";
        return conexion.createQuery(sql).executeAndFetch(Comentario.class);
    }


    public Long lastComentario(){
        Conexion con = new Conexion();
        conexion = con.getConexion();
        conexion.open();

        String lastId = "select top 1 * from comentario order by id desc";
        Long id = new Long(0);
        if(countComentario() != 0){
            id = conexion.createQuery(lastId).executeScalar(Long.class)+1;
        }
        return id;
    }

    public List<Etiqueta> getEtiquetas(Long idArticulo){
        Conexion con = new Conexion();
        conexion = con.getConexion();
        conexion.open();

        String sql = "select distinct id_comentario,  comentario from comentario, articulo_comentarios, articulo where id_articulo = '"+idArticulo+"' and id_comentario = comentario.id and comentario.activo = true order by id_comentario";
        return conexion.createQuery(sql).executeAndFetch(Etiqueta.class);
    }
}
