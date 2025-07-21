package br.com.alura.screenmatch.principal;
import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import org.apache.logging.log4j.util.Strings;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=your_api_key";
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

//    EXIBE DADOS DA SÉRIE BUSCADA (Titulo, Temporadas e Avaliação)
    public void exibeMenu(){
        System.out.println("Informe o nome da série que deseja buscar: ");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO+nomeSerie.replace(" ", "+")+API_KEY);
        //A variável Json recebe o ENDERECO, nome da série digitada pelo usuário e a api
        //Replace substitui os espaços por "+" para formar a URL correta

        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        //dados recebe os dados obtidos do JSON e converte para um objeto da classe DadosSerie
        System.out.println(dados);
//----------------------------------------------------------------------------------------------------------------------

//      BUSCA DE TEMPORADAS E EPISÓDIOS DETALHADOS
        List<DadosTemporada> temporadas = new ArrayList<>();
        //Cria uma lista de objetos que representam as temporadas

		for(int i = 1; i<=dados.totalTemporadas();i++){
            //Loop que itera de 1 até o número total de temporadas

			json = consumo.obterDados(ENDERECO+nomeSerie.replace(" ", "+")+"&season="+i+API_KEY);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
            //Adiciona os dados da temporada a lista temporadas criada anteriormente
		}
		temporadas.forEach(System.out::println);
        //Imprime os dados de cada temporada na lista de temporadas
//----------------------------------------------------------------------------------------------------------------------

        //METODO 1, COM FOR
        for(int i = 0; i < dados.totalTemporadas(); i++){
            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
            //Cria uma lista de objetos que representa os episódios
            //temporadas.get(i).episodios() -->  retorna uma lista de episódios da temporada "i"

            for(int j = 0; j < episodiosTemporada.size(); j++){
                //O índice "j" começa em 0 e continua até que "j" seja menor que o tamanho da lista
                System.out.println(episodiosTemporada.get(j).titulo());
                //Imprime o título do episódio na posição "j" da lista episodiosTemporada
            }
        }
        //METODO 2, COM LAMBDA
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
        //temporadas.forEach --> percorre cada temporada na lista temporadas
        //t -> t.episodios() --> Esta é uma expressão lambda que define o que deve ser feito para cada t (temporada)
        // Para cada temporada t, chamamos o metodo episodios(), que retorna uma lista de episódios dessa temporada
        //e -> System.out.println(e.titulo()) --> percorre cada episódio e imprime o título do episódio
//----------------------------------------------------------------------------------------------------------------------

//      BUSCA DOS 5 EPISÓDIOS COM MAIOR AVALIAÇÃO
        List<DadosEpisodio> dadosEpisodios = temporadas.stream().flatMap(t -> t.episodios().stream()).collect(Collectors.toList());
        System.out.println("\nTop 5 Episódios com maior avaliação:");
        dadosEpisodios.stream().filter(e -> !e.avaliacao().equalsIgnoreCase("N/A")).sorted(Comparator.comparing(DadosEpisodio::avaliacao)
                .reversed()).limit(5).forEach(System.out::println);
        //dadosEpisodios.stream() --> converte a lista em um fluxo (stream) de elementos.
        //filter(e -> !e.avaliacao().equalsIgnoreCase("N/A")) --> filtrar os elementos do fluxo com base em uma condição e
        // verifica se a avaliação do episódio e não é igual a "N/A" (não disponível)
        // O metodo equalsIgnoreCase é usado para comparar strings ignorando diferenças de maiúsculas e minúsculas.
        //sorted --> ordena os elementos do fluxo com base na avaliação
        //Comparator.comparing(DadosEpisodio::avaliacao) --> cria um comparador que compara os episódios com base na avaliação
        //forEach(System.out::println) --> percorre cada elemento do fluxo e imprime os dados
//----------------------------------------------------------------------------------------------------------------------

//      DETALHAMENTO DOS EPISÓDIOS
        System.out.println("\nSérie Detalhada:");
        List<Episodio> episodios = temporadas.stream().flatMap(t -> t.episodios().stream()
                .map(d -> new Episodio(t.numero(), d))).collect(Collectors.toList());
        episodios.forEach(System.out::println);
//----------------------------------------------------------------------------------------------------------------------

//       Busca de ep por nome (Busca o primeiro episódio que contém o trecho do título)
        System.out.println("Digite um trecho do título do episódio que deseja buscar:");
        var trechoTitulo = leitura.nextLine();
        Optional<Episodio> episodioBuscado = episodios.stream().filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase())).findFirst();
        //O Optional é uma classe que pode conter um valor ou estar vazia (null), ajudando a evitar NullPointerExceptions e a lidar com a ausência de valores de forma mais segura.
        //filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase())) -->filtra os elementos com base em uma condição
        //verifica se o título contém o trecho do título fornecido pelo usuário
        //Tanto o título do episódio quanto o trecho de título são convertidos para maiúsculas usando toUpperCase()
        // para garantir que a comparação seja feita de forma case-insensitive
        //O metodo findFirst() é chamado após o filtro. Ele retorna um Optional que contém o primeiro elemento do fluxo que atende à condição do filtro

        if(episodioBuscado.isPresent()){
            //se o valor está presente dentro do Optional. Ele retorna true se o Optional contém um valor
            System.out.println("Episódio encontrado: " + episodioBuscado.get());
        } else {
            System.out.println("Nenhum episódio encontrado com o título, informe sempre em inglês: " + trechoTitulo);
        }
//----------------------------------------------------------------------------------------------------------------------

//      SISTEMA DE BUSCA PELO ANO DE LANÇAMENTO
        System.out.println("\nBusca de episodios por ano:");
        var ano = leitura.nextInt();
        leitura.nextLine();
        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
        //Cria um objeto LocalDate representando o primeiro dia do ano especificado pelo usuário

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //Formata a data

        episodios.stream().filter(e -> e.getDatalancamento() != null && e.getDatalancamento().isAfter(dataBusca))
                //Filtra os episódios com base na data de lançamento e verifica se a data não é nula
                //isAfter(dataBusca) --> verifica se a data de lançamento do episódio é posterior à data de busca
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getTemporada() +
                        ", Episódio: " + e.getNumeroEpisodio() +
                        ", Título: " + e.getTitulo() +
                        ", Avaliação: " + e.getAvaliacao() +
                        ", Data de Lançamento: " + e.getDatalancamento().format(formatador)
                ));
//----------------------------------------------------------------------------------------------------------------------

//      AVALIAÇÃO DAS TEMPORADAS
        System.out.println("\nAvaliações por Temporada:");
        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                //Filtra os episódios com base na avaliação, cujo valor é maior que 0.0

                .collect(Collectors.groupingBy(Episodio::getTemporada,Collectors.averagingDouble(Episodio::getAvaliacao)));
                //O metodo collect é utilizado para coletar os elementos do fluxo resultante em uma nova coleção
                //O metodo groupingBy é um coletor que agrupa os elementos do fluxo, este agrupa os episódios pela temporada
                //Collectors.averagingDouble(Episodio::getAvaliacao) -->Este coletor é usado como segundo argumento para o groupingBy
                //Ele calcula a média das avaliações dos episódios que foram agrupados por temporada.
        System.out.println(avaliacoesPorTemporada);
//----------------------------------------------------------------------------------------------------------------------

//      ESTATÍSTICAS DOS EPISÓDIOS
        System.out.println("\nAvaliação por Estatísticas:");
        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));
        System.out.println("Média: " + est.getAverage());
        System.out.println("Melhor Episódio: " + est.getMax());
        System.out.println("Pior Episódio: " + est.getMin());
        System.out.println("Quantidade: " + est.getCount());
    }
}

