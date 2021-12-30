package cz.petrfilip.graphqlspring.repository;

import cz.petrfilip.graphqlspring.domain.Todo;
import cz.petrfilip.graphqlspring.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


}