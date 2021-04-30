package com.cg.drycleaning.service;

import java.util.List;

import com.cg.drycleaning.beans.User;
import com.cg.drycleaning.exception.PasswordNotMatchException;
import com.cg.drycleaning.exception.UserNotFoundException;
/****************************************************************************
 * @author               Shaik Abdul Rahiman
 * Description           It is a user service interface that describes the abstract methods
 *                       used in its implementation class.
 * version               1.0
 * created date          24-03-2021
 *
 ****************************************************************************/
public interface IUserService {
		public Boolean signIn(User user) throws UserNotFoundException;
		
		public Boolean signOut(User user) throws UserNotFoundException;
		
		public User changePassword(String newPassword, User user) throws UserNotFoundException, PasswordNotMatchException;
		
		public User viewUser(String userName) throws UserNotFoundException;

		public List<User> viewAllUser();

		public User validateUser(String username, String password) throws UserNotFoundException;

		public User addUser(User user);


		public User removeUser(String userName);
		

	}


