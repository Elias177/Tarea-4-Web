<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Blog de Web</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link href="https://stackpath.bootstrapcdn.com/bootswatch/4.1.1/lumen/bootstrap.min.css" rel="stylesheet" integrity="sha384-87sz15XXg/vK1YUT8aQFH3EIw5aC/jbpolvTC264SVJfjOIBWv0V/o2FyyKclY8G" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-dark bg-primary">
    <a class="navbar-brand" href="/">Blog Uchiha</a>

    <ul class="nav navbar-nav">

      <#if admin || autor>
      <li class="nav-item">
          <a class="btn btn-link text-light" href="/agregarArticulo">Crear artículo</a>
      </li>
      </#if>
      <#if admin>
      <li class="nav-item">
          <a class="btn btn-link text-light" href="usuario/crearUsuario">Nuevo usuario</a>
      </li>
      </#if>

    </ul>

    <#if admin || autor>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item">
            <a href="/logout">Log out</a>
        </li>
    </ul>
    <#else>
   <ul class="nav navbar-brand navbar-right">
       <a style="color:white;" href="/login"><i class="fas fa-sign-in-alt"></i> Log in</a>
   </ul>
    </#if>
</nav>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

<div class="col-12 p-2">
    <div class="row">
        <#list LosArticulos as articulo>
<div class="container">
    <div class="left-panel">
        <div class="col-xs-12 col-sm-6 col-lg-8">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="col-md-12">
                        <div class="row">
                            <h3 class="card-title">${articulo.titulo}</h3>
                            <div class="col-sm-3">
                                <h4 class="pull-right">
                                    <i class="fas fa-calendar-alt"></i> ${articulo.fecha}
                                </h4>
                            </div>
                        </div>
                    </div>
                </div>
                <#if articulo.cuerpo?length &lt; 71>
                    <div class="panel-body">${articulo.cuerpo}
                        <a href="/articulo/${articulo.id}">Leer más...</a>
                    </div>
                <#else>
                    <div class="panel-body">${articulo.cuerpo?substring(0,70)}...
                        <a href="/articulo/${articulo.id}">Leer más...</a>
                    </div>
                </#if>

                <div class="card-footer p-2">
                    <strong class="text-danger m-0">

                            <#if articulo.listaEtiqueta?size gt 0>
                                    <#list articulo.listaEtiqueta as etiqueta>
                                        <a href="homeTags/${etiqueta.etiqueta}?pagina=1" class="label label-default"">${etiqueta.etiqueta}</a>
                                    </#list>
                            </#if>
                    </strong>
                </div>
            </div>
        </div>
        </#list>
    </div>
    </div>



</body>
<footer>
    <div class="container">
        <ul class="pagination">
            <#if valorAnterior == 1>
					<li><a class="active" href="/inicio?pagina=${anterior}">&lt;&lt; Anterior</a></li>

            </#if>

            <#if valorSiguiente == 1>
                <li><a class="active" href="/inicio?pagina=${siguiente}">Siguiente &gt;&gt;</a></li>
            </#if>

        </ul>
    </div>

</footer>
</html>
