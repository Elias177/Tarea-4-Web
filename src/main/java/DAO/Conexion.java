package DAO;

import org.sql2o.Sql2o;

public class Conexion {

    private static Sql2o sql2o = null;

    public Sql2o getConexion() {

        sql2o = new Sql2o("jdbc:h2:tcp://localhost/~/test", "sa", "");

        return sql2o;

    }



}
