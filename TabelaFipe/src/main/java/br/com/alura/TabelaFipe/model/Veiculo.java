package br.com.alura.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(
        //jsonAlias mapeia os nomes dos campos JSON para os atributos do objeto
        @JsonAlias("Valor") String valor,
        @JsonAlias("Marca") String marca,
        @JsonAlias("Modelo") String modelo,
        @JsonAlias("AnoModelo") Integer ano,
        @JsonAlias("Combustivel") String tipoCombustivel
) {

    @Override
    //Formata a saída do Objeto veiculo para uma string
    public String toString() {
        return String.format("%s %s  ano: %s valor: %s combustível: %s",
                marca, modelo, ano, valor, tipoCombustivel);
    }
}
