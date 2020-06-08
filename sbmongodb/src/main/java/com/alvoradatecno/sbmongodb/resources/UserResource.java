// MODELO DE CAMADAS - RESOURCE (CONTROLLER)
// Esta classe integra a APPLICATION (tela) com o SERVICE
// Deve ser uma classe enxuta, apenas com as chamadas dos services

package com.alvoradatecno.sbmongodb.resources;

import com.alvoradatecno.sbmongodb.domain.User;
import com.alvoradatecno.sbmongodb.dto.UserDTO;
import com.alvoradatecno.sbmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

// Controladores REST conversam diretamente com a aplicação do cliente
@RestController // Anotação p/ identificar que é um recurso REST
@RequestMapping(value = "/users")// Caminho do end-point
public class UserResource {

    @Autowired
    private UserService service;
    // resource -> service -> repository

    // Para dizer que este método é um endpoint deste caminho (/users) eu preciso colocar a anotação
    @RequestMapping(method = RequestMethod.GET) // tmb poderia usar o @GetMapping()
    public ResponseEntity<List<UserDTO>> findAll() { // ResponseEntity é um objeto do Spring que encapsula estrutura p/ retornar em HTTP
        // User maria = new User("1", "Maria Brown", "maria@gmail.com");
        // User alex = new User("2", "Alex Green", "alex@gmail.com");
        // List é apenas uma interface. No java interfaces NÃO PODEM SER INSTANCIADAS.
        // Para "instanciar" precisamos colocar uma implementação da interface, como por ex. ArrayList<>()
        // List<User> list = new ArrayList<>();
        // list.addAll(Arrays.asList(maria, alex));

        List<User> list = service.findAll();
        // Como minha lista agora é de DTO preciso fazer a conversão dos elementos da lista original
        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        // .stream() - Converter para stream p/ ser compátivel com lambda
        // .map() para pegar cada objeto x (pode ser o nome que eu quiser. X é User pq vem da list
        // passo o X como argumento
        // transformar em lista novamente .collect(Collectors.toList()
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET) //@GetMapping(value = "/{id}") // Indicar que a requisição aceita um parâmetro
    public ResponseEntity<UserDTO> findById(@PathVariable String id) { // @PathVariable Indica que o id virá de parametro de url
        User obj = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj)); // converter o obj p/ UserDto
    }

    @RequestMapping(method = RequestMethod.POST) // tmb poderia usar o @PostMapping()
    public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
        // Converter o DTO p/ User
        User obj = service.fromDto(objDto);
        obj = service.insert(obj);
        // Melhoria, retornar o código 201
        // Como eu vou receber uma resposta vazia, como boa-prática, vou colococar um cabeçalho com a URL do novo recurso criado.
        // comando abaixo recupera o end. do novo obj que foi inserido
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) //@DeleteMapping(value = "/{id}") // Indicar que a requisição aceita um parâmetro
    public ResponseEntity<Void> delete(@PathVariable String id) { // @PathVariable Indica que o id virá de parametro de url
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT) // tmb poderia usar o @PostMapping()
    public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {
        // Converter o DTO p/ User
        User obj = service.fromDto(objDto);
        obj.setId(id); // garantir que meu obj vai ter o Id da requisição
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

}
