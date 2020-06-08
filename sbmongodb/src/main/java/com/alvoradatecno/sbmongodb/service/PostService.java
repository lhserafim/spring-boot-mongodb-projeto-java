// MODELO DE CAMADAS - SERVICE
// Esta classe integra o RESOURCE (CONTROLLER) com o REPOSITORY
// É nesta camada que estão implementadas as regras de negócio

package com.alvoradatecno.sbmongodb.service;

import com.alvoradatecno.sbmongodb.domain.Post;
import com.alvoradatecno.sbmongodb.repository.PostRepository;
import com.alvoradatecno.sbmongodb.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    // Serviço falar com repositório
    // Quando vc declara um obj dentro de uma classe usando o @Autowired o próprio Spring vai procurar a def. do obj
    // e vai instanciar o obj pra mim. Mecanismo de injeção automática de dependencia do Spring
    @Autowired
    private PostRepository repo;

    public Post findById(String id) {
        Optional<Post> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text) {
        //return repo.findByTitleContainingIgnoreCase(text);
        return repo.searchTitle(text);
    }
}