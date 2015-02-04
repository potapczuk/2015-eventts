package com.potapczuk.jee.events.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.potapczuk.jee.events.entity.User;
import com.potapczuk.jee.events.repository.UserRepository;
import com.potapczuk.jee.events.util.Flash;
import com.potapczuk.jee.events.util.Request;

@Model
@SessionScoped
public class UsersController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Flash flash;
	
	@Inject
	private Request request;
	
	@Inject
	private UserRepository users;

	private User selectedUser = new User();

	public List<User> getUsers() {
		return users.findAllOrderedByName();
	}

	public String view(long id) {
		if (id < 1)
			return null;

		selectedUser = users.get(id);

		if (selectedUser == null)
			return null;

		return "/user/view";
	}

	public String create() {
		System.out.println("[UserRepository] Create");
		selectedUser = new User();
		return "/user/new";
	}
	
	public String edit() {
		Long id = Long.parseLong(request.getParam("id"));
		
		if (id < 1)
			return null;

		selectedUser = users.get(id);
		
		if (selectedUser == null)
			return null;
		
		System.out.println("Editing: " + selectedUser);
		
		return "/user/edit";
	}

	public String save() {
		try {
			System.out.println("Saving: " + selectedUser);
			users.store(selectedUser);
			selectedUser = new User();

			flash.flashMessage("User saved with success!");
			return "/user/list";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			flash.flashException("Error while saving event!", e);
			return null;
		}
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setUsers(UserRepository users) {
		this.users = users;
	}

}
