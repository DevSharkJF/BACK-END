package br.com.alura.screenmatch.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {
    public String obterDados(String endereco) {

        HttpClient client = HttpClient.newHttpClient();
        // Objeto HttpClient será responsável por enviar requisições para servidores na internet
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();
        // É criado um objeto HttpRequest, que representa a requisição que será enviada
        HttpResponse<String> response = null;
        // Criada uma variável para armazenar a resposta do servidor

        // Tratamento de Erros:
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // cliente.send = envia a requisição criada anteriormente
/*
            (request, HttpResponse.BodyHandlers.ofString()) = Define como o corpo da resposta será tratado
            nesse caso, o corpo será transformado em uma string
*/
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
/*      catch =  capta os erros de entrada e saída
        Os dois catch pegam a exceção original e a transformam em uma exceção não verificada (unchecked exception).
*/
        String json = response.body();
        return json;
    }
}
