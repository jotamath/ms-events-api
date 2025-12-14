package io.github.jotamath.eventmicroservice.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

import io.github.jotamath.eventmicroservice.dtos.EventRequestDTO;

@Entity(name="event")
@Table(name="event")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Event {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private int maxParticipants;
	private int registredParticipants;
	private String date;
	private String title;
	private String description;
	
	
	public Event(EventRequestDTO eventRequest) {
		this.date = eventRequest.date();
		this.maxParticipants = eventRequest.maxParticipants();
		this.title = eventRequest.title();
		this.description = eventRequest.description();
		this.registredParticipants = eventRequest.registeredParticipants();
	}
	
	public int getRegisteredParticipants() {
        return registredParticipants;
    }

    public void setRegisteredParticipants(int registredParticipants) {
        this.registredParticipants = registredParticipants;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

}
