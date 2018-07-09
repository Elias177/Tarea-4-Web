
<!DOCTYPE HTML>
<html>
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
 						 <#if admin || autor>
  						<li><a href="/agregarArticulo">CREAR ARTICULO</a></li>
                         </#if>
 						 <#if admin>
  						<li><a href="usuario/crearUsuario">NUEVO USUARIO</a></li>
                         </#if>
 						 <#if admin || autor>
  						<li><a href="/logout">LOG OUT</a></li>
                         <#else>
 						 <li><a href="/login">																										LOG IN</a></li>
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
<div class="content">
    <div class="container">
        <div class="content-grids">
            <div class="col-md-8 content-main">
                <div class="content-grid">
                <div class="content-grid-info">
						 <#list LosArticulos as articulo>
                        <img src="images/bar1.jpg" alt=""/>
						 <div class="post-info">
                             <#if articulo.cuerpo?length &lt; 71>
                                 <h4><a href="/articulo/${articulo.id}">${articulo.titulo}</a>   ${articulo.fecha}</h4>
                                 <p>${articulo.cuerpo}</p>
                                 <a href="/articulo/${articulo.id}"><span></span>READ MORE</a><p>        </p>
                                 <#if articulo.listaEtiqueta?size gt 0>
                                     <#list articulo.listaEtiqueta as etiqueta>
                                        <a href="homeTags/${etiqueta.etiqueta}?pagina=1" class="label label-success">${etiqueta.etiqueta}</a><p>        </p>
                                     </#list>
                                 </#if>
                                 <h6><span class="label label-primary">Likes: ${articulo.likes}</span> <span class="label label-danger">Dislikes: ${articulo.dislikes}</span></h6>
						 </div>
					 </div>
                             <#else>
						<div class="content-grid-info">
                            <div class="post-info">
                                <h4><a href="/articulo/${articulo.id}">${articulo.titulo}</a>   ${articulo.fecha}</h4>
                                <p>${articulo.cuerpo?substring(0,70)}...</p>
                                <a href="/articulo/${articulo.id}"><span></span>READ MORE</a><p>        </p>
                                 <#if articulo.listaEtiqueta?size gt 0>
                                     <#list articulo.listaEtiqueta as etiqueta>
                                        <a href="homeTags/${etiqueta.etiqueta}?pagina=1" class="label label-success">${etiqueta.etiqueta}</a>
                                     </#list>
                                 </#if>
                            </div>
                        </div>
                             </#if>
                         </#list>

                    <div class="col-md-4 content-right">
                        <div class="recent">
                            <h3>RECENT POSTS</h3>
                            <#list LosArticulos as articulo>
                            <ul>
                                <li><a href="/articulo/${articulo.id}">${articulo.titulo}</a></li>

                            </ul>
                            </#list>
                        </div>


                        <div class="comments">
                            <h3>RECENT COMMENTS</h3>
                            <ul>
                                <li><a href="#">Amada Doe </a> on <a href="#">Hello World!</a></li>
                                <li><a href="#">Peter Doe </a> on <a href="#"> Photography</a></li>
                                <li><a href="#">Steve Roberts  </a> on <a href="#">HTML5/CSS3</a></li>
                            </ul>
                        </div>

                        <div class="clearfix"></div>

                        <div class="clearfix"></div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
        <!---->
        <div class="footer">
            <div class="container">
                <ul class="pagination">
            <#if valorAnterior == 1>
					<li><a class="active" href="/inicio?pagina=${anterior}">&lt;&lt; Anterior</a></li>

            </#if>

            <#if valorSiguiente == 1>
                <li><a class="active" href="/inicio?pagina=${siguiente}">Siguiente &gt;&gt;</a></li>
            </#if>

                </ul>
                <p>Copyrights © 2015 Blog All rights reserved | Template by <a href="http://w3layouts.com/">W3layouts</a></p>
            </div>
        </div>
