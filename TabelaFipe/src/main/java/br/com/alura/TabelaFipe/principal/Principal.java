package br.com.alura.TabelaFipe.principal;

import br.com.alura.TabelaFipe.model.Dados;
import br.com.alura.TabelaFipe.model.Modelos;
import br.com.alura.TabelaFipe.model.Veiculo;
import br.com.alura.TabelaFipe.service.ConsumoApi;
import br.com.alura.TabelaFipe.service.ConverteDados;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v2/";

    //Menu de Opções
    public void exibeMenu() {
        var menu = """
               *** OPÇÕES ***
               Carro
               Moto
               Caminhão
               
               Digite uma das opções para consulta: 
               """;

        //Leitura da opção do usuário
        System.out.println(menu);
        var opcao = leitura.nextLine();
        String endereco;

        //Verifica a opção escolhida pelo usuário e define o valor para a variável endereço
        //Contains é usado para verificar se a string contém palavras relacionadas aos tipos de veículos
        if (opcao.toLowerCase().contains("carr")) {
            endereco = URL_BASE + "carros/marcas";
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motos/marcas";
        } else {
            endereco = URL_BASE + "caminhoes/marcas";
        }

        //Json recebe os dados obtidos na variável endereco
        var json = consumo.obterDados(endereco);
        //Exibe o Json
        System.out.println(json);
        //Converte o Json em uma lista de objetos dados
        var marcas = conversor.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta: ");
        var codigoMarca = leitura.nextLine();

        //Atualiza a variável endereco com o endereco da marca e o código da marca na parte de modelos
        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = consumo.obterDados(endereco);
        //Converte o Json em um objeto do tipo Modelos
        var modeloLista = conversor.obterDados(json, Modelos.class);

        System.out.println("\nModelos dessa marca: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("\nDigite um trecho do nome do carro a ser buscado");
        var nomeVeiculo = leitura.nextLine();

        //Filtra os modelos com base no nome do veículo digitado pelo usuário
        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos filtrados");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite por favor o código do modelo para buscar os valores de avaliação: ");
        var codigoModelo = leitura.nextLine();

        //Atualiza a variável endereco com o endereço do modelo e o código do modelo na parte de anos
        endereco = endereco + "/" + codigoModelo + "/anos";
        //Obtem os dados da API e armazena em Json
        json = consumo.obterDados(endereco);
        //Converte o Json em uma lista de objetos do tipo Dados
        List<Dados> anos = conversor.obterLista(json, Dados.class);
        //Cria uma lista vazia para armazenar os veículos
        List<Veiculo> veiculos = new ArrayList<>();

        //Percorre a lista de anos e para cada ano, obtém os dados do veículo
        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
            //Obtem os dados e armazena em Json
            json = consumo.obterDados(enderecoAnos);
            //Converte o Json em um objeto
            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("\nTodos os veículos filtrados com avaliações por ano: ");
        veiculos.forEach(System.out::println);

    }
}