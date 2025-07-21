package br.com.alura.screenmatch.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double avaliacao;
    private LocalDate datalancamento;
    //Propriedades da classe Episodio

    public Episodio(Integer temporada, DadosEpisodio dadosEpisodio) {
        this.temporada = temporada;
        //Define o número da temporada do episódio
        this.titulo = dadosEpisodio.titulo();
        //Obtém o título do episódio a partir do objeto DadosEpisodio
        this.numeroEpisodio = dadosEpisodio.numero();
        //Obtém o número do episódio dentro da temporad

        try {
            this.avaliacao = Double.valueOf(dadosEpisodio.avaliacao());
        } catch (NumberFormatException ex) {
            this.avaliacao = 0.0;
        }
        //Tenta converter a avaliação do episódio para um número Double(representa números de ponto flutuante de precisão dupla)
        //Se falhar, define a avaliação como 0.0

        try {
            this.datalancamento = LocalDate.parse(dadosEpisodio.datalancamento());
        } catch (DateTimeParseException ex) {
            this.datalancamento = null;
        }
        //Tenta converter a data de lançamento do episódio para um objeto LocalDate
        //Se falhar, define a data de lançamento como null
        //LocalDate =é uma classe da API de data e hora do Java (java.time) que representa uma data sem informações de horário
//        Armazena e manipular datas no formato ano-mês-dia
//        * Sem horário: Apenas a data é armazenada, sem informações de horas, minutos ou segundos.
//        * Imutável: Os objetos de LocalDate são imutáveis, ou seja, não podem ser alterados após serem criados.
//        * Fuso horário: Não considera fuso horário.
    }

    //Getter: Retorna o valor atual do atributo .
    //Setter: Permite definir ou alterar o valor do atributo .
    public Integer getTemporada() {
        return temporada;
    }
    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }
    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }
    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getDatalancamento() {
        return datalancamento;
    }
    public void setDatalancamento(LocalDate datalancamento) {
        this.datalancamento = datalancamento;
    }

    @Override
    public String toString() {
        return
                "temporada=" + temporada +
                        ", titulo='" + titulo + '\'' +
                        ", numeroEpisodio=" + numeroEpisodio +
                        ", avaliacao=" + avaliacao +
                        ", datalancamento=" + datalancamento;
    }
    //toString retorna uma representação em formato de texto dos atributos da classe Episodio
    //Concatena os valores dosatributos em uma única string
}