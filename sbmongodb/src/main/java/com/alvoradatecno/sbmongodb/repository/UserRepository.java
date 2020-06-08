// MODELO DE CAMADAS - REPOSITORY
// Esta classe integra o SERVICE com a ENTITY

package com.alvoradatecno.sbmongodb.repository;

import com.alvoradatecno.sbmongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> { // String pq o ID da classe User é string
    // Só com esta implementação básica já será possível realizar operações CRUD
    // tudo isso já está imbutido no MongoRepository
}
