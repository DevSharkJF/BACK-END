# Segurança Back-End

Ao hospedar projetos ou sistemas na internet, é importante que dados sigilosos não fiquem disponíveis ao público (id,senhas,usuários,emails,api's)

Nesse projeto de help desk, está sendo usado o servidor local **XAMPP Control Panel**.
Os projetos ficam hospedados dentro do diretório público `htdocs`, o que possibilita requisições HTTP e tentativas de brechas de vulnerabilidades.

<hr>

## Novo Diretório

Uma maneira de ajudar a minimizar algumas vulnerabilidades, é deixar no diretório público `htdocs` somente os arquivos que não
possuem dados sigilosos, e criar um diretório dentro da pasta raiz do servidor local, o que dificulta o acesso a dados sigilosos.

```
C:/xampp
    - dados (diretório com os arquivos sigilosos)
    - htdocs/Help Desk
```

Os arquivos sigilosos que não poderiam ir a público, são:

* valida_login.php
* arquivo.hd

Esses arquivos devem ser **movidos** para a nova pasta `dados`. Após isso será necessário alterar alguns arquivos e incluir os arquivos
movidos no projeto novamente.

<hr>

## Incluindo Arquivos

Ao mover os arquivos para um novo diretório, será necessário incluir os scripts do `valida_login.php`, porém agora está em
outro diretório e fora do `htdocs`.

> Hierarquia dos Diretórios

Para fazer a busca em diretórios diferentes, é necessário usar o token `../`. Cada token, sobe um nível de hierarquia dos 
diretórios. Será necessário subir 2 níveis de hierarquia.

```
C:/xampp (2° nível de hierarquia)
    - dados (1° nível de hierarquia)
    - htdocs (1° nível de hierarquia)
        - Help Desk
```

Ao verificar os níveis de hierarquia, é necessário criar um novo arquivo que contenha uma inclusão dos scripts dos arquivos que foram movidos:

**Novo arquivo:**
seguranca.php

    ~~~
    <?php
        require "../../valida_login.php"
    ?>
    ~~~
O `require` irá buscar o arquivo `valida_login.php` dentro do diretório xampp.

> Alterações de Caminho

Os scripts que possuem qualquer parâmetro de caminho, devem ser alterados para que sejam salvos corretamente, utilizando a quantidade de tokens 
correspondentes ao nível da hierarquia dos diretórios. Nesse código, será alterado os arquivos `registra_chamado.php` e o `consultar_chamado.php`:

* registra_chamado.php --> $arquivo = fopen('../../arquivo.hd','a');
* consultar_chamado.php --> $arquivo = fopen('../../arquivo.hd','r');