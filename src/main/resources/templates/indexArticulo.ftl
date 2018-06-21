
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="panel-group">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h1 class="panel-title">
                            ${articulo.titulo}
                        </h1>
                        </br>
                        <#if admin || autor>
                            <a href="editar/${articulo.id}" class="text-success ml-2">
                                <button type="button" class="btn btn-warning">Editar</button>
                            </a>
                            <a href="eliminar/${articulo.id}" class="text-primary ml-2">
                                <button type="button" class="btn btn-danger">Eliminar</button>
                                </br>
                            </a>

                        </#if>
                    </div>
                    <div class="col-12 mt-2 bg-white px-4 rounded-0 login">
                        <div class="row">
                            <div class="col-sm-6 col-sm-offset-3">
                                <div class="panel panel-default">
                                    <div class="panel-heading">Articulo</div>
                                    <div class="panel-body">${articulo.cuerpo}</div>
                                </div>
                            </div>
                        </div>

                        <h6 class="col-12 pt-3">
                            <strong>Etiquetas</strong>

                        </h6>
                        <#if articulo.listaEtiqueta?size gt 0>
                          <span class="label label-default">
                                <#list articulo.listaEtiqueta as etiqueta>
                                    ${etiqueta.etiqueta}
                                </#list>
                          </span>
                        </#if>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header">
                    </div>

                    <div class="card-body">
                        <form class="col-11 py-5" method="post" action="/articulo/${articulo.id}/comentar">
                            <div class="panel px-2 py-3 bg-white">
                                <textarea name="comentario" class="form-control rounded-0"></textarea>
                            </div>

                            <div class="text-center">
                                <button class="btn btn-primary"type="submit">
                                    Comentar
                                </button>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="card">
                    <div class="card-header">
                        Comentarios
                    </div>
                    <div class="card-body">
                        <#list articulo.listaComentarios as comentario>
                            <div class="card col-12 mb-1 p-0">
                                <div class="card-body">
                                    <h5 class="card-title m-0">
                                        <strong>
                                            <i class="fas fa-user"></i> ${username}
                                        </strong>
                                    </h5>
                                </div>
                                <div class="card-footer p-2">
                                    <strong class="text-primary m-0">
                                        <i class="far fa-comment"></i> ${comentario.comentario}
                                    </strong>
                                </div>
                            </div>
                        </#list>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
