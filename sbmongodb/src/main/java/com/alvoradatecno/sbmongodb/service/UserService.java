// MODELO DE CAMADAS - SERVICE
// Esta classe integra o RESOURCE (CONTROLLER) com o REPOSITORY
// É nesta camada que estão implementadas as regras de negócio

package com.alvoradatecno.sbmongodb.service;

import com.alvoradatecno.sbmongodb.domain.User;
import com.alvoradatecno.sbmongodb.repository.UserRepository;
import com.alvoradatecno.sbmongodb.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    // Serviço falar com repositório
    // Quando vc declara um obj dentro de uma classe usando o @Autowired o próprio Spring vai procurar a def. do obj
    // e vai instanciar o obj pra mim. Mecanismo de injeção automática de dependencia do Spring
    @Autowired
    private UserRepository repo;

    public List<User> findAll() {
        return repo.findAll();
    }

    // OLD
    /*
        public User findById(String id) {
            User user = repo.findOne(id);
            if (user == null) {
                throw new ObjectNotFoundException("Objeto não encontrado");
            }
            return user;
        }
    */

    public User findById(String id) {
        Optional<User> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
}
