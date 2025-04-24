import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaCep{
    public Endereco buscaEndereco(String cep) {
//        Cria um objeto URI com a URL da API, subistituindo o cep na URL pela variável criada:
        URI endereco = URI.create("https://viacep.com.br/ws/" + cep + "/json/");
        HttpRequest request = HttpRequest.newBuilder().uri(endereco).build();
//        Tratamento de erros e exeções:
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//            Converte o corpo da resposta (JSON) em um objeto da classe Endereco utilizando a biblioteca Gson:
            return new Gson().fromJson(response.body(), Endereco.class);
        } catch (Exception e) {
            throw new RuntimeException("Não consegui obter o endereço a partir desse CEP.");
        }
    }
}
