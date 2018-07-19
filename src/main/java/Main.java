import ORM.*;
import clases.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.jasypt.util.text.BasicTextEncryptor;
import spark.Session;

import java.io.StringWriter;
import java.sql.Date;
import java.util.*;

import static spark.Spark.*;

public class Main {



    public static void main(String[] args) {
        staticFiles.location("/templates");
       // org.h2.tools.Server.createTcpServer().start();
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setClassForTemplateLoading(Main.class, "/");
        ORM.UsuarioORM usuarioORM = new UsuarioORM();

        if(usuarioORM.countUsuarios() == 0){
            Usuario admin = new Usuario("admin","admin","admin",true,true);
            usuarioORM.guardarUsuario(admin);
            System.out.println("Admin creado con exito.");
        }


        ORM.ArticuloORM articuloORM = new ArticuloORM();
        ORM.EtiquetaORM etiquetaORM = new EtiquetaORM();
        ORM.ComentarioORM comentarioORM = new ComentarioORM();
        ORM.ReaccionORM reaccionORM = new ReaccionORM();

        before("/agregarArticulo", (request, response) -> {
            Usuario usuario = request.session(true).attribute("usuario");
            if (usuario == null || (!usuario.isAdministrator() && !usuario.isAutor())) {
                response.redirect("/");
            }
        });


        before("/gestionUsuario", (request, response) -> {
            Usuario usuario = request.session(true).attribute("usuario");
            if (usuario == null || (!usuario.isAdministrator())) {
                response.redirect("/");
            }
        });

        before("/usuario/eliminar/:id", (request, response) -> {
            Usuario usuario = request.session(true).attribute("usuario");
            if (usuario == null || (!usuario.isAdministrator())) {
                response.redirect("/");
            }
        });

        before("/editarUsuario/:id", (request, response) -> {
            Usuario usuario = request.session(true).attribute("usuario");
            if (usuario == null || (!usuario.isAdministrator())) {
                response.redirect("/");
            }
        });

        before("/agregarUsuario", (request, response) -> {
            Usuario usuario = request.session(true).attribute("usuario");
            if (usuario == null || (!usuario.isAdministrator())) {
                response.redirect("/");
            }
        });

        before("/articulo/eliminar/:id", (request, response) -> {
            Usuario usuario = request.session(true).attribute("usuario");
            if (usuario == null || (!usuario.isAdministrator() && !usuario.isAutor())) {
                response.redirect("/");
            }
        });

        before("/articulo/editar/:id", (request, response) -> {
            Usuario usuario = request.session(true).attribute("usuario");
            if (usuario == null || (!usuario.isAdministrator() && !usuario.isAutor())) {
                response.redirect("/");
            }
        });

        before("/usuario/editar/:id", (request, response) -> {
            Usuario usuario = request.session(true).attribute("usuario");
            if (usuario == null || (!usuario.isAdministrator() && !usuario.isAutor())) {
                response.redirect("/");
            }
        });

        get("/", (req, res) -> {

            Usuario usuario = req.session(true).attribute("usuario");
            if(usuario == null && req.cookie("username") != null){
                BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
                textEncryptor.setPasswordCharArray("mangekyouSharingan42".toCharArray());
                req.session(true);
                req.session().attribute("usuario", usuarioORM.getUsuarioUsername(textEncryptor.decrypt(req.cookie("username"))));
                //res.redirect("/");
            }

            res.redirect("/inicio?pagina=1");
            return "";
        });


        get("/inicio", (req, res) -> {

            Usuario usuario = req.session(true).attribute("usuario");
            StringWriter writer = new StringWriter();
            Map<String, Object> atr = new HashMap<>();
            Template template = configuration.getTemplate("templates/home.ftl");

            int pagina = Integer.parseInt(req.queryParams("pagina"));
            int maxPagina = (int) Math.ceil(articuloORM.countArticulos() / 5);
            List<Articulo> articuloList = articuloORM.listarArticulos(pagina);

            for(int i = 0; i < articuloList.size(); i++){
                articuloList.get(i).setListaEtiqueta(etiquetaORM.getEtiquetas(articuloList.get(i).getId()));
                articuloList.get(i).setLikes(articuloORM.countLikes(articuloList.get(i).getId()));
                articuloList.get(i).setDislikes(articuloORM.countDislikes(articuloList.get(i).getId()));
            }

            atr.put("pagina", pagina);

            if(pagina >= maxPagina){
                atr.put("valorSiguiente", 0);
            }else{
                atr.put("valorSiguiente", 1);
            }

            if(pagina <= 1){
                atr.put("valorAnterior", 0);
            }else{
                atr.put("valorAnterior", 1);
            }

            atr.put("anterior", (pagina - 1));
            atr.put("siguiente", (pagina + 1));
            atr.put("usuario",usuario);
            atr.put("LosArticulos",articuloList);
            template.process(atr,writer);
            return writer;
        });


        get("/homeTags/:etiqueta", (req, res) -> {
            StringWriter writer = new StringWriter();
            Map<String, Object> atr = new HashMap<>();
            Template template = configuration.getTemplate("templates/homeTags.ftl");

            List<Articulo> articuloList = articuloORM.listarArticulos(1);
            Usuario usuario = req.session(true).attribute("usuario");

            for(int i = 0; i < articuloList.size(); i++){
                articuloList.get(i).setListaEtiqueta(etiquetaORM.getEtiquetas(articuloList.get(i).getId()));
            }


            int pagina = Integer.parseInt(req.queryParams("pagina"));

            ArrayList<Articulo> filtrados = new ArrayList<>();

            for(int j = 0; j < articuloList.size(); j++){
                for(int k = 0; k < articuloList.get(j).getListaEtiqueta().size(); k++){
                    if(articuloList.get(j).getListaEtiqueta().get(k).getEtiqueta().equals(req.params("etiqueta"))){
                        articuloList.get(j).setLikes(articuloORM.countLikes(articuloList.get(j).getId()));
                        articuloList.get(j).setDislikes(articuloORM.countDislikes(articuloList.get(j).getId()));
                        filtrados.add(articuloList.get(j));

                    }
                }
            }

            int maxPagina = (int) Math.ceil(filtrados.size() / 5);
            atr.put("pagina", pagina);

            if(pagina >= maxPagina){
                atr.put("valorSiguiente", 0);
            }else{
                atr.put("valorSiguiente", 1);
            }

            if(pagina <= 1){
                atr.put("valorAnterior", 0);
                System.out.println(pagina);
            }else{
                atr.put("valorAnterior", 1);
            }

            atr.put("anterior", (pagina - 1));
            atr.put("siguiente", (pagina + 1));
            atr.put("usuario",usuario);
            atr.put("LosArticulos",filtrados);
            atr.put("etiquetaFiltro",req.params("etiqueta"));
            template.process(atr,writer);
            return writer;
        });

        get("/login", (req, res) -> {
            StringWriter writer = new StringWriter();
            Map<String, Object> atr = new HashMap<>();
            Template template = configuration.getTemplate("templates/login.ftl");
            template.process(atr, writer);

            Map<String, String> cookies = req.cookies();

            return writer;
        });



        post("/login", (req, res) -> {

            String username = req.queryParams("username");
            String password = req.queryParams("password");
            Usuario usuario = usuarioORM.getUsuario(username, password);
            String isRecordado = req.queryParams("keepLog");
            if (usuario != null) {
                req.session(true);
                req.session().attribute("usuario", usuario);
                if(isRecordado!=null){
                    BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
                    textEncryptor.setPasswordCharArray("mangekyouSharingan42".toCharArray());
                    res.cookie("/", "username",
                            textEncryptor.encrypt(username), (60*60*24*7), false, true);
                }
                res.redirect("/");
            } else {
                res.redirect("/login");
            }
        return "";

        });




        get("/logout", (req, res) -> {
            StringWriter writer = new StringWriter();
            Map<String, Object> atr = new HashMap<>();

            Session ses = req.session(true);
            ses.invalidate();
            res.removeCookie("username");
            res.redirect("/");
            return writer;
        });

        post("/agregarUsuario", (req, res) -> {

            String user = req.queryParams("username");
            String nombre = req.queryParams("nombre");
            String password = req.queryParams("password");
            String administrator = req.queryParams("administrator");

            if(administrator == null){
                administrator = "false";
            }else if(administrator.equals("on")){
                administrator="true";
            }

            String autor = req.queryParams("autor");

            if(autor == null){
                autor = "false";
            }else if(autor.equals("on")){
                autor="true";
            }

            Usuario u =  new Usuario(user,nombre,password,Boolean.valueOf(administrator),Boolean.valueOf(autor));

            usuarioORM.guardarUsuario(u);


            res.redirect("/");

            return null;
        });

        get("/agregarArticulo", (req, res) -> {
            StringWriter writer = new StringWriter();
            Map<String, Object> atr = new HashMap<>();
            Template temp = configuration.getTemplate("templates/agregarArticulo.ftl");

            temp.process(null, writer);

            return writer;
        });

        post("/agregarArticulo", (req, res) -> {

            Usuario usuario = req.session(true).attribute("usuario");
            String titulo = req.queryParams("titulo");
            String cuerpo = req.queryParams("cuerpo");
            String etiquetas = req.queryParams("etiquetas");
            List<String> listaEtiquetas = Arrays.asList(etiquetas.split(","));

            ArrayList<Etiqueta> et =  new ArrayList<>();
            for(int i = 0; i < listaEtiquetas.size(); i++){
                Etiqueta e = new Etiqueta(listaEtiquetas.get(i));
                et.add(e);
                etiquetaORM.guardarEtiqueta(e);
            }

            Date d = new Date(System.currentTimeMillis());

            Articulo a =  new Articulo(titulo,cuerpo,usuario,d,null,et);
            a.setLikes(0);
            a.setDislikes(0);
            articuloORM.guardarArticulo(a);


            res.redirect("/");



            return null;
        });

        get("articulo/:id", (req, res) -> {
            StringWriter writer = new StringWriter();
            Map<String, Object> atributos = new HashMap<>();
            Usuario usuario = req.session(true).attribute("usuario");
            Template template = configuration.getTemplate("templates/indexArticulo.ftl");
            Articulo articulo = articuloORM.getArticulo(Long.parseLong(req.params("id")));
            int likes = articuloORM.countLikes(articulo.getId());
            int dislikes = articuloORM.countDislikes(articulo.getId());
            articulo.setListaEtiqueta(etiquetaORM.getEtiquetas(articulo.getId()));
            articulo.setListaComentarios(comentarioORM.getComentario(articulo.getId()));

            List<Articulo> articuloList = articuloORM.listarArticulos(1);

            for(int i = 0; i < articuloList.size(); i++){
                articuloList.get(i).setListaEtiqueta(etiquetaORM.getEtiquetas(articuloList.get(i).getId()));
                articuloList.get(i).setLikes(articuloORM.countLikes(articuloList.get(i).getId()));
                articuloList.get(i).setDislikes(articuloORM.countDislikes(articuloList.get(i).getId()));
            }
            atributos.put("articulo", articulo);
            atributos.put("usuario",usuario);
            atributos.put("likes",likes);
            atributos.put("dislikes",dislikes);
            atributos.put("LosArticulos",articuloList);
            template.process(atributos, writer);

            return writer;
        });



        post("articulo/:id/comentar", (req, res) -> {
            Usuario usuario = req.session(true).attribute("usuario");
            Long idArticulo = Long.parseLong(req.params("id"));
            String comentario = req.queryParams("comentario");
            Comentario c = new Comentario(comentario,usuario,articuloORM.getArticulo(idArticulo));
            comentarioORM.guardarComentario(c);
            res.redirect("/articulo/" + idArticulo);
            return null;
        });

        get("articulo/:id/like", (req, res) -> {
            Usuario usuario = req.session(true).attribute("usuario");
            Long idArticulo = Long.parseLong(req.params("id"));
            Articulo articulo = articuloORM.getArticulo(idArticulo);
            Reaccion re = reaccionORM.checkLike(usuario,articulo);
            if(re == null){
                reaccionORM.guardarLike(new Reaccion(articulo,usuario,true));
                res.redirect("/articulo/" + idArticulo);
            }else{
                reaccionORM.deleteLike(re);

                if(!re.isReaccion())
                    reaccionORM.updateLike(re,true);

                res.redirect("/articulo/" + idArticulo);
            }




            return null;
        });

        get("articulo/:id/dislike", (req, res) -> {
            Usuario usuario = req.session(true).attribute("usuario");
            Long idArticulo = Long.parseLong(req.params("id"));
            Articulo articulo = articuloORM.getArticulo(idArticulo);
            Reaccion re = reaccionORM.checkLike(usuario,articulo);
            if(re == null){
                reaccionORM.guardarLike(new Reaccion(articulo,usuario,false));
                res.redirect("/articulo/" + idArticulo);
            }else{
                reaccionORM.deleteLike(re);

                if(re.isReaccion())
                    reaccionORM.updateLike(re,false);

                res.redirect("/articulo/" + idArticulo);
            }

            return null;
        });

        get("articulo/eliminar/:id", (req, res) -> {
            StringWriter writer = new StringWriter();
            Map<String, Object> atributos = new HashMap<>();
            Template template = configuration.getTemplate("templates/eliminarArticulo.ftl");

            Articulo articulo = articuloORM.getArticulo(Long.parseLong(req.params("id")));
            atributos.put("articulo", articulo);
            template.process(atributos, writer);

            return writer;
        });


        get("articulo/editar/:id", (req, res) -> {
            StringWriter writer = new StringWriter();
            Map<String, Object> atributos = new HashMap<>();
            Template template = configuration.getTemplate("templates/editarArticulo.ftl");

            Articulo articulo = articuloORM.getArticulo(Long.parseLong(req.params("id")));
            String listEtiquetas = "";
            for(int i = 0; i < articulo.getListaEtiqueta().size(); i++){
                listEtiquetas += articulo.getListaEtiqueta().get(i).getEtiqueta() + ",";

            }
            listEtiquetas = listEtiquetas.replaceAll("\\s+","");
            System.out.println(listEtiquetas);
            atributos.put("articulo", articulo);
            atributos.put("listaEtiquetas",listEtiquetas);
            template.process(atributos, writer);

            return writer;
        });

        post("/eliminar/:id", (req, res) -> {
            articuloORM.borrarArticulo(Long.valueOf(req.params("id")));
            res.redirect("/inicio?pagina=1");
            return null;
        });


        post("/editar/:id", (req, res) -> {
            long id = Long.parseLong(req.params("id"));
            String titulo = req.queryParams("titulo");
            String cuerpo = req.queryParams("cuerpo");
            String etiquetas = req.queryParams("etiquetas");
            List<String> listaEtiquetas = Arrays.asList(etiquetas.split(","));
            ArrayList<Etiqueta> etiquetaArrayList = new ArrayList<>();
            System.out.println(listaEtiquetas);
            Articulo art = articuloORM.getArticulo(id);
            List<Etiqueta> etiquetaList = etiquetaORM.getEtiquetas(id);

            for(int j = 0; j<listaEtiquetas.size(); j++){
                Etiqueta e = new Etiqueta(listaEtiquetas.get(j));
                etiquetaArrayList.add(e);
            }
            art.setListaEtiqueta(etiquetaArrayList);
            art.setListaComentarios(null);
            art.setLikes(0);
            art.setDislikes(0);
            articuloORM.editarArticulo(art,titulo,cuerpo,etiquetaArrayList);

            res.redirect("/");

            return null;
        });


            get("/gestionUsuario", (req, res) -> {
                Usuario usuario = req.session(true).attribute("usuario");
                StringWriter writer = new StringWriter();
                Map<String, Object> atributos = new HashMap<>();
                Template temp = configuration.getTemplate("templates/gestionUsuarios.ftl");
                if(req.queryParams("esAdmin")!=null)
                atributos.put("esAdmin",Integer.parseInt(req.queryParams("esAdmin")));


                atributos.put("usuarioList",usuarioORM.getUsuarioList());
                atributos.put("usuario",usuario);
                temp.process(atributos, writer);

                return writer;
            });
        get("/crearUsuario", (req, res) -> {
            StringWriter writer = new StringWriter();
            Template temp = configuration.getTemplate("templates/crearUsuario.ftl");

            temp.process(null, writer);

            return writer;
        });
        get("/usuario/eliminar/:id", (req, res) -> {
            StringWriter writer = new StringWriter();
            Map<String, Object> atributos = new HashMap<>();
            Template template = configuration.getTemplate("templates/eliminarUsuario.ftl");

            Usuario usuario = usuarioORM.getUsuarioId(Long.parseLong(req.params("id")));
            atributos.put("usuario", usuario);
            template.process(atributos, writer);

            return writer;
        });
        post("/usuarioEliminar/:id", (req, res) -> {
            String re = usuarioORM.dropUsuario(Long.valueOf(req.params("id")));
            System.out.println(re);
            if(re.equalsIgnoreCase("no")){
                res.redirect("/gestionUsuario?esAdmin=1");
            }else{
                res.redirect("/gestionUsuario?esAdmin=0");
            }

            return null;
        });

        get("usuario/editar/:id", (req, res) -> {
            StringWriter writer = new StringWriter();
            Map<String, Object> atributos = new HashMap<>();
            Template template = configuration.getTemplate("templates/editarUsuario.ftl");

            Usuario usuario = usuarioORM.getUsuarioId(Long.parseLong(req.params("id")));

            atributos.put("usuario", usuario);
            template.process(atributos, writer);

            return writer;
        });

        post("/editarUsuario/:id", (req, res) -> {
            long id = Long.parseLong(req.params("id"));
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            String administrator = req.queryParams("administrator");
            Usuario usuario = usuarioORM.getUsuarioId(id);
            boolean admin = usuario.isAdministrator();
            boolean a = usuario.isAutor();
            if(administrator == null){
                admin = false;
            }else if(administrator.equals("on")){
                admin = true;
            }

            String autor = req.queryParams("autor");

            if(autor == null){
                a=false;
            }else if(autor.equals("on")){
                a=true;
            }


            usuarioORM.editarUsuario(usuario,username,password,a,admin);

            res.redirect("/gestionUsuario");

            return null;
        });



    }


}