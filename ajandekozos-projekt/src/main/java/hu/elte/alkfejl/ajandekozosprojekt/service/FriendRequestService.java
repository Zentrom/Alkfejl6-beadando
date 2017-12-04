package hu.elte.alkfejl.ajandekozosprojekt.service;

import hu.elte.alkfejl.ajandekozosprojekt.model.FriendRequest;
import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import hu.elte.alkfejl.ajandekozosprojekt.model.dto.FriendRequestDTO;
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

    public List<FriendRequestDTO> findAllByRequesteeId(int requesteeId) {
        List<FriendRequestDTO> friendRequests = new LinkedList();
        for (FriendRequest request: friendRequestRepository.findAllByRequesteeId(requesteeId)) {
            User requester = request.getRequester();
            User requestee = request.getRequestee();

            FriendRequestDTO requestDTO = new FriendRequestDTO();
            requestDTO.setId(request.getId());
            requestDTO.setRequesteeId(requestee.getId());
            requestDTO.setRequesterId(requester.getId());
            requestDTO.setRequesteeName(requestee.getFirstname() + " " + requestee.getLastname());
            requestDTO.setRequesterName(requester.getFirstname() + " " + requester.getLastname());

            friendRequests.add(requestDTO);
        }
        return friendRequests;
    }

    public FriendRequestDTO findById(int friendRequestId) {
        FriendRequest found = friendRequestRepository.findOne(friendRequestId);
        FriendRequestDTO foundDTO = new FriendRequestDTO();

        User requester = found.getRequester();
        User requestee = found.getRequestee();

        foundDTO.setRequesteeId(requestee.getId());
        foundDTO.setRequesterId(requester.getId());
        foundDTO.setRequesteeName(requestee.getFirstname() + " " + requestee.getLastname());
        foundDTO.setRequesterName(requester.getFirstname() + " " + requester.getLastname());

        return foundDTO;
    }

    public FriendRequestDTO create(int requesteeId, User requester) {
        FriendRequestDTO newRequest = new FriendRequestDTO();

        User requestee = userRepository.findOne(requesteeId);
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setRequester(requester);
        friendRequest.setRequestee(requestee);
        friendRequestRepository.save(friendRequest);

        newRequest.setRequesterName(requester.getFirstname() + " " + requester.getLastname());
        newRequest.setRequesteeName(requestee.getFirstname() + " " + requestee.getLastname());
        newRequest.setRequesterId(requester.getId());
        newRequest.setRequesteeId(requestee.getId());
        return newRequest;
    }

    @Transactional
    public void process(int friendRequestId, int statusInt) {
        FriendRequest.Status status = FriendRequest.Status.values()[statusInt];
        FriendRequest friendRequest = friendRequestRepository.findOne(friendRequestId);
        if (status.equals(FriendRequest.Status.ACCEPTED)) {
            User requester = userRepository.findOne(friendRequest.getRequester().getId());
            User requestee = userRepository.findOne(friendRequest.getRequestee().getId());

            friendRequestRepository.deleteByRequesterIdAndRequesteeId(requestee.getId(), requester.getId());

            requester.getFriends().add(requestee);
            requestee.getFriends().add(requester);
        }

        friendRequestRepository.delete(friendRequestId);
    }
    
}
