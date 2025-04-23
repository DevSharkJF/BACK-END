package br.com.alura.screenmatch.principal;
import br.com.alura.screenmatch.excecao.ErroDeConversaoDeAnoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;

//Importa a biblioteca Gson, que é usada para converter objetos Java em JSON e vice-versa.
//Usada para converter a resposta JSON da API em um objeto Java do tipo Titulo.
import com.google.gson.Gson;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

//import java.io.IOException;: Importa a classe IOException, que é uma exceção verificada em Java. Ela é lançada para indicar que ocorreu um erro de entrada/saída (I/O), como falhas ao acessar arquivos ou recursos de rede. No seu código, é usada porque o metodo HttpClient.send pode lançar essa exceção.
//import java.net.URI;: Importa a classe URI, que representa um Identificador Uniforme de Recursos (URI). No seu código, é usada para criar o URI da API que será acessada
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;

//  Documentação java para Http:
//  https://docs.oracle.com/en/java/javase/24/docs/api/java.net.http/java/net/http/package-summary.html
//  Documentação para HttpRequest:
//  https://docs.oracle.com/en/java/javase/24/docs/api/java.net.http/java/net/http/HttpRequest.html
//  Documentação para HttpResponse:
//  https://docs.oracle.com/en/java/javase/24/docs/api/java.net.http/java/net/http/HttpResponse.html

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Leitura do nome do filme
        Scanner leitura = new Scanner(System.in);
        //Cria uma string para armazenar o nome do filme
        String busca = "";
        //Cria uma lista dos titulos
        List<Titulo> titulos = new ArrayList<>();
        //Transforma os atributos para maiusculo
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

        //Ignora o tamanho da caixa da mensagem sair
        while(!busca.equalsIgnorecase("sair")){
            System.out.println("Digite o nome do filme que deseja buscar:");
            busca = leitura.nextLine();

            //Se o usuario digitar sair, o programa encerra
            if(busca.equalsIgnoreCase("sair")){
                break;
            }
            //Criação do endereço da API
            String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=chave_da_api";
            /*busca.replace(" ", "+"): Substitui os espaços no texto digitado pelo usuário (armazenado na variável busca) por +. 
            Isso é necessário porque, em URLs, espaços não são permitidos e precisam ser codificados como + ou %20.*/
            System.out.println(endereco);

            //Tratamento de erros e exceções
            try {
                //Sistema de Busca dos filmes
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                System.out.println(json);

                TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
                System.out.println(meuTituloOmdb);

                Titulo meuTitulo = new Titulo(meuTituloOmdb);
                System.out.println("Titulo já convertido");
                System.out.println(meuTitulo);

                titulos.add(meuTitulo);

                /*Após Aplicar a biblioteca, método simples:
                String json = response.body();
                Gson gson = new Gson();
                Titulo meuTitulo = gson.fromJson(Json, Titulo.class);
                System.out.println(meuTitulo);
                */

            } catch (NumberFormatException e) {
                System.out.println("Aconteceu um erro: ");
                System.out.println(e.getMessage());
            }catch (IllegalArgumentException e) {
                System.out.println("Algum erro de argumento na busca, verifique o endereço");
            } catch (ErroDeConversaoDeAnoException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(titulos);

        //Cria um arquivo JSON com os dados de todos os filmes buscados
        //Esse arquivo pode ser gerado em diferentes formatos, como txt.
        FileWriter escrita = new FileWriter("filmes.json");
        escrita.write(gson.toJson(titulos));
        escrita.close();
        System.out.println("O programa finalizou corretamente!");

        /*
        Exemplo do arquivo JSON Gerado:
        [
            {
                "Title": "Matrix",
                "Year": 1993,
                "IncluidoNoPlano": false,
                "SomaDasAvaliacoes": 0.0,
                "TotalDeAvaliacoes": 0,
                "DuracaoEmMinutos": 60
            }
        ]
        */
    }
}

/*
Método para buscar filmes direto por um link especifico, sem usar o Scanner
public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.omdbapi.com/?t=matrix&apikey=d07a5ac2")).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
*/