package br.com.alura.TabelaFipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteDados implements IConverteDados {
    //mapper é uma instância da classe ObjectMapper, responsável por converter entre JSON e Java
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    //json = contém os dados no formato JSON
    //classe = classe do tipo de objeto que o JSON será convertido
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            //O metodo readValue do ObjectMapper é usado para desserializar o JSON
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> obterLista(String json, Class<T> classe) {
        //CollectionType da biblioteca Jackson para definir o tipo de coleção que será criada.
        CollectionType lista = mapper.getTypeFactory()
                .constructCollectionType(List.class, classe);
        try {
            return mapper.readValue(json, lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
