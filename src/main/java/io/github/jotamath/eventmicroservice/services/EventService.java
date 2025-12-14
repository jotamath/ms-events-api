package io.github.jotamath.eventmicroservice.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.jotamath.eventmicroservice.feign.EmailServiceClient;
import io.github.jotamath.eventmicroservice.repositories.EventRepository;
import io.github.jotamath.eventmicroservice.repositories.SubscriptionRepository;
import io.github.jotamath.eventmicroservice.domain.*;
import io.github.jotamath.eventmicroservice.dtos.EmailRequestDTO;
import io.github.jotamath.eventmicroservice.dtos.EventRequestDTO;
import io.github.jotamath.eventmicroservice.exceptions.EventFullException;
import io.github.jotamath.eventmicroservice.exceptions.EventNotFoundException;

@Service
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private EmailServiceClient emailServiceClient;
	
	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}
	
	public List<Event> getUpComingEvents() {
		return eventRepository.findByDateAfterOrderByDate(LocalDateTime.now());
	}
	
	public Event createEvent(EventRequestDTO eventRequest) {
		Event newEvent = new Event(eventRequest);
		return eventRepository.save(newEvent);
	}
	
	private Boolean isEventFull(Event event) {
		return event.getRegistredParticipants() >= event.getMaxParticipants();
	}
	
	public void registerParticipants(String eventId, String participantEmail) {
		UUID uuid = UUID.fromString(eventId);
		
		Event event = eventRepository.findById(uuid).orElseThrow(EventNotFoundException::new);
		
		if(isEventFull(event)) {
			throw new EventFullException();
		}
		
		Subscription subscription = new Subscription(event, participantEmail);
		subscriptionRepository.save(subscription);
		
		event.setRegisteredParticipants(event.getRegisteredParticipants() + 1);
		
		EmailRequestDTO emailRequest = new EmailRequestDTO(participantEmail, "Confirmaçao de Inscriçao", "Você foi inscrito no evento com sucesso!");
		
		//Comentando a linha abaixo por conta do Microsserviço de Emails nao ser funcional ainda. 
		//TODO - Microservices de Email
		//emailServiceClient.sendEmail(emailRequest);
	}
}
