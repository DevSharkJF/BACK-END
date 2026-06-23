<?php
  /*
  Esse arquivo irá controlar as sessões dos demais arquivos do projeto
  Em vez do bloco abaixo estar presente em cada arquivo de sessão, será adicionado
  somente nesse arquivo e depois, ele poderá ser incluído
  */

  // Inicia a sessão dentro do arquivo. Recomendado ficar no início do arquivo
  session_start();

  /*
  O 'isset' verifica se o array 'SESSION' está setado
  Utilizando o operador de negação '!', caso o usuário não esteja setado, ou o 'autenticado'
  seja diferente de 'SIM', o header força um redirecionamento para a página inicial 'index'
  */
  if(!isset($_SESSION['autenticado']) || $_SESSION['autenticado'] != 'SIM'){
    header('Location: index.php?login=erro2');
    // Ao tentar acessar uma página diretamente, sem efetuar login, o 'SESSION' irá bloquear e redirecionar para a página inicial
  }
?>