import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class GeradorDeArquivo {
    public void salvaJson(Endereco endereco) throws IOException {
//        Cria uma instancia da biblioteca Gson configurada para gerar um JSON formatado:
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        Cria um arquivo com o nome do cep, e o formato JSON:
        FileWriter escrita = new FileWriter(endereco.cep() + ".json");
//        Converte o objeto Endereco para o formato JSON e escreve o conteúdo no arquivo
        escrita.write(gson.toJson(endereco));
        escrita.close();
    }
}