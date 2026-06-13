package br.com.alura.screenmatch.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
// Ignora propriedades desconhecidas no JSON que não estão mapeadas no record, evitando erros

public record DadosSerie(@JsonAlias("Title") String titulo,
                            @JsonAlias("totalSeasons") Integer totalTemporadas,
                            @JsonAlias("imdbRating") String avaliacao){
// JsonAlias mapea os nomes dos campos Json para as variáveis do record, os atributos do objeto

}
