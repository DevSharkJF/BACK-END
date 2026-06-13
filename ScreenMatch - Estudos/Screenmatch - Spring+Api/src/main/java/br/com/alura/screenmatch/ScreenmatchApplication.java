package br.com.alura.screenmatch;
import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.principal.Principal;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.alura.screenmatch.service.ConsumoApi;
import java.util.ArrayList;
import java.util.List;

// @  = é uma anotação. Fornece informações extras para o compilador ou para algum framework.
/* 
	@SpringBootApplication é equivalente a 3 anotações:
	@Configuration = Indica que a classe possui configurações do Spring.
	@EnableAutoConfiguration = Configura automaticamente o que for necessário
	@ComponentScan = Procura classes gerenciadas pelo spring 
*/

@SpringBootApplication
/* 
	informa ao Spring Boot que esta é a classe principal da aplicação.
	O spring procura essa anotação para saber:
	* Onde a aplicação começa
	* Quais classes ela deve procurar
	* Quais configurações deve carregar automaticamente 
*/
public class ScreenmatchApplication implements CommandLineRunner {
	// Cria uma classe normal e implementa uma interface
	// Interface = contrato que define um conjunto de métodos e constantes,As classes que assinam esse contrato são obrigadas a implementar os comportamentos declarados
	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
		// Inicia o spring boot, cria contexto de aplicação, carrega componentes, configura dependencias e executa o commandlinerunner
		// Método principal do java, é o primeiro a ser chamado
		// ScreenmatchApplication.class = referência a propria classe (NÃO CRIA UMA CLASSE, não confundir com NEW)
		// Ao receber ScreenmatchApplication.class ele procura @SpringBootApplication
	}
	@Override
	public void run(String... args) throws Exception{
		Principal principal = new Principal();
		principal.exibeMenu();
	}
}
