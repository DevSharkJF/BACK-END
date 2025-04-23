package br.com.alura.screenmatch.modelos;
import br.com.alura.screenmatch.calculos.Classificavel;

//A classe Filme se extende a classe Titulo e implementa a interface Classificavel
public class Filme extends Titulo implements Classificavel {
    private String diretor;

    public String getDiretor() {
        return diretor;
    }
    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    //Sobscreve o metodo getClassificacao da interface Classificavel
    @Override
    public int getClassificacao() {
        return (int) pegaMedia() / 2;
    }
}
