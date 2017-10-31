package hu.elte.alkfejl.ajandekozosprojekt.repository;

import hu.elte.alkfejl.ajandekozosprojekt.model.UserData;
import hu.elte.alkfejl.ajandekozosprojekt.model.UserLogin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends CrudRepository<UserData, Integer> {
    Optional<UserData> findByEmail(String email);

    Optional<UserData> findByFirstname(String firstname);

    Optional<UserData> findByLastname(String lastname);
    
    UserData save(UserData userData);
}