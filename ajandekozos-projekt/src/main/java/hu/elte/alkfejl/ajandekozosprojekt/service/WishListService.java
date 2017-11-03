package hu.elte.alkfejl.ajandekozosprojekt.service;

import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import hu.elte.alkfejl.ajandekozosprojekt.model.WishList;
import hu.elte.alkfejl.ajandekozosprojekt.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    public Iterable<WishList> listByRole(User user) {
        User.Role role = user.getRole();
        if (role.equals(User.Role.USER)) {
            return wishListRepository.findAllByUser(user);
        } else if (role.equals(User.Role.ADMIN)) {
            return wishListRepository.findAll();
        }
        return Collections.emptyList();
    }

    public WishList createOrUpdate(WishList list) {
        return wishListRepository.save(list);
    }

    public WishList read(int id) {
        return wishListRepository.findOne(id);
    }

    public void delete(int id) {
        wishListRepository.delete(id);
    }

}
