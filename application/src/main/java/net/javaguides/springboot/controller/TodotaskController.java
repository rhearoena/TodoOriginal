package net.javaguides.springboot.controller;

import net.javaguides.springboot.entity.Todotask;
import net.javaguides.springboot.repository.TodotaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:8082")
public class TodotaskController {

	@Autowired
	private TodotaskRepository todotaskRepository;

    // Create
    @PostMapping("/todotasks")
    public Todotask addTodo(@RequestBody Todotask todo) {
        return todotaskRepository.save(todo);
    }

    // Read all
    @GetMapping("/todotasks")
    public List<Todotask> getAllTodo() {
        return todotaskRepository.findAll();
    }

    // Read by ID
    @GetMapping("/todotasks/{id}")
    public Todotask getTodoById(@PathVariable Long id) {
        return todotaskRepository.findById(id).orElse(null);
    }

    // Update
    @PutMapping("/todotasks/{id}")
    public Todotask updateTodo(@PathVariable Long id, @RequestBody Todotask todoDetails) {
    	Todotask todo = todotaskRepository.findById(id).orElse(null);
        if (todo != null) {
        	todo.setTitle(todoDetails.getTitle());
            return todotaskRepository.save(todo);
        }
        return null;
    }

    // Delete
    @DeleteMapping("/todotasks/{id}")
    public String deleteTodo(@PathVariable Long id) {
    	todotaskRepository.deleteById(id);
        return "Todo deleted with id: " + id;
    }
}