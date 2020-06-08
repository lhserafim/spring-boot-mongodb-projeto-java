// MODELO DE CAMADAS - ENTITY/DOMAIN
// Esta classe integra o REPOSITORY com o BD
// Esta classe representa as tabelas do BD

package com.alvoradatecno.sbmongodb.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//incluir a anotação @Document e @Id para indicar que se trata de uma coleção do MongoDB
@Document(collection = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private String email;

    @DBRef(lazy = true) // @DBRef Falar que um atributo está referenciando um outro atributo do MongoDB
                        // lazy é uma forma de não trazer os posts quando eu acessar os usuários. Só quando eu quiser ele trará os posts
    private List<Post> posts = new ArrayList<>(); // List é apenas uma interface. No java interfaces NÃO PODEM SER INSTANCIADAS.
    // Para "instanciar" precisamos colocar uma implementação da interface, como por ex. ArrayList<>()

    public User(){}

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
