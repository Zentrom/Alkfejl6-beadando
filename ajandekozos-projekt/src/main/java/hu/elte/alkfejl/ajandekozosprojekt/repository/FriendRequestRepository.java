package hu.elte.alkfejl.ajandekozosprojekt.repository;

import hu.elte.alkfejl.ajandekozosprojekt.model.FriendRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendRequestRepository extends CrudRepository<FriendRequest, Integer> {

    Iterable<FriendRequest> findAllByRequesteeId(int requesteeId);

    Optional<FriendRequest> findByRequesteeIdAndRequesterId(int requesteeId, int requesterId);

    void deleteByRequesterIdAndRequesteeId(int requesterId, int requesteeId);
}
