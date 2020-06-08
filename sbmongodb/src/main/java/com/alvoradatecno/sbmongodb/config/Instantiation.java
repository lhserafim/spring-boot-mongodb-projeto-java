// Faz a carga inicial dos dados / Instanciação da base de dados -- "TESTE "
package com.alvoradatecno.sbmongodb.config;

import com.alvoradatecno.sbmongodb.domain.Post;
import com.alvoradatecno.sbmongodb.domain.User;
import com.alvoradatecno.sbmongodb.repository.PostRepository;
import com.alvoradatecno.sbmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        // Limpara a base no MongoDB
        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        Post post1 = new Post(null, sdf.parse("21/03/2018 10:00:00"), "Partiu viagem", "Vou viajar pra SP", maria);
        Post post2 = new Post(null, sdf.parse("21/03/2019 05:00:10"), "Bom dia!", "Acordei feliz", maria);

        userRepository.saveAll(Arrays.asList(maria, alex, bob));
        postRepository.saveAll(Arrays.asList(post1, post2));
    }
}
