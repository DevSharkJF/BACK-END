package br.com.alura.screenmatch.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
//Ignora propriedades desconhecidas no JSON que não estão mapeadas no record

public record DadosTemporada(@JsonAlias("Season") Integer numero,
                             @JsonAlias("Episodes")List<DadosEpisodio> episodios){
//Cria uma lista de objetos do tipo DadosEpisodio, que representa os episódios da temporada
}
