import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        ConsultaCep consultaCep = new ConsultaCep();

        System.out.println("Digite o Cep para consulta:");
        var cep = leitura.nextLine();
//        Tratamento de erros e exeções:
        try {
//            Busca o endereço pelo cep informado:
            Endereco novoEndereco = consultaCep.buscaEndereco(cep);
            System.out.println(novoEndereco);
//            Cria um novo objeto da classe GeradorDeArquivo, com metodo para salvar um endereço no formato JSON:
            GeradorDeArquivo gerador = new GeradorDeArquivo();
//            Chama o metodo salvaJson, passando o endereço por parâmetro:
            gerador.salvaJson(novoEndereco);
        } catch (RuntimeException | IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Finalizando a aplicação");
        }
    }
}