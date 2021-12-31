package cz.petrfilip.graphqlspring.service;

import cz.petrfilip.graphqlspring.api.dto.TodoDtoIn;
import cz.petrfilip.graphqlspring.domain.Todo;
import cz.petrfilip.graphqlspring.domain.User;
import cz.petrfilip.graphqlspring.repository.TodoRepository;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

  private final TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public List<Todo> findByCreatedBy(Integer userId) {
    return todoRepository.findByCreatedBy_Id(userId);
  }

  public List<Todo> findByCreatedByIds(Collection<Integer> userIds) {
    return todoRepository.findAllByCreatedBy_Ids(userIds);
  }

  public Todo create(TodoDtoIn dtoIn) {
    Todo todo = new Todo();
    todo.setTitle(dtoIn.title());
    todo.setCompleted(false);
    todo.setCreatedBy(User.builder().id(2).build());
    return todoRepository.save(todo);
  }
}
