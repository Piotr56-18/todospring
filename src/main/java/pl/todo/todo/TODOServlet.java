package pl.todo.todo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
class TODOServlet {
    private final Logger logger = LoggerFactory.getLogger(TODOServlet.class);

    private TODORepository repository;

    public TODOServlet(TODORepository repository) {
        this.repository = repository;
    }

    @GetMapping
    ResponseEntity<List<TODO>> findAllTodos() {
        logger.info("Got request");
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/{id}")
    ResponseEntity<TODO>toggleTodo(@PathVariable Integer id) {
        var todo = repository.findById(id);
        todo.ifPresent(t -> {
            t.setDone(!t.isDone());
            repository.save(t);
        });
        return todo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<TODO>saveTodo(@RequestBody TODO todo) {
        return ResponseEntity.ok(repository.save(todo));
    }
}
