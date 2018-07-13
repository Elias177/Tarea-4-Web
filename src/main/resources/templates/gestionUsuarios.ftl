

<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/html">
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
<#list usuarioList as usuarios>
  <div class="content-grid">
   <div class="content-grid-info"> <img src="images/bar1.jpg" alt=""/>
     </a> <div class="post-info">
     <a href="usuario/editar/${usuarios.id}" class="text-success ml-2">
        <button type="button" class="btn btn-warning">Editar</button>
 </a>
<a href="usuario/eliminar/${usuarios.id}" class="text-primary ml-2">
<button type="button" class="btn btn-danger">Eliminar</button>
</br>
 </a>
<h4>${usuarios.username}</h4> Permisos:
 <#if usuarios.autor && usuarios.administrator>
        <ul>
         <li>
         Administrador
        </li>
                   <li>
                   Autor
        </li>

 <#elseif usuarios.autor>
        <li>
         Autor
        </li>
 <#else>
        <li>Usuario</li>
                     </ul>
 </#if>
</div>
  </div>
    </div>
</#list>

