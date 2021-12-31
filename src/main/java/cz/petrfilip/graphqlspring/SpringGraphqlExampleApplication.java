package cz.petrfilip.graphqlspring;

import cz.petrfilip.graphqlspring.domain.Todo;
import cz.petrfilip.graphqlspring.domain.User;
import cz.petrfilip.graphqlspring.repository.TodoRepository;
import cz.petrfilip.graphqlspring.repository.UserRepository;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringGraphqlExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringGraphqlExampleApplication.class, args);
  }

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private TodoRepository todoRepository;

  @PostConstruct
  public void initData() {
    User userJiri = new User(null, "jiri@novak.cz");
    User userPepa = new User(null, "pepa@suchy.cz");
    userRepository.saveAll(Arrays.asList(userJiri, userPepa));


    todoRepository.save(new Todo(null, "Hey!!!", false, userPepa));
    todoRepository.save(new Todo(null, "123!!!", false, userPepa));
    todoRepository.save(new Todo(null, "ACBD!!!", false, userPepa));


    todoRepository.save(new Todo(null, "Jirka!!!", false, userJiri));
    todoRepository.save(new Todo(null, "is!!!", false, userJiri));
    todoRepository.save(new Todo(null, "the best", false, userJiri));




  }

}


