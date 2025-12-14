package io.github.jotamath.eventmicroservice.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.github.jotamath.eventmicroservice.services.EventService;
import io.github.jotamath.eventmicroservice.domain.Event;
import io.github.jotamath.eventmicroservice.dtos.EventRequestDTO;
import io.github.jotamath.eventmicroservice.dtos.SubscriptionRequestDTO;

@RestController
@RequestMapping("/events")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@GetMapping
	public List<Event> getAllEvents() {
		return eventService.getAllEvents();
	}
	
	@GetMapping("/upcoming")
	public List<Event> getUpComingEvents() {
		return eventService.getUpComingEvents();
	}
	
	@PostMapping
	public Event createEvent(@RequestBody EventRequestDTO event) {
		return eventService.createEvent(event);
	}
	
	@PostMapping("/{eventId}/register")
	public void registerParticipant(@PathVariable String eventId, @RequestBody SubscriptionRequestDTO subscriptionRequest) {
		eventService.registerParticipants(eventId, subscriptionRequest.participantEmail());
	}

}
