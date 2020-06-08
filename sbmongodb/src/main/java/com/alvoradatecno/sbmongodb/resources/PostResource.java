// MODELO DE CAMADAS - RESOURCE (CONTROLLER)
// Esta classe integra a APPLICATION (tela) com o SERVICE
// Deve ser uma classe enxuta, apenas com as chamadas dos services

package com.alvoradatecno.sbmongodb.resources;

import com.alvoradatecno.sbmongodb.domain.Post;
import com.alvoradatecno.sbmongodb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// Controladores REST conversam diretamente com a aplicação do cliente
@RestController // Anotação p/ identificar que é um recurso REST
@RequestMapping(value = "/posts")// Caminho do end-point
public class PostResource {

    @Autowired
    private PostService service;
    // resource -> service -> repository

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id) { // @PathVariable Indica que o id virá de parametro de url
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj); // converter o obj p/ UserDto
    }
}