package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.DayofWeek;
import softuni.exam.models.entity.Forecast;

import java.util.List;
import java.util.Set;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast,Integer> {
    @Query("select f from Forecast f WHERE f.dayofWeek = ?1 AND f.city.population < ?2 " +
            "ORDER BY f.maxTemperature DESC, f.id")
    Set<Forecast> onlySundayForecasts(DayofWeek dayofWeek, int population);

}
