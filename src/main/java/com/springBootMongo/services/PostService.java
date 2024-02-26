package com.springBootMongo.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBootMongo.domain.Post;
import com.springBootMongo.respository.PostRepository;
import com.springBootMongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;
	
	public Post findById(String id) {
		Optional<Post> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text){
		return repository.findByTitleContainingIgnoreCase(text);
	}
	
	public List<Post> fullSearch(String text, LocalDate dateMin, LocalDate dateMax){
		return repository.fullSearch(text, dateMin, dateMax);
	}
}
