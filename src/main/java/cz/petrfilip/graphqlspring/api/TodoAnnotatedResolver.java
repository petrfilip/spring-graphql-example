package cz.petrfilip.graphqlspring.api;

import cz.petrfilip.graphqlspring.api.dto.TodoDtoIn;
import cz.petrfilip.graphqlspring.domain.Todo;
import cz.petrfilip.graphqlspring.domain.User;
import cz.petrfilip.graphqlspring.repository.UserRepository;
import cz.petrfilip.graphqlspring.service.TodoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
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

  // N+1 issue
  // @SchemaMapping
  // public User createdBy(Todo todo) {
  //   log.info("Resolving user for todo: `{}`", todo);
  //   return userRepository.findById(todo.getCreatedBy().getId()).orElseThrow(RuntimeException::new);
  // }


  @BatchMapping // solve N+1 issue
  public Map<Todo, User> createdBy(List<Todo> todoList) {
    log.info("Resolving users for todos: `{}`", todoList);
    List<User> allById = userRepository.findAllById(todoList.stream().map(todo -> todo.getCreatedBy().getId()).toList());
    Map<Todo, User> result = new HashMap<>();
    for (Todo todo : todoList) {
      result.put(todo, allById.stream().filter(i -> i.getId().equals(todo.getCreatedBy().getId())).findFirst().orElseThrow());
    }
    return result;

  }

  // N+1 issue
  // @SchemaMapping
  // public List<Todo> todoList(User user) {
  //   log.info("Resolving user's todo: `{}`", user);
  //   return todoService.findByCreatedBy(user.getId());
  // }


  @BatchMapping // solve N+1 issue
  public Map<User, List<Todo>> todoList(List<User> userList) {
    log.info("Resolving users for todos: `{}`", userList);
    List<Todo> allById = todoService.findByCreatedByIds(userList.stream().map(User::getId).toList());
    Map<User, List<Todo>> result = new HashMap<>();
    for (User user : userList) {
      result.put(user, allById.stream().filter(todo -> todo.getCreatedBy().getId().equals(user.getId())).toList());
    }
    return result;

  }


  @MutationMapping
  public Todo createTodo(@Argument TodoDtoIn todo) {
    log.info("Create todo with title `{}`", todo.title());
    return todoService.create(todo);
  }


}