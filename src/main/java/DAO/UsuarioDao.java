package DAO;

import clases.Usuario;
import org.sql2o.Sql2o;

public class UsuarioDao {

    private Sql2o conexion = null;

    public void insertarUsuario(Usuario usuario) {
        String sql = "insert into usuario (id, username, nombre, password,administrator,autor,activo) values(:id,:username,:nombre,:password,:administrator,:autor,:activo)";
        Conexion con = new Conexion();
        conexion = con.getConexion();
        conexion.open();
        String lastId = "select top 1 * from usuario order by id desc";
        Long id = new Long(0);
        if(countUsuarios() != 0){
            id = conexion.createQuery(lastId).executeScalar(Long.class)+1;
        }



        conexion.createQuery(sql)
                .addParameter("id",id)
                .addParameter("username",usuario.getUsername())
                .addParameter("nombre", usuario.getNombre())
                .addParameter("password",usuario.getPassword())
                .addParameter("administrator",usuario.isAdministrator())
                .addParameter("autor",usuario.isAutor())
                .addParameter("activo",true)
                .executeUpdate();



    }
    public int countUsuarios(){
        String sql = "select count(id) from usuario";
        Conexion con = new Conexion();
        conexion = con.getConexion();
        conexion.open();
        int count = conexion.createQuery(sql).executeScalar(Integer.class);
        return count;
    }

    public void crearDB(){
       // String sql = "runscript from '~/IdeaProjects/Tarea3 Web/src/main/resources/scripts/tablas.sql'";
        String sql = "CREATE TABLE IF NOT EXISTS USUARIO (\n" +
                "  ID            BIGINT PRIMARY KEY,\n" +
                "  USERNAME      VARCHAR2(30),\n" +
                "  NOMBRE        VARCHAR2(30),\n" +
                "  PASSWORD      VARCHAR2(30),\n" +
                "  ADMINISTRATOR BOOLEAN,\n" +
                "  AUTOR         BOOLEAN,\n" +
                "  COOKIES       VARCHAR2(255),\n" +
                "  ACTIVO        BOOLEAN\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE IF NOT EXISTS ETIQUETA (\n" +
                "  ID       BIGINT PRIMARY KEY,\n" +
                "  ETIQUETA VARCHAR2(200),\n" +
                "  ACTIVO   BOOLEAN\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE IF NOT EXISTS ARTICULO (\n" +
                "  ID       BIGINT PRIMARY KEY,\n" +
                "  TITULO   VARCHAR2(70),\n" +
                "  CUERPO   VARCHAR2(900000),\n" +
                "  ID_AUTOR BIGINT REFERENCES USUARIO (ID) ON UPDATE CASCADE,\n" +
                "  FECHA    DATE,\n" +
                "  ACTIVO   BOOLEAN\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE IF NOT EXISTS COMENTARIO (\n" +
                "  ID          BIGINT PRIMARY KEY,\n" +
                "  COMENTARIO  VARCHAR2(1000),\n" +
                "  ID_AUTOR    BIGINT REFERENCES USUARIO (ID) ON UPDATE CASCADE,\n" +
                "  ID_ARTICULO BIGINT REFERENCES ARTICULO (ID) ON UPDATE CASCADE,\n" +
                "  ACTIVO      BOOLEAN\n" +
                ");\n" +
                "CREATE TABLE IF NOT EXISTS ARTICULO_COMENTARIOS (\n" +
                "  ID_ARTICULO   BIGINT REFERENCES ARTICULO (ID) ON UPDATE CASCADE,\n" +
                "  ID_COMENTARIO BIGINT REFERENCES COMENTARIO (ID) ON UPDATE CASCADE,\n" +
                "  ACTIVO        BOOLEAN\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE IF NOT EXISTS ARTICULO_ETIQUETAS (\n" +
                "  ID_ARTICULO BIGINT REFERENCES ARTICULO (ID) ON UPDATE CASCADE,\n" +
                "  ID_ETIQUETA BIGINT REFERENCES ETIQUETA (ID) ON UPDATE CASCADE,\n" +
                "  ACTIVO      BOOLEAN\n" +
                ");\n" +
                "\n";
        Conexion con = new Conexion();
        conexion = con.getConexion();
        conexion.open();

        conexion.createQuery(sql).executeUpdate();
        System.out.println("Base de datos creada con exito.");

    }

    public void borrarUsuario(Long id){
        String sql = "update usuario set activo=false where id="+id;
        Conexion con = new Conexion();
        conexion = con.getConexion();
        conexion.open();
        conexion.createQuery(sql).executeUpdate();
    }

    public Usuario getSesion(String sesion) {
            String sql = "select * from usuario where cookies = '" +sesion+"' and activo = true";
            Conexion con = new Conexion();
            conexion = con.getConexion();
            conexion.open();
            return conexion.createQuery(sql).executeAndFetchFirst(Usuario.class);
    }

    public Usuario getUsuario(String nombre, String pass){
       String sql = "select * from usuario where username = '" + nombre +"' and password = '" + pass + "' and activo = true";
        Conexion con = new Conexion();
        conexion = con.getConexion();
        conexion.open();
        return conexion.createQuery(sql).executeAndFetchFirst(Usuario.class);
    }

    public void saveCookies(Long id, String sesion){
        String sql ="UPDATE usuario SET cookies='" + sesion + "' WHERE id='" + id + "'";
        Conexion con = new Conexion();
        conexion = con.getConexion();
        conexion.open();
        conexion.createQuery(sql).executeUpdate();

    }


}
