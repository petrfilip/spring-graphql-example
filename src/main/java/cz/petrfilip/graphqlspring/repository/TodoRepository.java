package cz.petrfilip.graphqlspring.repository;

import cz.petrfilip.graphqlspring.domain.Todo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

  List<Todo> findByCreatedBy_Id(Integer userId);

}
