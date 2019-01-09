package edu.sxu.service;

import edu.sxu.model.User;

public interface UserService {
	public void add(User user);
	public boolean checkUser(User user);
	public User getUser(User user);
}
