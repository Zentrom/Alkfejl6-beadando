package hu.elte.alkfejl.ajandekozosprojekt.service;

import hu.elte.alkfejl.ajandekozosprojekt.model.UserData;
import static hu.elte.alkfejl.ajandekozosprojekt.model.UserLogin.Role.USER;
import hu.elte.alkfejl.ajandekozosprojekt.model.UserLogin;
import hu.elte.alkfejl.ajandekozosprojekt.repository.UserDataRepository;
import hu.elte.alkfejl.ajandekozosprojekt.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
@Data
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDataRepository userDataRepository;
    
    private UserLogin userLogin;
    
    private UserData userData;

    public UserLogin login(UserLogin userLogin) //throws UserNotValidException 
    {
        if (isValid(userLogin)) {
            return this.userLogin = userRepository.findByUsername(userLogin.getUsername()).get();
        }
        //throw new UserNotValidException();
        return null;
    }

    public UserLogin register(UserLogin userLogin) {
        userLogin.setRole(USER);
        this.userLogin = userRepository.save(userLogin);
        return userLogin;
    }
    
    public UserData register(UserData userData){
        this.userData = userDataRepository.save(userData);
        
        return userData;
    }

    public boolean isValid(UserLogin userLogin) {
        return userRepository.findByUsernameAndPassword(userLogin.getUsername(), userLogin.getPassword()).isPresent();
    }

    public boolean isLoggedIn() {
        return userLogin != null;
    }
}