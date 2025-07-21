package br.com.alura.screenmatch.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
//Ignora propriedades desconhecidas no JSON que não estão mapeadas no record

public record DadosEpisodio(@JsonAlias("Title") String titulo,
                            @JsonAlias("Episode")Integer numero,
                            @JsonAlias("imdbRating")String avaliacao,
                            @JsonAlias("Released")String datalancamento) {
//JsonAlias mapea os nomes dos campos Json para as variáveis do record
}
