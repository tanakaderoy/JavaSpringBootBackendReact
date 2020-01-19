package com.tanaka.rest.webservices.restfulwebservices;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TodoResource {
	@Autowired
	private TodoHardCodedService todoService;

	@GetMapping(path = "/todos/todo/{id}")
	public Todo getTodos(@PathVariable(name = "id") String id) {
//		throw new RuntimeException("Nope! Chuck Testa driving a Tesla ");
		Date date = new GregorianCalendar(2020, 1 - 1, 17).getTime();
		date.setHours(8);
		date.setMinutes(45);
		date.setSeconds(20);
		return new Todo((long) Integer.parseInt(id), "Todo bean", id, date, false);
	}

	@GetMapping(path = "/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
		return todoService.findAll();
	}

	@GetMapping(path = "/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username, @PathVariable long id) {
		return todoService.findById(id);
	}

	@DeleteMapping(path = "/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
		Todo todo = todoService.deleteById(id);

		return todo != null ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

	@PutMapping(path = "/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id,
			@RequestBody Todo todo) {
		Todo savedTodo = todoService.save(todo);
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}

	@PostMapping(path = "/users/{username}/todos")
	public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo) {
		todo.setId((long) -1);
		Todo savedTodo = todoService.save(todo);
		//Location
		//get current resource url
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTodo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	
	@GetMapping("/basicAuth")
	public ResponseEntity<String> authentication(){
	
		return new ResponseEntity<String>("Authenticated",HttpStatus.OK);
	}

}
