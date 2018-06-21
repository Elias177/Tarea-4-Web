
<!doctype html>
<html lang="es">
<head>

    <title>Blog de Web</title>
    <link rel="stylesheet" href="/public/styles/style.css">


    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">


    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-dark" id = "custom-nav">
    <a href="/"> </a>

                                <ul class="nav navbar-nav">
                                        <#if admin || autor>
                                    <li class="nav-item">
                                        <a class="btn btn-link text-light" href="/agregarArticulo">
                                            Crear artículo
                                        </a>
                                    </li>
                                        </#if>
                                    <#if admin>
                                        <li class="nav-item">
                                            <a class="btn btn-link text-light" href="usuario/crearUsuario">
                                                Nuevo usuario
                                            </a>
                                        </li>
                                    </#if>

                                </ul>

                                <#if admin || autor>
                                    <ul class="navbar-nav ml-auto">
                                        <li class="nav-item">
                                            <a href="/logout">
                                                Log out
                                            </a>
                                        </li>
                                    </ul>
                                <#else>
                                <ul class="navbar-nav ml-auto">
                                    <a href="/login">
                                        Log in
                                    </a>
                                </li>

                                </ul>

                                </#if>

</nav>
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
                                <span class="label label-default">
                                    <#list articulo.listaEtiqueta as etiqueta>
                                        ${etiqueta.etiqueta}
                                    </#list>
                                </span>
                            </#if>
                    </strong>
                </div>
            </div>
        </div>
        </#list>
    </div>
    </div>

</body>
</html>
