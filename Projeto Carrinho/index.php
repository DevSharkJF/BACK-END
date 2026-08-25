<?php
session_start(); // Inicia a sessão
require_once 'products.php'; // Pega as informações dos produtos

$successMessage = ''; // Mensagem de sucesso ao adicionar um produto ao carrinho

if (isset($_GET['action']) && $_GET['action'] === 'add' && isset($_GET['id'])) { // Se a ação é adicionar ao carrinho e um ID de produto é passado
    $productId = $_GET['id'];

    if (isset($products[$productId - 1])) { // Verifica se o produto existe
        if (!isset($_SESSION['cart'])) { // Inicializa o carrinho
            $_SESSION['cart'] = [];
        }

        if (isset($_SESSION['cart'][$productId])) {  // Verifica se o produto já está no carrinho
            $_SESSION['cart'][$productId]['quantity'] += 1;
        } else {                                     // Adiciona o produto ao carrinho
            $_SESSION['cart'][$productId] = ['quantity' => 1, 'product' => $products[$productId - 1]];
            $successMessage = 'Produto adicionado no carrinho com sucesso!';
        }
    }
}

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Loja</title>
    <link rel="stylesheet" href="style.css">
    <script>
        function showMessage(message) { 
            alert(message);
        }
    </script>
</head>
<body>
    <h1>Produtos</h1>
    <?php if (!empty($successMessage)): ?> <!-- Exibe a mensagem de sucesso se houver -->
        <script>
            showMessage("<?php echo $successMessage; ?>");
        </script>
    <?php endif; ?>
    <div>
        <?php foreach ($products as $product): ?> <!-- Para cada produto -->
            <div>
                <img src="<?php echo $product['img']; ?>" alt="<?php echo $product['name']; ?>"> <!-- Imagem do produto -->
                <h3><?php echo $product['name']; ?></h3> <!-- Nome do produto -->
                <p><?php echo $product['description']; ?></p> <!-- Descrição do produto -->
                <p>Preço: $<?php echo number_format($product['price'], 2); ?></p> <!-- Preço do produto -->
                <form action="index.php" method="get">  <!-- Formulário para adicionar o produto ao carrinho -->
                    <input type="hidden" name="action" value="add">  <!-- Campo oculto com a action de add -->
                    <input type="hidden" name="id" value="<?php echo $product['id']; ?>"> <!-- Campo oculto para passar o id do produto -->
                    <button type="submit" class="add-to-cart-btn">Adicionar ao Carrinho</button> <!-- Botão para adicionar ao carrinho -->
                </form>
            </div>
        <?php endforeach; ?>
    </div>
    <a href="cart.php" class="view-cart-btn">Ver Carrinho</a> <!-- Botão para ver o carrinho -->
</body>
</html>