package edu.sxu.dao;

import edu.sxu.model.User;

public interface UserDao {
	public void save(User user);
	public boolean isExistUser(User user);
	public User getUser(User user);
}