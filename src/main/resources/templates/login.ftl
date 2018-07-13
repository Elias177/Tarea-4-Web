<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet" href="css/login.css">
<div class="wrapper fadeInDown">
    <div id="formContent">
        <!-- Tabs Titles -->

        <!-- Icon -->
        <div class="fadeIn first">
            <img src="http://danielzawadzki.com/codepen/01/icon.svg" id="icon" alt="User Icon" />
        </div>

        <!-- Login Form -->
        <form class="form-signin" method="post" action="/login">
            <input type="text" class="fadeIn second" name="username" placeholder="username" required="" autofocus="">
            <input type="password" class="fadeIn third" name="password" placeholder="contraseña" required="">

            <input name="Submit" type="Submit" class="fadeIn fourth" value="Login">
            <br/>
            <input class="form-check-input" type="checkbox" name="keepLog">
            <label class="form-check-label" for="keepLog">
                <strong>Recuerdame</strong>
            </label>
        </form>

    </div>
</div>