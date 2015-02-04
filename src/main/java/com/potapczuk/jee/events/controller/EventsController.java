package com.potapczuk.jee.events.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.potapczuk.jee.events.entity.Event;
import com.potapczuk.jee.events.repository.EventRepository;
import com.potapczuk.jee.events.util.Flash;
import com.potapczuk.jee.events.util.Request;

@Model
@SessionScoped
public class EventsController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Flash flash;
	
	@Inject
	private Request request;
	
	@Inject
	private EventRepository events;

	private Event selectedEvent = new Event();

	public List<Event> getEvents() {
		return events.findAllOrderedByDate();
	}

	public String view(long id) {
		if (id < 1)
			return null;

		selectedEvent = events.get(id);

		if (selectedEvent == null)
			return null;

		return "/event/view";
	}

	public String create() {
		selectedEvent = new Event();
		return "/event/new";
	}
	
	public String remove() {
		Long id = Long.parseLong(request.getParam("id"));
		
		if (id < 1)
			return null;
		
		selectedEvent = events.get(id);
		
		if (selectedEvent == null)
			return null;
		
		System.out.println("Remove: " + selectedEvent);
		
		return "/event/remove";
	}
	
	public String removeConf(Long id) {
		
		if (id < 1)
			return null;
		
		selectedEvent = events.get(id);
		
		if (selectedEvent == null)
			return null;
		
		System.out.println("Removing: " + selectedEvent);
		
		events.remove(selectedEvent);
		
		return "/event/my";
	}
	
	public String edit() {
		Long id = Long.parseLong(request.getParam("id"));
		
		if (id < 1)
			return null;

		selectedEvent = events.get(id);
		
		if (selectedEvent == null)
			return null;
		
		System.out.println("Editing: " + selectedEvent);
		
		return "/event/edit";
	}

	public String save() {
		try {
			System.out.println("Saving: " + selectedEvent);
			events.store(selectedEvent);
			selectedEvent = new Event();

			flash.flashMessage("Event saved with success!");

			return "/event/my";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			flash.flashException("Error while saving event!", e);
			return null;
		}
	}

	public Event getSelectedEvent() {
		return selectedEvent;
	}

	public void setEvents(EventRepository events) {
		this.events = events;
	}
}
