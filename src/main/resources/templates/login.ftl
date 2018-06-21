

<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet" href="/styles/login.css">
<div class = "container">
    <div class="wrapper">
        <form class="form-signin" method="post" action="/login">
            <h3 class="form-signin-heading">Welcome Back! Please Sign In</h3>
            <hr class="colorgraph"><br>

            <input type="text" class="form-control rounded-0" name="username" placeholder="usuario" required="" autofocus="" />
            <input type="password" class="form-control rounded-0" name="password" placeholder="contraseña" required=""/>
            <input class="form-check-input" type="checkbox" name="keepLog">
            <label class="form-check-label" for="keepLog">
                <strong>Mantener Logeado</strong>
            </label>
            <button class="btn btn-lg btn-primary btn-block"  name="Submit" value="Login" type="Submit">Login</button>
        </form>
    </div>
</div>
