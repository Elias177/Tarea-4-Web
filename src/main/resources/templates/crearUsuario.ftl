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
                    </div>
                    <form class="col-11 py-5" method="post" action="/agregarUsuario">
                        <div class="form-group row">
                            <label for="user" class="col-sm-2 col-form-label">Nombre de usuario</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control rounded-0" name="username" placeholder="usuario" required="">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="password" class="col-sm-2 col-form-label">Password</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control rounded-0" name="password" placeholder="contraseña" required="">
                            </div>
                        </div>
                        <fieldset class="form-group">
                            <div class="row">
                                <legend class="col-form-label col-sm-2 pt-0">Seguridad</legend>
                                <div class="col-sm-10">
                                    <div class="radio">
                                        <input class="form-check-input" type="checkbox" name="administrator">
                                        <label class="form-check-label" for="administrator">Administrador </label>
                                    </div>
                                    <div class="radio">
                                        <input class="form-check-input" type="checkbox" name="autor">
                                        <label class="form-check-label" for="autor">Autor</label>
                                    </div>

                                    <div class="form-group row">
                                        <div class="col-sm-10">
                                            <button type="submit" class="btn btn-primary">Submit</button>
                                        </div>
                                    </div>
                    </form>
