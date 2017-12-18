package hu.elte.alkfejl.ajandekozosprojekt.repository;

import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import hu.elte.alkfejl.ajandekozosprojekt.model.WishList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends CrudRepository<WishList, Integer> {

    Iterable<WishList> findAllByUser(User user);

    Iterable<WishList> findAllByUserId(int userId);

    WishList findById(int listId);


}
