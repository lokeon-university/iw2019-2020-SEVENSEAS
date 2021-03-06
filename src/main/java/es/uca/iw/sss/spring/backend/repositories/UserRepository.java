package es.uca.iw.sss.spring.backend.repositories;

import es.uca.iw.sss.spring.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
  User findById(int id);

  User findByUsername(String user);

  User findByUsername(User userInstance);

  User findByEmail(String email);

  List<User> findByUsernameStartsWithIgnoreCase(String lastName);
}
