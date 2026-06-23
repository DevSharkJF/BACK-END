<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>App Help Desk</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        .card-login {
            padding: 30px 0 0 0;
            width: 350px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="#">
            <img src="logo.png" width="30" height="30" class="d-inline-block align-top" alt="">
            App Help Desk
        </a>
    </nav>
    <div class="container">
        <div class="row">
            <div class="card-login">
                <div class="card">
                    <div class="card-header">LOGIN</div>
                    <div class="card-body">

                        <form action="valida_login.php" method="POST">
                            <div class="form-group">
                                <input name="email" type="email" class="form-control" placeholder="E-mail">
                            </div>
                            <div class="form-group">
                                <input name="senha" type="password" class="form-control" placeholder="Senha">
                            </div>

                            <?php
                            if(isset($_GET['login']) && $_GET['login'] == 'erro'){
                                /*
                                isset() --> Função que verifica se um determinado índice de array está setado antes de conseguir usá-lo
                                Com ele, é possível estabeler condições de controle de login
                                Verifica se o índice 'login' está setado dentro da superglobal GET, e se o valor for igual a 'erro',
                                será exibido uma mensagem de usuário ou senha inválido
                                */
                            ?>
                                <div class="text-danger">
                                    Usuário ou Senha Inválido(s)
                                </div>
                            <?php }

                            /*
                            O erro2 controla a exibição de mensagem quando um usuário tenta acessar qualquer página de forma direta,
                            sem efetuar um login antes
                            */
                            if(isset($_GET['login']) && $_GET['login'] == 'erro2'){
                            ?>
                                <div class="text-danger">
                                    Por favor, faça login antes de acessar as páginas protegidas
                                </div>
                            <?php } ?>
                            <button class="btn btn-lg btn-info btn-block">Entrar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>