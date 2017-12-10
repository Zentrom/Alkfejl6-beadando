package hu.elte.alkfejl.ajandekozosprojekt.service;

import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import hu.elte.alkfejl.ajandekozosprojekt.model.dto.UserDTO;
import hu.elte.alkfejl.ajandekozosprojekt.repository.FriendRequestRepository;
import hu.elte.alkfejl.ajandekozosprojekt.repository.UserRepository;
import hu.elte.alkfejl.ajandekozosprojekt.service.exceptions.UserNotValidException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static hu.elte.alkfejl.ajandekozosprojekt.model.User.Role.ADMIN;
import static hu.elte.alkfejl.ajandekozosprojekt.model.User.Role.USER;

@Service
@SessionScope
@Data
public class UserService {

    private UserRepository userRepository;

    private FriendRequestRepository friendRequestRepository;

    private PasswordEncoder passwordEncoder;

    private User user;

    @Autowired
    public UserService(UserRepository userRepository, FriendRequestRepository friendRequestRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.friendRequestRepository = friendRequestRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(User user) throws UserNotValidException {
        Optional<User> dbUser = userRepository.findByUsername(user.getUsername());

        System.out.println(passwordEncoder.encode(user.getPassword()));

        if(dbUser.isPresent() && passwordEncoder.matches(user.getPassword(), dbUser.get().getPassword())) {
            this.user = dbUser.get();
            return this.user;
        } else {
            throw new UserNotValidException();
        }
    }

    public User register(User user) {
        user.setRole(USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.user = userRepository.save(user);

        return user;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
    }

    public List<UserDTO> listFriends() {
        user = userRepository.findOne(user.getId());
        List<UserDTO> friendsDTO = new LinkedList();

        if (ADMIN.equals(user.getRole())) {
            List<User> users = (List) userRepository.findAll();
            users.remove(user);
            users.stream().map(x -> friendsDTO.add(new UserDTO(x.getId(), x.getFirstname(), x.getLastname())));
        } else {
            user.getFriends().stream().map(x -> friendsDTO.add(new UserDTO(x.getId(), x.getFirstname(), x.getLastname())));
        }

        return friendsDTO;
    }

    private boolean alreadyRequested(int requesteeId) {
        return friendRequestRepository.findByRequesteeIdAndRequesterId(requesteeId, user.getId()) == null;
    }

    @Transactional
    public List<UserDTO> findPossibleFriends(String firstName, String lastName) {
        List<User> searchedUsers = userRepository.findAllByFirstnameContainingAndLastnameContaining(firstName, lastName);
        user = userRepository.findOne(user.getId());
        List<User> alreadyFriends = user.getFriends();

        //searchedUsers.forEach(user -> System.out.println(user.getFirstname() + " " + user.getLastname()));

        List<User> filteredUsers = searchedUsers.stream().filter(user -> !alreadyFriends.contains(user)
                && !user.getRole().equals(ADMIN)
                && !user.equals(this.user)
                && !alreadyRequested(user.getId())).collect(Collectors.toList());

        List<UserDTO> filteredUsersDTO = new LinkedList();
        filteredUsers.forEach(user -> filteredUsersDTO.add(new UserDTO(user.getId(), user.getFirstname(), user.getLastname())));

        return filteredUsersDTO;
    }

    @Transactional
    public void deleteUser(int userId) {
        User userToDelete = userRepository.findOne(userId);
        userRepository.delete(userToDelete);
    }

    @Transactional
    public void deleteFriend(int friendId) {
        User friend = userRepository.findOne(friendId);
        user = userRepository.findOne(user.getId());

        friend.getFriends().remove(user);
        user.getFriends().remove(friend);

        userRepository.save(friend);
    }

    public void deleteFriendOrUser(int friendId) {
        if (user.getRole().equals(ADMIN)) {
            deleteUser(friendId);
        } else {
            deleteFriend(friendId);
        }
    }
}