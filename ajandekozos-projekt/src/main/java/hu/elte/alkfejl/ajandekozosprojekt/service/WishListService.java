package hu.elte.alkfejl.ajandekozosprojekt.service;

import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import hu.elte.alkfejl.ajandekozosprojekt.model.WishList;
import hu.elte.alkfejl.ajandekozosprojekt.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;

@Service
@SessionScope
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

    public WishList findById(int listId) {
        return wishListRepository.findById(listId);
    }

    public Iterable<WishList> findAllByUserId(int userId) {
        return wishListRepository.findAllByUserId(userId);
    }

    public WishList create(WishList list) {
        return wishListRepository.save(list);
    }

    public WishList update(int id, WishList wishList) {
        WishList currentWishList = wishListRepository.findOne(id);
        currentWishList.setTitle(wishList.getTitle());

        return wishListRepository.save(currentWishList);
    }

    public void delete(int id) {
        wishListRepository.delete(id);
    }

}
