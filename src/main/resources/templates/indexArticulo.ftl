<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
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
<div class="single">
<div class="container">
<div class="col-md-8 single-main">
    <div class="single-grid">
        <h1 class="panel-title">${articulo.titulo}</h1>
        </br>
                        <#if usuario.administrator || usuario.autor>
                            <a href="editar/${articulo.id}" class="text-success ml-2">
                                <button type="button" class="btn btn-warning">Editar</button>
                            </a>

                            <a href="eliminar/${articulo.id}" class="text-primary ml-2">
                                <button type="button" class="btn btn-danger">Eliminar</button>
                                </br>
                            </a>

                        </#if>
        <img src="images/bar1.jpg" alt=""/>
        <p>${articulo.cuerpo}</p>
					<#if articulo.listaEtiqueta?size gt 0>
                        <#list articulo.listaEtiqueta as etiqueta>
								 <a href="homeTags/${etiqueta.etiqueta}?pagina=1" class="label label-success">${etiqueta.etiqueta}</a>
                        </#list>
                    </#if>
        <div>
            </br>
					<#if usuario.password != "no" && usuario.username != "no">
					<h5>
                        <form action="/articulo/${articulo.id}/like">
                            <form action="/articulo/${articulo.id}/dislike">
                                <button style="display: inline;" class="label label-primary" type="submit" >
                                    Like
                                </button>

                                <button style="display: inline;" class="label label-danger" type="submit">
                                    Dislike
                                </button>
                            </form>
                        </form>
                    </h5>
                    </#if>
        </div>

    </div>

				 <#list articulo.listaComentarios as comentario>
			 <ul class="comment-list">
                 <h5 class="post-author_head">Written by <a href="#" title="Posts by admin" rel="author">${comentario.autor.username}</a></h5>
                 <li><img src="images/avatar.png" class="img-responsive" alt="">
                     <div class="desc">
                         <p> Comentarios: ${comentario.autor.username}</p>
                     </div>
                     <div class="clearfix"></div>
                 </li>
             </ul>
                 </#list>

				  <div class="content-form">
                     <#if usuario.password != "no" && usuario.username != "no">
                         <h3>Leave a comment</h3>
                         <form method="post" action="/articulo/${articulo.id}/comentar">
                             <textarea placeholder="Message"></textarea>
                             <input type="submit" value="SEND"/>
                         </form>
						 </div>
                     </#if>
		  </div>

			  <div class="col-md-4 side-content">
                  <div class="recent">
                      <h3>RECENT POSTS</h3>
					 <#list LosArticulos as articulo>
					 <ul>
                         <li><a href="/articulo/${articulo.id}">${articulo.titulo}</a></li>

                     </ul>
                     </#list>
                  </div>
                  <div class="clearfix"></div>

              </div>
			  <div class="clearfix"></div>
		  </div>
	  </div>
</div>
<!---->
<div class="footer">
    <div class="container">
        <p>Copyrights © 2015 Blog All rights reserved | Template by <a href="http://w3layouts.com/">W3layouts</a></p>
    </div>
</div>
