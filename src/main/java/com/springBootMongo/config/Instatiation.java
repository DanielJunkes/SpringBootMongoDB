package com.springBootMongo.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.springBootMongo.domain.Post;
import com.springBootMongo.domain.User;
import com.springBootMongo.dto.AuthorDTO;
import com.springBootMongo.dto.CommentDTO;
import com.springBootMongo.dto.CommentDTO;
import com.springBootMongo.respository.PostRepository;
import com.springBootMongo.respository.UserRepository;

@Configuration
public class Instatiation implements CommandLineRunner{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post p1 = new Post(null, LocalDate.now(), "Partiu viajar", "To indo viajar, flw galera", new AuthorDTO(maria));
		Post p2 = new Post(null, LocalDate.now(), "Testando", "To testando o novo app", new AuthorDTO(maria));
		
		p1.getComments().add(new CommentDTO("Boa viagem", LocalDate.now(), new AuthorDTO(alex)));
		p1.getComments().add(new CommentDTO("Boa sorte na viagem", LocalDate.now(), new AuthorDTO(bob)));
		
		p2.getComments().add(new CommentDTO("ta funcionando bem?", LocalDate.now(), new AuthorDTO(bob)));
		p2.getComments().add(new CommentDTO("parece um bom app", LocalDate.now(), new AuthorDTO(alex)));
		
		postRepository.saveAll(Arrays.asList(p1, p2));
		
		maria.getPosts().addAll(Arrays.asList(p1, p2));
		userRepository.save(maria);
	}

}
