package com.springBootMongo.resources;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springBootMongo.domain.Post;
import com.springBootMongo.resources.util.URL;
import com.springBootMongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	PostService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String txt){
		txt = URL.decodeParam(txt);
		List<Post> posts = service.findByTitle(txt);
		return ResponseEntity.ok().body(posts);
	}
	
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(
							@RequestParam(value = "text", defaultValue = "") String txt,
							@RequestParam(value = "minDate", defaultValue = "") String minDate,
							@RequestParam(value = "maxDate", defaultValue = "") String maxDate){
		txt = URL.decodeParam(txt);
		LocalDate minDateD = URL.convertDate(minDate, LocalDate.parse("2024-01-01"));
		LocalDate maxDateD = URL.convertDate(maxDate, LocalDate.now().plusDays(1));
		List<Post> posts = service.fullSearch(txt, minDateD, maxDateD);
		return ResponseEntity.ok().body(posts);
	}

}
