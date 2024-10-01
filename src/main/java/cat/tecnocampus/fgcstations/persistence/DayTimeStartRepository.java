package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.application.DTOs.PopularDayOfWeek;
import cat.tecnocampus.fgcstations.domain.DayTimeStart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DayTimeStartRepository extends JpaRepository<DayTimeStart, String> {

    @Query("SELECT t.dayOfWeek FROM DayTimeStart t GROUP BY t.dayOfWeek ORDER BY COUNT(t) DESC")
    List<PopularDayOfWeek> findPopularDayOfWeek();
}
