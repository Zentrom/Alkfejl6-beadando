package hu.elte.alkfejl.ajandekozosprojekt.service;

import hu.elte.alkfejl.ajandekozosprojekt.model.Present;
import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import hu.elte.alkfejl.ajandekozosprojekt.model.WishList;
import hu.elte.alkfejl.ajandekozosprojekt.repository.PresentRepository;
import hu.elte.alkfejl.ajandekozosprojekt.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import static hu.elte.alkfejl.ajandekozosprojekt.model.User.Role.ADMIN;

@Service
@SessionScope
public class PresentService {

    private PresentRepository presentRepository;

    private WishListRepository wishListRepository;

    @Autowired
    public PresentService(PresentRepository presentRepository, WishListRepository wishListRepository) {
        this.presentRepository = presentRepository;
        this.wishListRepository = wishListRepository;
    }

    public Iterable<Present> findAllByListId(int listId) {
        return presentRepository.findAllByWishListId(listId);
    }

    public Iterable<Present> findAllByListIdForOwner(int listId) {
        return presentRepository.findAllByWishListIdAndHiddenFalse(listId);
    }

    public Present findById(int presentId) {
        return presentRepository.findOne(presentId);
    }

    public Present create(int wishlistId, Present present, User user) {
        WishList wishList = wishListRepository.findById(wishlistId);
        User listOwner = wishList.getUser();
        if (user.getRole().equals(ADMIN) || user.equals(listOwner)) {
            present.setHidden(false);
        } else {
            present.setHidden(true);
        }
        present.setWishList(wishList);

        return presentRepository.save(present);
    }

    public Present updatePresent(User currentUser, int presentId, Present present) {
        if (currentUser.getRole().equals(ADMIN)) {
            return updateByListOwnerOrAdmin(presentId, present);
        } else {
            return updateByFriend(presentId, present);
        }
    }

    public Present updateByListOwnerOrAdmin(int presentId, Present present) {
        Present currentPresent = presentRepository.findOne(presentId);
        currentPresent.setName(present.getName());
        currentPresent.setPrice(present.getPrice());
        currentPresent.setLink(present.getLink());

        return presentRepository.save(present);
    }

    public Present updateByFriend(int presentId, Present present) {
        Present currentPresent = presentRepository.findOne(presentId);
        currentPresent.setUser(present.getUser());

        return presentRepository.save(present);
    }

    public void delete(int id) {
        presentRepository.delete(id);
    }

}
