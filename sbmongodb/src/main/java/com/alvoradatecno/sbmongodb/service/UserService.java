// MODELO DE CAMADAS - SERVICE
// Esta classe integra o RESOURCE (CONTROLLER) com o REPOSITORY
// É nesta camada que estão implementadas as regras de negócio

package com.alvoradatecno.sbmongodb.service;

import com.alvoradatecno.sbmongodb.domain.User;
import com.alvoradatecno.sbmongodb.dto.UserDTO;
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

    public User insert(User obj) {
        return repo.insert(obj);
    }

    public void delete(String id) {
        findById(id); // Buscar se o id existe e se não existir já lança a exceção
        repo.deleteById(id);
    }

    public User update(User obj) {
        User newObj = findById(obj.getId()); // Buscar o usuário p/ poder atualizar
        updateData(newObj, obj); // faço a atualização do objeto em memória
        return repo.save(newObj);
    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    // Este obj criado aqui, está violando a regra da responsabilidade única INTENSIONALMENTE
    // pois já está prevendo futuras mudanças o que facilitaria na manutenção, pois o Service já fala com o BD
    // Poderia ser criado no UserDTO
    public User fromDto(UserDTO objDto) {
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }
}
