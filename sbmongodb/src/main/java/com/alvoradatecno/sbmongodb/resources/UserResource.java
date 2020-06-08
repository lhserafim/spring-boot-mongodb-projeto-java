// MODELO DE CAMADAS - RESOURCE (CONTROLLER)
// Esta classe integra a APPLICATION (tela) com o SERVICE
// Deve ser uma classe enxuta, apenas com as chamadas dos services

package com.alvoradatecno.sbmongodb.resources;

import com.alvoradatecno.sbmongodb.domain.User;
import com.alvoradatecno.sbmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Controladores REST conversam diretamente com a aplicação do cliente
@RestController // Anotação p/ identificar que é um recurso REST
@RequestMapping(value = "/users")// Caminho do end-point
public class UserResource {

    @Autowired
    private UserService service;
    // resource -> service -> repository

    // Para dizer que este método é um endpoint deste caminho (/users) eu preciso colocar a anotação
    @RequestMapping(method = RequestMethod.GET) // tmb poderia usar o @GetMapping()
    public ResponseEntity<List<User>> findAll() { // ResponseEntity é um objeto do Spring que encapsula estrutura p/ retornar em HTTP
        // User maria = new User("1", "Maria Brown", "maria@gmail.com");
        // User alex = new User("2", "Alex Green", "alex@gmail.com");
        // List é apenas uma interface. No java interfaces NÃO PODEM SER INSTANCIADAS.
        // Para "instanciar" precisamos colocar uma implementação da interface, como por ex. ArrayList<>()
        // List<User> list = new ArrayList<>();
        // list.addAll(Arrays.asList(maria, alex));

        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}