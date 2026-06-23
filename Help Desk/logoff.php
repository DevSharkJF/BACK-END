<?php
    /*
    Há duas maneiras de remover índices de array da sessão
    unset() --> função que remove um índice específico de um array
    session_destroy --> função que remove todos os índices de um array
    */
    session_start();
    session_destroy();
    //Encerra todos os índices da superglboal da sessão
    header('Location: index.php');
    /*
    Após destruir os índices, é comum forçar qualquer
    redirecionamento, para que haja uma nova requisição
    HTTP em que as variáveis não estarão mais disponíveis
    */
?>