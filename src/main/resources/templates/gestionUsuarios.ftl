

<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/html">

<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<head>
    <title>BLOG DEL CLAN UCHICHA!</title>
    <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
    <link href="css/style.css" rel='stylesheet' type='text/css' />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!----webfonts---->
    <link href='http://fonts.googleapis.com/css?family=Oswald:100,400,300,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900,300italic' rel='stylesheet' type='text/css'>
    <!----//webfonts---->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <!--end slider -->
    <!--script-->
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
    <!--/script-->
    <script type="text/javascript">
        jQuery(document).ready(function($) {
            $(".scroll").click(function(event){
                event.preventDefault();
                $('html,body').animate({scrollTop:$(this.hash).offset().top},900);
            });
        });
    </script>
    <!---->
</head>
<body>
<!---header---->
<div class="header">
    <div class="container">
        <div class="logo">
            <a href="/"><img src="images/logo.jpg" title="" /></a>
        </div>
        <!---start-top-nav---->
        <div class="top-menu">
            <div class="search">
                <form>
                    <input type="text" placeholder="" required="">
                    <input type="submit" value=""/>
                </form>
            </div>

            <span class="menu"> </span>
            <ul>
                <li class="active"><a href="/">HOME</a></li>

	<#if usuario??>
        <#if usuario.administrator || usuario.autor>
        <li><a href="/agregarArticulo">CREAR ARTICULO</a></li>
        </#if>
    </#if>

	<#if usuario??>
        <#if usuario.administrator>
        <li><a href="/crearUsuario">NUEVO USUARIO</a></li>
        </#if>
    </#if>

	<#if usuario??>
        <li><a href="/logout">LOG OUT</a></li>
    <#else>
        <li><a href="/login">LOG IN</a></li>
    </#if>
                <div class="clearfix"> </div>
            </ul>
        </div>
        <div class="clearfix"></div>

        <script>
            $("span.menu").click(function(){
                $(".top-menu ul").slideToggle("slow" , function(){
                });
            });
        </script>
        <!---//End-top-nav---->
    </div>
</div>
<!--/header-->

<div class="container">
    <div class="row">
        <div class="col-md-5  toppad  pull-right col-md-offset-3 ">

            <br>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >

   	<#list usuarioList as usuarios>
          <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">${usuarios.username}</h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-3 col-lg-3 " align="center"> <img alt="User Pic" src="https://i1.wp.com/www.winhelponline.com/blog/wp-content/uploads/2017/12/user.png?fit=256%2C256&quality=100&ssl=1" class="img-circle img-responsive"> </div>


                <div class=" col-md-9 col-lg-9 ">
                  <table class="table table-user-information">
                    <tbody>
                      <tr>
                        PERMISOS DEL USUARIO:
                        <p>______________________________________________</p>
                        <br/>
                        	<#if usuarios.autor && usuarios.administrator>
                        Administrador
                        <br/>
                        Autor
                        <br/>
                            <#elseif usuarios.autor>
                        Autor

                            <#else>
                        Usuario

        </tr>

        </tbody>
        </table>

        </div>
        </div>
        </div>

        </#if>
        <br/>
         <div class="panel-footer">
         <a href="usuario/editar/${usuarios.id}" data-original-title="Edit this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>
         <a href="usuario/eliminar/${usuarios.id}" data-original-title="Remove this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></a>
    </#list>
                </div>
             </div>
        </div>
    </div>
</div>