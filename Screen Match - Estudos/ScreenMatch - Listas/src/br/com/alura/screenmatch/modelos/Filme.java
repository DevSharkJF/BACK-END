package br.com.alura.screenmatch.modelos;

import br.com.alura.screenmatch.calculos.Classificavel;

public class Filme extends Titulo implements Classificavel {
    private String diretor;
    public String getDiretor() {
        return diretor;
    }
    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    @Override
    public int getClassificacao() {
        return (int) pegaMedia() / 2;
    }

    @Override
    //toString retorna uma representação de um objeto como uma string
    public String toString(){
        return "Filme: " + this.getNome() + "(" + this.getAnoDeLancamento() + ")";
    }

    //Construtor
    public Filme(String nome, int anoDeLancamento) {
        //this.setNome(nome);
        super(nome,anoDeLancamento);
    }
}
