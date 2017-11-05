package hu.elte.alkfejl.ajandekozosprojekt.service;

import hu.elte.alkfejl.ajandekozosprojekt.model.Present;
import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import hu.elte.alkfejl.ajandekozosprojekt.repository.PresentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

public class PresentService {

    @Autowired
    private PresentRepository presentRepository;

    public Iterable<Present> findAllByRole(User user, int listId) {
        User.Role role = user.getRole();
        if (role.equals(User.Role.USER)) {
            return presentRepository.findAllByListIdAndHiddenFalse(listId);
        } else if (role.equals(User.Role.ADMIN)) {
            return presentRepository.findAllByListId(listId);
        }
        return Collections.emptyList();
    }

    public Iterable<Present> findAllForFriend(int listId) {
        return presentRepository.findAllByListId(listId);
    }

    public Present create(Present present) {
        return presentRepository.save(present);
    }

    public Present update(int id, Present present) {
        Present currentPresent = presentRepository.findOne(id);
        currentPresent.setName(present.getName());
        currentPresent.setPrice(present.getPrice());
        currentPresent.setUser(present.getUser());
        currentPresent.setLink(present.getLink());
        // TODO commenteket is updatelni kéne?
        //currentPresent.setComments(present.getComments());

        return presentRepository.save(present);
    }

    public Present read(int id) {
        return presentRepository.findOne(id);
    }

    public void delete(int id) {
        presentRepository.delete(id);
    }
}
