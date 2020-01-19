package com.tanaka.rest.webservices.restfulwebservices;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
class TodoHardCodedService {
	private static List<Todo> todos = new ArrayList();
	private static int idCounter = 0;

	static {
		Date date = new GregorianCalendar(2020, 1 - 1, 17).getTime();
		date.setHours(12+7);
		date.setMinutes(45);
		date.setSeconds(20);
		todos.add(new Todo(++idCounter, "tanaka", "Play fifa", date, false));
		todos.add(new Todo(++idCounter, "tanaka", "Play red dead", date, false));

		todos.add(new Todo(++idCounter, "tanaka", "Play assassins creed", date, false));

	}

	public List<Todo> findAll() {
		return todos;
	}
	public Todo deleteById(long id) {
		Todo todo = findById(id);
		if (todo == null)
			return null;
		if (todos.remove(todo))
			return todo;
		return null;
	}

	
	public Todo save(Todo todo) {
		if(todo.getId()==-1 || todo.getId() == 0) {
			todo.setId(++idCounter);
			todos.add(todo);
		
		}else {
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}
	public Todo findById(long id) {

		return (Todo) todos.stream().filter(todo -> todo.getId() == id).findFirst().get();
//		for (Todo todo : todos) {
//			return todo.getId() == id ? todo : null;
//		}
//		return null;
	}
}