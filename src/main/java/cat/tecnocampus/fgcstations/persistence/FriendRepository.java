package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.application.DTOs.FriendUserDTO;
import cat.tecnocampus.fgcstations.application.DTOs.UserFriendsDTO;
import cat.tecnocampus.fgcstations.application.DTOs.UserTopFriend;
import cat.tecnocampus.fgcstations.domain.Friend;
import cat.tecnocampus.fgcstations.domain.FriendId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, FriendId> {

    List<Friend> findByUserUsername(String username);

    @Query("SELECT f.user.username AS username, COUNT(f) AS numberOfFriends FROM Friend f GROUP BY f.user.username ORDER BY COUNT(f) DESC")
    List<UserTopFriend> findTop3UsersWithMostFriends();

    @Query("SELECT f.user.username AS username, f.user.name AS name FROM Friend f WHERE f.id.friend = :friendName")
    List<FriendUserDTO> findUsersByFriendName(String friendName);
}
