package br.com.alura.screensound.principal;

import br.com.alura.screensound.model.Artista;
import br.com.alura.screensound.model.Musica;
import br.com.alura.screensound.model.TipoArtista;
import br.com.alura.screensound.repository.ArtistaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final ArtistaRepository repositorio;
    //private: Este modificador de acesso indica que o campo repositorio é acessível apenas dentro da classe em que está definido.
    //final: Este modificador indica que o campo repositorio não pode ser reatribuído após a inicialização.
    private Scanner leitura = new Scanner(System.in);
    public Principal(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao!= 9) {
            var menu = """
                    *** Screen Sound Músicas ***                    
                    
                    1- Cadastrar artistas
                    2- Cadastrar músicas
                    3- Listar músicas
                    4- Buscar músicas por artistas

                    9 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 9:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    private void buscarMusicasPorArtista() {
        System.out.println("Buscar músicas de qual artista? ");
        var nome = leitura.nextLine();
        List<Musica> musicas = repositorio.buscaMusicasPorArtista(nome);
        musicas.forEach(System.out::println);
    }

    private void listarMusicas() {
        List<Artista> artista = repositorio.findAll();
        artista.forEach(a -> a.getMusicas().forEach(System.out::println));
        //Para cada artista, será listado suas músicas
    }

    private void cadastrarMusicas() {
        System.out.println("Cadastrar Músicas de qual Artista? ");
        var nome = leitura.nextLine();
        Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nome);
        //o repositorio busca um artista cujo nome contém a string fornecida pelo usuário, ignorando o Case
        //Optional<Artista> é uma maneira segura de lidar com a possibilidade de que um artista correspondente não seja encontrado.
        //fornece métodos para verificar a presença de um valor antes de acessá-lo.

        if(artista.isPresent()){
            //O metodo isPresent() retorna um valor booleano (true ou false), indicando se um valor está presente dentro do Optional
            System.out.println("Informe o título da música: ");
            var nomeMusica = leitura.nextLine();
            Musica musica = new Musica(nomeMusica);
            musica.setArtista(artista.get());
            //seta o valor do artista pegando pelo get
            artista.get().getMusicas().add(musica);
            //Pega o valor do artista, depois a lista de musicas associadas a ele e adiciona na coleção
            repositorio.save(artista.get());
        }else{
            System.out.println("Artista não encontrado!");
        }
    }

    private void cadastrarArtistas() {
        var cadastrarNovo = "S";
        while(cadastrarNovo.equalsIgnoreCase("s")){
            //enquanto a variável cadastrarNovo for igual a "s"(independente do Case) p loop continua
            System.out.println("Informe o Nome do Artista: ");
            var nome = leitura.nextLine();

            System.out.println("Infome o tipo de artista (Solo, Dupla, Banda)");
            var tipo = leitura.nextLine();
            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artista artista = new Artista(nome, tipoArtista);
            repositorio.save(artista);
            //convertendo uma string da variavel tipo em uma constante do enum TipoArtista.
            //valueof tenta encontrar uma constante do enum que corresponda ao nome fornecido.
            //new Artista cria uma nova instância da classe Artista

            System.out.println("Cadastrar um novo artista? (S/N)");
            cadastrarNovo = leitura.nextLine();
        }
    }
}
