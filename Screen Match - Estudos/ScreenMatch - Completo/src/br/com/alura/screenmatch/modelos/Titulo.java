package br.com.alura.screenmatch.modelos;
//Por causa da ordenação de listas, a classe Titulo implementa a interface comparable, que irá comparar titulo com titulo
import br.com.alura.screenmatch.excecao.ErroDeConversaoDeAnoException;
import com.google.gson.annotations.SerializedName;
//Importa a anotação SerializedName, que é usada para mapear nomes de campos JSON para nomes de variáveis Java. No seu código, é usada para mapear o campo "Runtime" do JSON para a variável duracaoEmMinutos na classe Titulo.

public class Titulo implements Comparable<Titulo>{
    //SerializedName vai mapear o nome do atributo no JSON com o nome do atributo na classe
    @SerializedName("Title")
    private String nome;
    @SerializedName("Year")
    private int anoDeLancamento;
    private boolean incluidoNoPlano;
    private double somaDasAvaliacoes;
    private int totalDeAvaliacoes;
    private int duracaoEmMinutos;

    //Construtor da Classe Titulo, passando por parametro o nome e o ano de lançamento
    public Titulo(String nome, int anoDeLancamento) {
        this.nome = nome;
        this.anoDeLancamento = anoDeLancamento;
    }
    public Titulo(TituloOmdb meuTituloOmdb) {
        this.nome = meuTituloOmdb.title();
        if(meuTituloOmdb.year().length() > 4) {
            throw new ErroDeConversaoDeAnoException("Não consegui converter o ano " + "porque tem mais de 04 caracteres.");
    }
    this.anoDeLancamento = Integer.valueOf(meuTituloOmdb.year());
    this.duracaoEmMinutos = Integer.valueOf(meuTituloOmdb.runtime().substring(0, 2));
}
    public String getNome() {
        return nome;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public boolean isIncluidoNoPlano() {
        return incluidoNoPlano;
    }

    public int getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public int getTotalDeAvaliacoes() {
        return totalDeAvaliacoes;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAnoDeLancamento(int anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public void setIncluidoNoPlano(boolean incluidoNoPlano) {
        this.incluidoNoPlano = incluidoNoPlano;
    }

    public void setDuracaoEmMinutos(int duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    public void exibeFichaTecnica(){
        System.out.println("Nome do filme: " + nome);
        System.out.println("Ano de lançamento: " + anoDeLancamento);
    }

    public void avalia(double nota){
        somaDasAvaliacoes += nota;
        totalDeAvaliacoes++;
    }

    public double pegaMedia(){
        return somaDasAvaliacoes / totalDeAvaliacoes;
    }

    //É necessário implementar o metodo compareTo, que irá comparar os titulos entre si
    @Override
    //Comparacao feita por nome entre os titulos
    public int compareTo(Titulo outroTitulo){
    //Irá retornar o nome, comparando com outro nome de outro titulo, e o sort irá deixar em ordem alfabética
        return this.getNome().compareTo(outroTitulo.getNome());
    }


    //To string para imprimir os dados do filme
    @Override
    public String toString() {
        return "(nome = " + nome + ", anoDeLancamento = " + anoDeLancamento + "," + " duração = " + duracaoEmMinutos + ")";
    }
}


