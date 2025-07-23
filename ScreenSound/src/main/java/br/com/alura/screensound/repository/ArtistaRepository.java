package br.com.alura.screensound.repository;

import br.com.alura.screensound.model.Artista;
import br.com.alura.screensound.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT m FROM Artista a JOIN a.musicas m WHERE a.nome ILIKE %:nome%")
    List<Musica> buscaMusicasPorArtista(String nome);}
//SELECT m: Esta parte da consulta indica que você está selecionando instâncias da entidade Musica (presumivelmente, m é um alias para a entidade Musica).
//FROM Artista a: Aqui, você está especificando que a consulta está sendo feita a partir da entidade Artista, e a é um alias para essa entidade.
//JOIN a.musicas: Este trecho indica que você está realizando uma junção entre a entidade Artista e a coleção de músicas associadas a ela.
//Isso pressupõe que a entidade Artista possui um relacionamento @OneToMany com a entidade Musica.
//WHERE a.nome ILIKE %:nome%: Esta cláusula filtra os resultados com base no nome do artista. O operador ILIKE é usado para realizar uma comparação de string
//que ignora a diferença entre maiúsculas e minúsculas. O :nome é um parâmetro nomeado que será substituído pelo valor fornecido quando a consulta for executada.
