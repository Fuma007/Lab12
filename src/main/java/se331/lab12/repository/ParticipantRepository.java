package se331.lab12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab12.entity.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}