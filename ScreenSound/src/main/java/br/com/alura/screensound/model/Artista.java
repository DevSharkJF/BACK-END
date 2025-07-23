package br.com.alura.screensound.model;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;

@Entity
//Entity fará com que a classe será uma tabela no banco de dados
@Table(name = "artistas")
//Table irá determinar o nome da tabela no banco

public class Artista {
    //id, nome, tipo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Id é uma anotação que indica que o campo id é a chave primária da entidade
    //Esta anotação é usada para especificar como o valor da chave primária será gerado.
    //O parâmetro strategy define a estratégia de geração de valores. No caso de GenerationType.IDENTITY, isso significa que o valor do id
    //será gerado pelo banco de dados, geralmente usando uma coluna de auto-incremento.
    //Long é uma classe wrapper em Java que representa um valor numérico inteiro de 64 bits

    @Column(unique = true)
    private String nome;
    //@Column: Esta é uma anotação que indica que o campo em questão deve ser mapeado para uma coluna em uma tabela do banco de dados.
    //unique = true: Este parâmetro especifica que os valores na coluna correspondente devem ser únicos.
    //Isso significa que não pode haver duas linhas na tabela que tenham o mesmo valor para essa coluna.

    @Enumerated(EnumType.STRING)
    private TipoArtista tipo;
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas = new ArrayList<>();
    //@Enumerated: Esta é uma anotação que indica que o campo em questão é um tipo enumerado (enum) e que deve ser
    //tratado de uma maneira específica ao ser persistido no banco de dados.
    //@OneToMany indica que uma instância de uma entidade (a entidade "pai") pode estar associada a várias instâncias de outra entidade (a entidade "filha").
    //EnumType.STRING: Este parâmetro especifica que os valores do enum devem ser armazenados como strings(textos) na coluna correspondente no banco de dados.
    //mappedBy: Este parâmetro é usado para especificar o nome do campo na entidade "filha" que possui a referência de volta para a entidade "pai".
    //cascade: Este parâmetro define o comportamento de cascata para operações de persistência. Por exemplo, se você usar CascadeType.ALL,
    //todas as operações (persistir, atualizar, remover, etc.) realizadas na entidade "pai" também serão aplicadas às entidades "filhas".
    //FetchType.EAGER significa que as músicas associadas ao artista serão carregadas imediatamente quando o artista for carregado do banco de dados.

    public Artista(){}
    //Construtor padrão que não aceita parâmetros. Ele é chamado quando uma nova instância da classe é criada sem fornecer argumentos.

    public Artista(String nome, TipoArtista tipo) {
        this.nome = nome;
        this.tipo = tipo;
        //Construtor da classe Artista com os parametros:
        //nome - String & TipoArtista - enum
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoArtista getTipo() {
        return tipo;
    }
    public void setTipo(TipoArtista tipo) {
        this.tipo = tipo;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }
    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    @Override
    public String toString() {
        return
                ", Artista='" + nome + '\'' +
                ", tipo=" + tipo +
                ", musicas=" + musicas;
    }
}
