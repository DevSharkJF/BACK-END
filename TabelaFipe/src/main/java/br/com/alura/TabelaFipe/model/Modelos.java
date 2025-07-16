package br.com.alura.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

// anotação @JsonIgnoreProperties(ignoreUnknown = true) indica que, ao desserializar JSON, propriedades desconhecidas no JSON serão ignoradas, evitando erros.
@JsonIgnoreProperties(ignoreUnknown = true)
public record Modelos(List<Dados> modelos) {
}
