package com.tanaka.rest.webservices.restfulwebservices;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

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
public class TodoJPAResource {
	@Autowired
	private TodoHardCodedService todoService;

	@Autowired
	private TodoJpaRepository todoJpaRepository;

//	@GetMapping(path = "/todos/todo/{id}")
//	public Todo getTodos(@PathVariable(name = "id") String id) {
////		throw new RuntimeException("Nope! Chuck Testa driving a Tesla ");
//		Date date = new GregorianCalendar(2020, 1 - 1, 17).getTime();
//		date.setHours(8);
//		date.setMinutes(45);
//		date.setSeconds(20);
//		return new Todo((long) Integer.parseInt(id), "Todo bean", id, date, false);
//	}

	@GetMapping(path = "/jpa/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
		
		return todoJpaRepository.findByUsername(username);
	}

	@GetMapping(path = "/jpa/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username, @PathVariable long id) throws Exception {
		Optional<Todo> optionalTodo = todoJpaRepository.findById(id);
		if (optionalTodo == null) {
			throw new Exception("Something went wrong");
		}
		return optionalTodo.get();
	}

	@DeleteMapping(path = "/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
		todoJpaRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	@PutMapping(path = "/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id,
			@RequestBody Todo todo) {
		todo.setUsername(username);
		Todo savedTodo = todoJpaRepository.save(todo);
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}

	@PostMapping(path = "/jpa/users/{username}/todos")
	public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo) {
//		todo.setId((long) -1);
		todo.setUsername(username);

		Todo savedTodo = todoJpaRepository.save(todo);
		// Location
		// get current resource url
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTodo.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

//	@GetMapping("/basicAuth")
//	public ResponseEntity<String> authentication() {
//
//		return new ResponseEntity<String>("Authenticated", HttpStatus.OK);
//	}

}
