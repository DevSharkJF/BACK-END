<?php
session_start(); // Starta sessão
require_once 'products.php'; // Inclui os dados dos produtos

if (isset($_GET['action'])) { // Pega a action
    $action = $_GET['action'];

    switch ($action) {
        case 'remove': // Remove um produto do carrinho
            if (isset($_GET['id'])) { 
                $productId = $_GET['id'];
                if (isset($_SESSION['cart'][$productId])) {
                    unset($_SESSION['cart'][$productId]);
                }
            }
            break;
        case 'update': // Atualiza
            if (isset($_POST['update'])) {
                foreach ($_POST['quantity'] as $productId => $quantity) {
                    if (isset($_SESSION['cart'][$productId])) {
                        $_SESSION['cart'][$productId]['quantity'] = $quantity;
                    }
                }
            }
            break;
        case 'empty': // Esvazia
            unset($_SESSION['cart']);
            break;
    }
}

// Calcula o total do carrinho
$totalPrice = 0;

if (isset($_SESSION['cart']) && !empty($_SESSION['cart'])) {
    foreach ($_SESSION['cart'] as $productId => $item) {
        $totalPrice += $item['quantity'] * $item['product']['price'];
    }
}

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrinho de Compras</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h1>Carrinho de Compras</h1>
    <?php if (isset($_SESSION['cart']) && !empty($_SESSION['cart'])): ?>
        <form action="cart.php?action=update" method="post"> <!-- Formulário para atualizar a quantidade de produtos no carrinho -->
            <table>
                <thead>
                    <tr>
                        <th>Produto</th>
                        <th>Quantidade</th>
                        <th>Preço Unitário</th>
                        <th>Total</th>
                        <th>Ação</th>
                    </tr>
                </thead>
                <tbody>
                    <?php foreach ($_SESSION['cart'] as $productId => $item): ?> <!-- Exibe informações sobre cada item no carrinho -->
                        <tr>
                            <td><?php echo $item['product']['name']; ?></td>
                            <td><input type="number" name="quantity[<?php echo $productId; ?>]" value="<?php echo $item['quantity']; ?>"></td>
                            <td>$<?php echo number_format($item['product']['price'], 2); ?></td>
                            <td>$<?php echo number_format($item['quantity'] * $item['product']['price'], 2); ?></td>
                            <td><a href="cart.php?action=remove&id=<?php echo $productId; ?>">Remover</a></td> <!-- Botão para remover produto -->
                        </tr>
                    <?php endforeach; ?>
                </tbody>
            </table>
            <p class="cart-total">Total do Carrinho: $<?php echo number_format($totalPrice, 2); ?></p> <!-- Exibe o total do carrinho -->
            <button type="submit" name="update">Atualizar Carrinho</button> <!-- Botão para atualizar -->
        </form>
        <a href="cart.php?action=empty" class="empty-cart-btn">Esvaziar Carrinho</a> <!-- Botão para esvaziar -->
    <?php else: ?>
        <p>O carrinho está vazio.</p>
    <?php endif; ?>
    <br>
    <a href="index.php" class="continue-shopping-btn">Continuar Comprando</a> <!-- Botão para continuar comprando -->
</body>
</html>