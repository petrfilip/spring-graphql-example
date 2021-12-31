package cz.petrfilip.graphqlspring.repository;

import cz.petrfilip.graphqlspring.domain.Todo;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

  List<Todo> findByCreatedBy_Id(Integer userId);

  @Query("SELECT i FROM Todo i WHERE i.createdBy.id IN :ids")
  List<Todo> findAllByCreatedBy_Ids(Collection<Integer> ids);

}
