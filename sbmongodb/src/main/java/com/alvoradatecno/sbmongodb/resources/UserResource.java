package com.alvoradatecno.sbmongodb.resources;

import com.alvoradatecno.sbmongodb.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Controladores REST conversam diretamente com a aplicação do cliente
@RestController // Anotação p/ identificar que é um recurso REST
@RequestMapping(value = "/users")// Caminho do end-point
public class UserResource {

    // Para dizer que este método é um endpoint deste caminho (/users) eu preciso colocar a anotação
    @RequestMapping(method = RequestMethod.GET) // tmb poderia usar o @GetMapping()
    public ResponseEntity<List<User>> findAll() { // ResponseEntity é um objeto do Spring que encapsula estrutura p/ retornar em HTTP
        User maria = new User("1", "Maria Brown", "maria@gmail.com");
        User alex = new User("2", "Alex Green", "alex@gmail.com");
        // List é apenas uma interface. No java interfaces NÃO PODEM SER INSTANCIADAS.
        // Para "instanciar" precisamos colocar uma implementação da interface, como por ex. ArrayList<>()
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(maria, alex));
        return ResponseEntity.ok().body(list);
    }
}
