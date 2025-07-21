package br.com.alura.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe){
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    //public class ConverteDados implements IConverteDados:
    //Aqui, estamos definindo a classe ConverteDados, que implementa a interface IConverteDados. Isso significa que a classe deve fornecer implementações
    // para os métodos definidos na interface.

    //private ObjectMapper mapper = new ObjectMapper();:
    //ObjectMapper é uma classe da biblioteca Jackson que é usada para ler e escrever dados em formato JSON.
    // Neste caso, estamos criando uma instância de ObjectMapper chamada mapper, que será usada para realizar a conversão entre JSON e objetos Java.

    //@Override:
    //Esta anotação indica que o metodo obterDados está sobrescrevendo um metodo da interface IConverteDados.
    // Isso é útil para garantir que você está realmente implementando um metodo da interface e ajuda a evitar erros.

    //public <T> T obterDados(String json, Class<T> classe):
    //Este é um metodo genérico que aceita uma string json e uma classe classe como parâmetros. O tipo de retorno do metodo é T, que é um tipo genérico.
    // Isso significa que o metodo pode retornar um objeto de qualquer tipo, dependendo do que for passado como argumento para classe.
    //O metodo é projetado para converter a string JSON fornecida em um objeto do tipo especificado.

    //try { ... } catch (JsonProcessingException e) { ... }:
    //Dentro do bloco try, estamos chamando o metodo readValue do ObjectMapper, que tenta converter a string JSON em um objeto da classe especificada.
    //Se ocorrer um erro durante a conversão (por exemplo, se o JSON não estiver no formato esperado), uma exceção do tipo JsonProcessingException será lançada.
    //No bloco catch, estamos capturando essa exceção e lançando uma nova RuntimeException, passando a exceção original como causa.
    // Isso permite que o erro seja tratado em um nível superior, se necessário.
}
