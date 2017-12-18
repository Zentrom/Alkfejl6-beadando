package hu.elte.alkfejl.ajandekozosprojekt.service;

import hu.elte.alkfejl.ajandekozosprojekt.model.Present;
import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import hu.elte.alkfejl.ajandekozosprojekt.model.WishList;
import hu.elte.alkfejl.ajandekozosprojekt.model.dto.PresentDTO;
import hu.elte.alkfejl.ajandekozosprojekt.model.dto.UserDTO;
import hu.elte.alkfejl.ajandekozosprojekt.repository.PresentRepository;
import hu.elte.alkfejl.ajandekozosprojekt.repository.UserRepository;
import hu.elte.alkfejl.ajandekozosprojekt.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import java.util.LinkedList;
import java.util.List;

import static hu.elte.alkfejl.ajandekozosprojekt.model.User.Role.ADMIN;

@Service
@SessionScope
public class PresentService {

    private PresentRepository presentRepository;

    private WishListRepository wishListRepository;

    private UserRepository userRepository;

    @Autowired
    public PresentService(PresentRepository presentRepository, WishListRepository wishListRepository, UserRepository userRepository) {
        this.presentRepository = presentRepository;
        this.wishListRepository = wishListRepository;
        this.userRepository = userRepository;
    }

    // TODO itt hogy setteljem a buyert ha az null?
    public List<PresentDTO> findAllByListId(int listId) {
        List<PresentDTO> presentsDTO = new LinkedList<>();
        List<Present> presents = presentRepository.findAllByWishListId(listId);

        for (Present present: presents) {
            PresentDTO presentDTO = new PresentDTO();
            presentDTO.setId(present.getId());
            presentDTO.setName(present.getName());
            presentDTO.setPrice(present.getPrice());
            presentDTO.setLink(present.getLink());
            presentDTO.setUser(present.getUser() != null ?
                                new UserDTO(present.getUser().getId(), present.getUser().getFirstname(), present.getUser().getLastname())
                                : null);
            presentDTO.setHidden(present.isHidden());

            presentsDTO.add(presentDTO);
        }

        return presentsDTO;
    }

    public List<PresentDTO> findAllByListIdForOwner(int listId) {
        List<PresentDTO> presentsDTO = new LinkedList<>();
        List<Present> presents = presentRepository.findAllByWishListIdAndHiddenFalse(listId);

        for (Present present: presents) {
            PresentDTO presentDTO = new PresentDTO();
            presentDTO.setId(present.getId());
            presentDTO.setName(present.getName());
            presentDTO.setPrice(present.getPrice());
            presentDTO.setLink(present.getLink());

            presentsDTO.add(presentDTO);
        }

        return presentsDTO;
    }

    public PresentDTO findById(int presentId) {
        Present found = presentRepository.findOne(presentId);
        PresentDTO foundDTO = new PresentDTO();
        foundDTO.setId(found.getId());
        foundDTO.setName(found.getName());
        foundDTO.setPrice(found.getPrice());
        foundDTO.setLink(found.getLink());
        foundDTO.setUser(found.getUser() != null ?
                new UserDTO(found.getId(), found.getUser().getFirstname(), found.getUser().getLastname())
                : null);

        return foundDTO;
    }

    @Transactional
    public PresentDTO create(int wishlistId, PresentDTO presentDTO) {
        WishList wishList = wishListRepository.findById(wishlistId);
        Present present = new Present();

        present.setName(presentDTO.getName());
        present.setPrice(presentDTO.getPrice());
        present.setLink(presentDTO.getLink());
        present.setHidden(presentDTO.isHidden());
        //present.setUser(null);
        present.setWishList(wishList);
        Present saved = presentRepository.save(present);

        presentDTO.setId(saved.getId());

        return presentDTO;
    }

/*    public PresentDTO updatePresent(User currentUser, int presentId, PresentDTO present) {
        if (currentUser.getRole().equals(ADMIN)) {
            System.out.println("ADMIN V LIST OWNER");
            return updateByListOwnerOrAdmin(presentId, present);
        } else {
            System.out.println("FRIEND");
            return updateByFriend(presentId, present);
        }
    }*/

    public PresentDTO updateByListOwnerOrAdmin(int presentId, PresentDTO present) {
        Present currentPresent = presentRepository.findOne(presentId);
        currentPresent.setName(present.getName());
        currentPresent.setPrice(present.getPrice());
        currentPresent.setLink(present.getLink());

        presentRepository.save(currentPresent);

        System.out.println("PRESENT ADMIN V OWNER UPDATE");

        return present;
    }

    public PresentDTO updateByFriend(int presentId, PresentDTO present) {
        Present currentPresent = presentRepository.findOne(presentId);
        if (present.getUser() != null) {
            User buyer = userRepository.findOne(present.getUser().getId());
            currentPresent.setUser(buyer);
        } else {
            currentPresent.setUser(null);
        }

        currentPresent.setName(present.getName());
        currentPresent.setPrice(present.getPrice());
        currentPresent.setLink(present.getLink());

        presentRepository.save(currentPresent);

        System.out.println("PRESENT FRIEND UPDATE");


        return present;
    }

    public void delete(int id) {
        System.out.println("PRESENT ID TO DELETE: "+ id);
        presentRepository.delete(id);
    }

}
