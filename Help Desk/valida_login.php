<?php
    /*
    A instruçaõ deve anteceder as demais, pois inicia as
    configurações de sessão de um arquivo
    */
    session_start();

    /*
    $_SESSION = [''];
    Variável superglobal utilizada para armazenar dados de um usuário
    entre várias páginas de um site.
    Os sites navegam via protocolo HTTP, a sessão cria uma "memória" para o visitante
    Funciona como um array
    */

    /*
    Controle que verifica se a autenticação foi realizada e o n° de ID's
    variável falsa pois se torna TURE ao entrar na condição do 'foreach'
    */
    $usuario_autenticado = false;
    $usuario_id = null;
    $usuario_perfil_id = null;

    $perfis = array(1 => 'Administrativo', 2 => 'Usuário');
    // Os perfis controlam quais vão ser usuários e quais vão ser administradores

    /*
    Usuários do Sistema
    São armazenados em um array multidimensional
    */
    $usuarios_app = array(
        array('id' => 1, 'email' => 'adm@teste.com.br', 'senha' => '1234', 'perfil_id' => 1),
        array('id' => 2, 'email' => 'user@teste.com.br', 'senha' => '1234', 'perfil_id' => 1),
        array('id' => 3, 'email' => 'jose@teste.com.br', 'senha' => '1234', 'perfil_id' => 2),
        array('id' => 4, 'email' => 'maria@teste.com.br', 'senha' => '1234', 'perfil_id' => 2)
    );

    foreach($usuarios_app as $user){
        /*
        Percorre os arrays de email e senha para fazer validação
        Para cada item em '$usuarios_app' ele adiciona o valor em '$user'
        */

        if($user['email'] == $_POST['email'] && $user['senha'] == $_POST['senha']){
            // Quando atender as condições estabelicidas, a autenticação será considerada 'true'
            /*  
            Quando atender as condições estabelecidas1, a autenticaçaõ será considera 'true
            '$user" recebe os valores de ID e do 'perfil_id' que estão no array
            */
            $usuario_autenticado = true;
            $usuario_id = $user['id'];
            $usuario_perfil_id = $user['perfil_id'];
        }
    }

    if($usuario_autenticado){
        //Caso a autenticação tenha sucesso, o usuário será redirecionado direto para a página 'home' do projeto
        $_SESSION['autenticado'] = 'SIM';
        $_SESSION['id'] = $usuario_id;
        $_SESSION['perfil_id'] = $usuario_perfil_id;
        header('Location: home.php');
        /*
        Quando a autenticação for TRUE, o usuário recebe a mensagem que foi autenticado e
        será redirecionado para a página 'home' do sistema
        */
    }else{  
        $_SESSION['autenticado'] = 'NÃO';
        header('Location: index.php?login=erro');
        /*
        Caso a autenticação do usuário retorne FALSE, ele será redirecionado
        para a página de login do sistema
        erro = o primeiro erro serve para quando o usuário não existe ou 
        informa seus dados de forma incorreta
        */
    }
    /* 
    header --> função que tem como parâmetro, um 'Location'. Ele redireciona o usuário para o
    arquivo contido nele
    */
?>