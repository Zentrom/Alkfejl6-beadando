package hu.elte.alkfejl.ajandekozosprojekt.service;

import hu.elte.alkfejl.ajandekozosprojekt.model.FriendRequest;
import hu.elte.alkfejl.ajandekozosprojekt.model.User;
import hu.elte.alkfejl.ajandekozosprojekt.repository.FriendRequestRepository;
import hu.elte.alkfejl.ajandekozosprojekt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

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

    public Iterable<FriendRequest> findAllByRequesteeId(int requesteeId) {
        return friendRequestRepository.findAllByRequesteeId(requesteeId);
    }

    public FriendRequest findById(int friendRequestId) {
        return friendRequestRepository.findOne(friendRequestId);
    }

    public FriendRequest create(int requesteeId, User requester) {
        User requestee = userRepository.findOne(requesteeId);
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setRequester(requester);
        friendRequest.setRequestee(requestee);

        return friendRequestRepository.save(friendRequest);
    }


    public void process(int friendRequestId, FriendRequest friendRequest) {
        if (friendRequest.getStatus().equals(FriendRequest.Status.ACCEPTED)) {
            User requester = userRepository.findOne(friendRequest.getRequester().getId());
            User requestee = userRepository.findOne(friendRequest.getRequestee().getId());

            requester.getFriends().add(requestee);
            requestee.getFriends().add(requester);
        }

        friendRequestRepository.delete(friendRequestId);
    }
    
}
