// MODELO DE CAMADAS - RESOURCE (CONTROLLER)
// Esta classe integra a APPLICATION (tela) com o SERVICE
// Deve ser uma classe enxuta, apenas com as chamadas dos services

package com.alvoradatecno.sbmongodb.resources;

import com.alvoradatecno.sbmongodb.domain.Post;
import com.alvoradatecno.sbmongodb.resources.util.URL;
import com.alvoradatecno.sbmongodb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

    @RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text",defaultValue = "") String text) {
        // Primeiro decodificar o parametro
        text = URL.decodeParam(text);
        List<Post> list = service.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value="/fullsearch", method=RequestMethod.GET)
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value="text", defaultValue="") String text,
            @RequestParam(value="minDate", defaultValue="") String minDate,
            @RequestParam(value="maxDate", defaultValue="") String maxDate) {
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L)); // gera a data minima do java
        Date max = URL.convertDate(maxDate, new Date()); // instante atual do sistema
        List<Post> list = service.fullSearch(text, min, max);
        return ResponseEntity.ok().body(list);
    }
}