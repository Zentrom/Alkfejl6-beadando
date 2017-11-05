package hu.elte.alkfejl.ajandekozosprojekt.repository;

import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByEmail(String email);

    Optional<User> findByFirstname(String firstname);

    Optional<User> findByLastname(String lastname);
    
}