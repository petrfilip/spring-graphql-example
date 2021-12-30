package cz.petrfilip.graphqlspring.api;

import cz.petrfilip.graphqlspring.api.dto.TodoDtoIn;
import cz.petrfilip.graphqlspring.domain.Todo;
import cz.petrfilip.graphqlspring.service.TodoService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class TodoResolver {

  private final TodoService todoService;

  public TodoResolver(TodoService service) {
    this.todoService = service;
  }

  @QueryMapping
  public String hello() {
    return "Hello, world!";
  }

  @QueryMapping
  public List<Todo> getTodosByUserId(@Argument Integer userId, @Argument PageInfo pageInfo) {
    log.info("User's {} todos requests with pagination {}", userId, pageInfo);
    return todoService.findByCreatedBy(userId);
  }

  @MutationMapping
  public Todo createTodo(@Argument TodoDtoIn todo) {
    log.info("Create todo with title `{}`", todo.title());

    return todoService.create(todo);
  }


}