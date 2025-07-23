package br.com.alura.screensound.model;

import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne
    private Artista artista;
    //Um relacionamento @ManyToOne indica que várias instâncias de uma entidade (a entidade "filha") podem estar associadas
    //a uma única instância de outra entidade (a entidade "pai").

    public Musica(){}
    public Musica(String nomeMusica){
        this.titulo = nomeMusica;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Artista getArtista() {
        return artista;
    }
    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                "artista=" + artista.getNome();
    }
    //@Override é uma anotação em Java que indica que um metodo está sendo sobrescrito (ou "overridden") de uma classe pai ou de uma interface.
}
