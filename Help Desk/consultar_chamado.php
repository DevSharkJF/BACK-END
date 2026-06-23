<?php
    require_once "validador_acesso.php";
    /*
    Caso ocorra qualquer problema dentro da validação, o arquivo é interrompido imediatamente.
    Isso ajuda a anular algumas brechas de segurança
  */
?>

<?php
    // Array de Chamados
    $chamados = array();

    // Abre um arquivo existente e utiliza o parâmetro 'r', feito para leitura
    $arquivo = fopen('arquivo.hd','r');

    // Enquanto houverem registros (linhas) a serem recuperados
    while(!feof($arquivo)){
        $registro = fgets($arquivo);
        $chamados[] = $registro;
        /*
        Percorre o arquivo enquanto houver registro
        feof é a função que identifica o fim de um arquivo
        fgets é a função que recupera o conteudo dentro de um arquivo
        */
    }
    fclose($arquivo);
    // Fecha o arquivo
?>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>App Help Desk</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        .card-consultar-chamado {
            padding: 30px 0 0 0;
            width: 100%;
            margin: 0 auto;
        }
    </style>
</head>

<body>
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="#">
            <img src="logo.png" width="30" height="30" class="d-inline-block align-top" alt="">App Help Desk
        </a>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a href="logoff.php" class="nav-link">SAIR</a>
            </li>
        </ul>
    </nav>

    <div class="container">
        <div class="row">
            <div class="card-consultar-chamado">
                <div class="card">
                    <div class="card-header">
                        Consulta de Chamado
                    </div>

                    <div class="card-body">
                        <?php foreach($chamados as $chamado){?>
                        <?php
                            // Percorre '$chamados' acrescentando os valores em '$chamado'
                            $chamado_dados = explode('#', $chamado);
                            // Divide a string contida no array '$chamado', baseado no delimitador '#'

                            if($_SESSION['perfil_id'] == 2){
                                if($chamado_dados[0] != $_SESSION['id']){
                                    continue;
                                    /*
                                    $chamado_dados[0] --> O índice zero é o número do ID do usuário
                                    Se o $_SESSION for igual a 2, significa que o usuário é um colaborador, então ele só pode
                                    vizualizar os chamados que ele mesmo criou, e não os de outros usuários
                                    Somente os perfis com 'perfil_id' == 1, que são os administradores, podem visualizar todos os chamados
                                    */
                                }
                            }
                            if(count($chamado_dados) < 3){
                                continue;
                            }
                            ?>

                            <div class="card mb-3 bg-light">
                                <div class="card-body">
                                    <h5 class="card-title"><?= $chamado_dados[1]?></h5>
                                    <h6 class="card-subtitle mb-2 text-muted"><?= $chamado_dados[2]?></h6>
                                    <p class="card-text"><?= $chamado_dados[3]?></p>
                                </div>
                            </div>
                        <?php } ?>

                        <div class="row mt-5">
                            <div class="col-6">
                                <a href="home.php" class="btn btn-lg btn-warning btn-block">Voltar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>