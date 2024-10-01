package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.application.DTOs.UserDTOInterface;
import cat.tecnocampus.fgcstations.application.DTOs.UserDTOnoFJ;
import cat.tecnocampus.fgcstations.application.DTOs.UserTopFavoriteJourney;
import cat.tecnocampus.fgcstations.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<UserDTOnoFJ> findUserDTOnoFJByUsername(String username);

    Optional<UserDTOInterface> findUserDTOInterfaceByUsername(String username);

    @Query("SELECT f.user.username AS username, COUNT(f) AS numberOfFavoriteJourneys FROM FavoriteJourney f GROUP BY f.user.username ORDER BY COUNT(f) DESC")
    List<UserTopFavoriteJourney> findTop3UsersWithMostFavoriteJourneys();

    List<UserDTOInterface> findUserDTOInterfaceByNameAndSecondName(String username, String secondName);
}
