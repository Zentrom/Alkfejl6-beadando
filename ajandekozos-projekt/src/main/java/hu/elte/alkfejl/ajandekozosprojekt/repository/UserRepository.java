package hu.elte.alkfejl.ajandekozosprojekt.repository;

import hu.elte.alkfejl.ajandekozosprojekt.model.UserLogin;
import hu.elte.alkfejl.ajandekozosprojekt.model.UserData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserLogin, String> {
    //Optional<UserData> findByEmail(String email);

    Optional<UserLogin> findByUsername(String username);

    Optional<UserLogin> findByUsernameAndPassword(String username, String password);

    //public void save(User user);
}