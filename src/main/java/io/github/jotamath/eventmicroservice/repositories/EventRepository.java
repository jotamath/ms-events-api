package io.github.jotamath.eventmicroservice.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.jotamath.eventmicroservice.domain.Event;
import jakarta.annotation.Nonnull;

public interface EventRepository extends JpaRepository<Event, UUID>{
	
	@Query(value="SELECT * FROM events e WHERE parsedatetime(e.date, 'dd/MM/yyyy')> :currentDate", nativeQuery = true)
	List<Event> findUpComingEvents(@Param("currentDate") LocalDateTime currentDate);
	
	@Nonnull
	Optional<Event> findById(@Nonnull String id);

	List<Event> findByDateAfterOrderByDate(LocalDateTime now);
}
