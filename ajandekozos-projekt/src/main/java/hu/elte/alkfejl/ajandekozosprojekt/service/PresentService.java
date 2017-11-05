package hu.elte.alkfejl.ajandekozosprojekt.service;

import hu.elte.alkfejl.ajandekozosprojekt.model.Present;
import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import hu.elte.alkfejl.ajandekozosprojekt.repository.PresentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PresentService {

    @Autowired
    private PresentRepository presentRepository;

    public List<Present> findAllByRole(User user, int listId) {
        User.Role role = user.getRole();
        if (role.equals(User.Role.USER)) {
            return presentRepository.findAllByWishListIdAndHiddenFalse(listId);
        } else if (role.equals(User.Role.ADMIN)) {
            return presentRepository.findAllByWishListId(listId);
        }
        return Collections.emptyList();
    }

    public List<Present> findAllForFriend(int listId) {
        return presentRepository.findAllByWishListId(listId);
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
