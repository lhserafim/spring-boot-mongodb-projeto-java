// MODELO DE CAMADAS - REPOSITORY
// Esta classe integra o SERVICE com a ENTITY

package com.alvoradatecno.sbmongodb.repository;

import com.alvoradatecno.sbmongodb.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

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
    List<Post> findByTitleContainingIgnoreCase(String text); // o Title é o campo que eu quero buscar
}
