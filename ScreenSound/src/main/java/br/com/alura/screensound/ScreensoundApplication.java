package br.com.alura.screensound;
import br.com.alura.screensound.principal.Principal;
import br.com.alura.screensound.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreensoundApplication implements CommandLineRunner {
	@Autowired
	private ArtistaRepository repositorio;
	//Autowired é utilizada para realizar a injeção de dependência.
	//A injeção de dependência é um padrão de design que permite que um objeto (ou classe) receba suas dependências de uma fonte externa,
	//em vez de criá-las internamente.

	public static void main(String[] args) {
		SpringApplication.run(ScreensoundApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception{
		Principal principal = new Principal(repositorio);
		principal.exibeMenu();
	}
}
