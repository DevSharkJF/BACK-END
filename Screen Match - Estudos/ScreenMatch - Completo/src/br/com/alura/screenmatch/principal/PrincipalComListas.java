package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.modelos.Filme;
import br.com.alura.screenmatch.modelos.Serie;
import br.com.alura.screenmatch.modelos.Titulo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class PrincipalComListas {
    public static void main(String[] args){
        //var ja referencia que o meuFilme é do tipo Filme, passando seus valores por parametro
        var meuFilme = new Filme("O poderoso chefão",1970);
        var outroFilme = new Filme("Avatar",2023);
        var filmeDoGustavo = new Filme("Dogville",2003);
        var  lost = new Serie("Lost", 2000);

        //Cria uma nova arraylist, e o metodo add adiciona o que estiver dentro do parametro na variavel da array
        //O array Titulo já contem series e filmes
        ArrayList<Titulo> lista = new ArrayList<>();
        lista.add(filmeDoGustavo);
        filmeDoGustavo.avalia(10);
        lista.add(meuFilme);
        lista.add(outroFilme);
        lista.add(lost);

        
        //Size é um metodo que retorna o tamanho da lista
        System.out.println("Tamanho da lista: " + lista.size());
        
        //Get vai retornar o filme que estiver na posição 0 e pegar o nome dele
        System.out.println("Primeiro Filme: " + lista.get(0).getNome());
        System.out.println(lista);
        System.out.println("toString do Filme: " + lista.get(0).toString());

        //Variaveis de referencia
        //Filme f1 = filmeDoGustavo;

        for (Titulo item: lista){
            System.out.println(item.getNome());
            //Filme filme = (Filme) item;
            //System.out.println("Classificação: " + filme.getClassificacao());
            if(item instanceof Filme filme){
                System.out.println("Classificação: " + filme.getClassificacao());
            }
        }

        //Ordenação de Listas
        List<String> buscaPorArtista = new LinkedList<>();
        buscaPorArtista.add("Robert JR");
        buscaPorArtista.add("Brad Pitt");
        buscaPorArtista.add("Angelina Jolie");

        //Comparable
        //Ordenação feita por ordem alfabética, com o comando sort
        //O Collections precisa ser importado 
        //O Collections.sort irá ordenar tipos obvios que podem se comparar, como somente arrays de int, string, doubles e etc
        Collections.sort(buscaPorArtista);
        System.out.println(buscaPorArtista);

        //Como a ordenação de filmes e séries não é obvia, é necessário implementar a interface comparable
        Collections.sort(lista);
        System.out.println(lista);

        //Comparator
        //Interface que irá comparar os titulos entre si, enquanto o sort deixa em ordem alfabética
        //Os dois pontos duplicados representam o operador de referencia a metodo em Java.
        //Ele é usado para referenciar métodos ou construtores de forma mais concisa, sem a necessidade de criar uma função lambda explícita.
        lista.sort(Comparator.comparing(Titulo::getAnoDeLancamento));
        //Ordenação por ano de lançamento, do mais recente para o mais antigo
        System.out.println(lista);
    }
}