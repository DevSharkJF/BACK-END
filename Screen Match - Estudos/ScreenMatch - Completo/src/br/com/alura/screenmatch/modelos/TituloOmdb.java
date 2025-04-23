package br.com.alura.screenmatch.modelos;

public record TituloOmdb(String title, String year, String runtime) {
    //O record serve para criar uma classe imutável, ou seja, não é possível alterar os valores dos atributos depois que o objeto é criado
    /*public record TituloOmdb(String Title, String Year, String Runtime)
    Poderia usar os atributos em maiusculo, e com isso nao seria necessário usar o UPPER_CAMEL_CASE */
}
