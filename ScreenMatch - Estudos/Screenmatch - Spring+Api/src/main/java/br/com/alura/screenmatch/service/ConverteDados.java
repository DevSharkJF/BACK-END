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
/* public class ConverteDados implements IConverteDados:
    Ao definir a classe ConverteDDados, implementa a interface IConverteDados. Isso significa que a classe deve fornecer implementações
    para os métodos definidos na interface
*/

/* private ObjectMapper mapper = new ObjectMapper();:
    ObjectMapper é uma classe da biblioteca Jackson quew é usada para ler e escrever dados em formato JSON
    Nesse bloco de código, cria uma instância de ObjectMapper chamada mapper, usada para realizar a conversão entre JSON e objetos Java
*/

/* @Override:
    Essa anotação indica que o método obterDados está sobrescrevendo um método da interface IConverteDados
    Útil para garantir que realmente está implementndo um método da interface, evitando erros
*/

/* public <T> T obterDados(String json, Class<T> classe):
    Método genérico que aceita uma string JSON e uma classe como parâmetros. O tipo de retorno do método é T, um tipo genérico
    Pode retornar um objeto de qualquer tipo, dependendo do que for passado como argumento para classe
    É projetado para converter a string JSON fornecida em um objeto do tipo especificado
*/

/* try { ... } catch (JsonProcessingException e) { ... }:
    Dentro do bloco try, chama o método readValue do ObjectMapper, que tenta converter a string JSON em um objeto da classe especificada
    Caso ocorra um erro durante a conversão (ex: se o JSON não estiver no formato esperado), uma exceção do tipo JsonProcessingException será lançada
    No bloco catch, captura exceção e lança uma nova RunTimeException, passando a exceção original como causa
    Isso permite que o erro seja tratado em um nível superior, se necessário
*/
}
