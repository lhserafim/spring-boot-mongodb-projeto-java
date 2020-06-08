// MODELO DE CAMADAS - REPOSITORY
// Esta classe integra o SERVICE com a ENTITY

package com.alvoradatecno.sbmongodb.repository;

import com.alvoradatecno.sbmongodb.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> { // String pq o ID da classe User é string
    // Só com esta implementação básica já será possível realizar operações CRUD
    // tudo isso já está imbutido no MongoRepository

    //    Referências:
    //    https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/
    //    https://docs.spring.io/spring-data/data-document/docs/current/reference/html/
    //    Consulta: "Buscar posts contendo um dado string no título"

    // Implementar consultas simples com query methods
    // este nome findByTitleContainingIgnoreCase está em conformidade com a documentação acima
    List<Post> findByTitleContainingIgnoreCase(String text); // o Title é o campo que eu quero buscar

    //    Referências:
    //    https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/
    //    https://docs.spring.io/spring-data/data-document/docs/current/reference/html/
    //    https://docs.mongodb.com/manual/reference/operator/query/regex/
    //    Consulta: "Buscar posts contendo um dado string no título"

    @Query("{ 'title': { $regex: ?0, $options: 'i' } }") // o ?0 é referente ao primeiro paramentro. aqui no caso o String text
                                                         // o i é um parametro p/ ignorar maiusculas e minusculas
    List<Post> searchTitle(String text); // Aqui pode ser qualquer nome

    // sintaxe de query do mongoDB
    @Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
    List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
