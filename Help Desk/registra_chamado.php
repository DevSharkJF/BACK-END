<?php
    // $texto = $_SESSION['id'].'#'.$_POST['titulo'].'#'.$_POST['categoria']."#".$_POST['descricao'].PHP_EOL;
    // CONSTANTE PHP_EOL = end of line --> armazena a quebra de linha de acordo com o sistema operional que está
    //rodando o projeto

    session_start();
    //Montando o texto
    $titulo = str_replace('#','-',$_POST['titulo']);
    $categoria = str_replace('#','-',$_POST['categoria']);
    $descricao = str_replace('#','-',$_POST['descricao']);

    $texto = $_SESSION['id'].'#'.$titulo.'#'.$categoria.'#'.$descricao.PHP_EOL;

    /* fopen
    Abre um arquivo, e caso ele não exista, é criado
    Possui 2 parâmetros; o primeiro é o nome do arquivo e o segundo é a
    função que deseja executar nesse arquivo
    */
    $arquivo = fopen('arquivo.hd','a');

    /*fwrite
    Escreve um texto no arquivo, possuindo 2 parâmetros
    O primeiro é a referência do arquivo que foi aberto,
    o segundo é o que será escrito dentro do arquivo
    */
    fwrite($arquivo,$texto);

    /*fclose
    Fecha um arquivo que foi aberto
    */
    fclose($arquivo);
    header('Location: abrir_chamado.php')
?>