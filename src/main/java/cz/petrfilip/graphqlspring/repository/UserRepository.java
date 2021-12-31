package cz.petrfilip.graphqlspring.repository;

import cz.petrfilip.graphqlspring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


}