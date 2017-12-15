package hu.elte.alkfejl.ajandekozosprojekt.service;

import hu.elte.alkfejl.ajandekozosprojekt.model.FriendRequest;
import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import hu.elte.alkfejl.ajandekozosprojekt.model.dto.FriendRequestDTO;
import hu.elte.alkfejl.ajandekozosprojekt.model.dto.UserDTO;
import hu.elte.alkfejl.ajandekozosprojekt.repository.FriendRequestRepository;
import hu.elte.alkfejl.ajandekozosprojekt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import java.util.LinkedList;
import java.util.List;

@Service
@SessionScope
public class FriendRequestService {

    private FriendRequestRepository friendRequestRepository;

    private UserRepository userRepository;

    @Autowired
    public FriendRequestService(FriendRequestRepository friendRequestRepository, UserRepository userRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRepository = userRepository;
    }

    // csak a reqeuestert állítjuk be, a requestee-t felesleges
    public List<FriendRequestDTO> findAllByRequesteeId(int requesteeId) {
        List<FriendRequestDTO> friendRequestsDTO = new LinkedList();
        for (FriendRequest request: friendRequestRepository.findAllByRequesteeId(requesteeId)) {
            User requester = request.getRequester();

            FriendRequestDTO requestDTO = new FriendRequestDTO();
            requestDTO.setId(request.getId());
            requestDTO.setRequester(new UserDTO(requester.getId(), requester.getFirstname(), requester.getLastname()));

            friendRequestsDTO.add(requestDTO);
        }
        return friendRequestsDTO;
    }

    public FriendRequestDTO create(int requesteeId, User requester) {
        FriendRequestDTO newRequestDTO = new FriendRequestDTO();

        User requestee = userRepository.findOne(requesteeId);
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setRequester(requester);
        friendRequest.setRequestee(requestee);
        FriendRequest saved = friendRequestRepository.save(friendRequest);

        newRequestDTO.setId(saved.getId());
        newRequestDTO.setRequester(new UserDTO(requester.getId(), requester.getFirstname(), requester.getLastname()));
        newRequestDTO.setRequestee(new UserDTO(requestee.getId(), requestee.getFirstname(), requestee.getLastname()));

        return newRequestDTO;
    }

    @Transactional
    public void process(int friendRequestId, int statusInt) {
        FriendRequest.Status status = FriendRequest.Status.values()[statusInt];
        FriendRequest friendRequest = friendRequestRepository.findOne(friendRequestId);

        User requester = userRepository.findOne(friendRequest.getRequester().getId());
        User requestee = userRepository.findOne(friendRequest.getRequestee().getId());

        friendRequestRepository.deleteByRequesterIdAndRequesteeId(requestee.getId(), requester.getId());
        friendRequestRepository.deleteByRequesterIdAndRequesteeId(requester.getId(), requestee.getId());

        if (status.equals(FriendRequest.Status.ACCEPTED)) {
            requester.getFriends().add(requestee);
            requestee.getFriends().add(requester);
        }

    }
    
}
