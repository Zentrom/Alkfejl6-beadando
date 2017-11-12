package hu.elte.alkfejl.ajandekozosprojekt.service;

import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import hu.elte.alkfejl.ajandekozosprojekt.repository.UserRepository;
import hu.elte.alkfejl.ajandekozosprojekt.service.exceptions.UserNotValidException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.stream.Collectors;

import static hu.elte.alkfejl.ajandekozosprojekt.model.User.Role.USER;

@Service
@SessionScope
@Data
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    private User user;

    public User login(User user) throws UserNotValidException {
        if (isValid(user)) {
            return this.user = userRepository.findByUsername(user.getUsername()).get();
        }
        throw new UserNotValidException();
    }

    public User register(User user) {
        user.setRole(USER);
        this.user = userRepository.save(user);

        User admin = userRepository.findByUsername("admin").get();
        admin.getFriends().add(user);

        return user;
    }

    public boolean isValid(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).isPresent();
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
    }

/*    public User update(int id, User user) {
        User currentUser = userRepository.findOne(id);
        currentUser.setFirstname(user.getFirstname());
        currentUser.setLastname(user.getLastname());
        currentUser.setEmail(user.getEmail());
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        currentUser.setRole(User.Role.USER);

        return this.user = userRepository.save(currentUser);
    }*/

    public Iterable<User> listFriends() {
        return user.getFriends();
    }

    public List<User> findPossibleFriends(String firstName, String lastName) {
        List<User> searchedUsers = userRepository.findAllByFirstnameContainingAndLastnameContaining(firstName, lastName);
        List<User> alreadyFriends = user.getFriends();

        return searchedUsers.stream().filter(x -> !alreadyFriends.contains(x)).collect(Collectors.toList());
    }

    public void deleteUser(int userId) {
        userRepository.delete(userId);
    }

    public void deleteFriend(int friendId) {
        User friend = userRepository.findOne(friendId);
        friend.getFriends().removeIf(x -> x.equals(user));
        user.getFriends().removeIf(x -> x.equals(friend));
    }
}