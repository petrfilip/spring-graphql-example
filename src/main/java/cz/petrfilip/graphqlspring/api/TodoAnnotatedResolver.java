package cz.petrfilip.graphqlspring.api;

import cz.petrfilip.graphqlspring.api.dto.TodoDtoIn;
import cz.petrfilip.graphqlspring.domain.Todo;
import cz.petrfilip.graphqlspring.domain.User;
import cz.petrfilip.graphqlspring.repository.UserRepository;
import cz.petrfilip.graphqlspring.service.TodoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TodoAnnotatedResolver {

  private final TodoService todoService;
  private final UserRepository userRepository;

  @QueryMapping
  public String hello() {
    return "Hello, world!";
  }

  @QueryMapping
  public List<Todo> getTodosByUserId(@Argument Integer userId, @Argument PageInfo pageInfo) {
    log.info("User's {} todos requests with pagination {}", userId, pageInfo);
    return todoService.findByCreatedBy(userId);
  }

  @SchemaMapping
  public User createdBy(Todo todo) {
    log.info("Resolving user for todo: `{}`", todo);
    return todo.getCreatedBy();
  }

  @SchemaMapping
  public List<Todo> todoList(User user) {
    log.info("Resolving user's todo: `{}`", user);
    return todoService.findByCreatedBy(user.getId());
  }

  @MutationMapping
  public Todo createTodo(@Argument TodoDtoIn todo) {
    log.info("Create todo with title `{}`", todo.title());

    return todoService.create(todo);
  }


}