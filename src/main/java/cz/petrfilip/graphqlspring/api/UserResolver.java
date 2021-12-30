package cz.petrfilip.graphqlspring.api;

import cz.petrfilip.graphqlspring.api.dto.TodoDtoIn;
import cz.petrfilip.graphqlspring.api.dto.UserDtoIn;
import cz.petrfilip.graphqlspring.domain.Todo;
import cz.petrfilip.graphqlspring.domain.User;
import cz.petrfilip.graphqlspring.repository.UserRepository;
import cz.petrfilip.graphqlspring.service.TodoService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class UserResolver {

  @Autowired
  private UserRepository userRepository;

  @QueryMapping
  public List<User> getUsers() {
    return userRepository.findAll();
  }

  @MutationMapping
  public User createUser(@Argument UserDtoIn user) {
    log.info("Create user with email `{}`", user.email());
    User userEntity = new User();
    userEntity.setEmail(user.email());
    return userRepository.save(userEntity);
  }


}