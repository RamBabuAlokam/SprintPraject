package com.cg.drycleaning.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.drycleaning.dao.IUserRepository;
import com.cg.drycleaning.beans.User;
import com.cg.drycleaning.exception.EmptyEntityListException;
import com.cg.drycleaning.exception.EntityCreationException;
import com.cg.drycleaning.exception.EntityDeletionException;
import com.cg.drycleaning.exception.PasswordNotMatchException;
import com.cg.drycleaning.exception.UserNotFoundException;
/**********************************************************************************
 * @author                 Shaik Abdul Rahiman
 * Description             It is a user service implementation class that defines the methods
 *                         mentioned in its interface.
 * Version                 1.0
 * created date            24-03-2021
 *
 ****************************************************************************************/
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserRepository userRepository;
	/************************************************************************************
	 * Method                     signIn
	 * Description                It is used to sign In into application
	 * @param user                user's reference variable
	 * @UserNotFoundException     It is raised due to invalid user details
	 * created by                 Shaik Abdul Rahiman
	 * created date               26-03-2021
	 ***********************************************************************************/

	@Override
	public Boolean signIn(User user) throws UserNotFoundException {
		Boolean status= false;
		Optional<User> resultUser=userRepository.findByuserName(user.getUserName());
		if (resultUser.isPresent()) {
			if((resultUser.get().getPassword().equals(user.getPassword()))) 
			{
				status=true;

		} 
			else 
			
			throw new UserNotFoundException("User Not Found");
		}
		return status;
	}
	/*******************************************************************
	 * Method                                     signOut
	 * Description                                It is used to signout from application
	 * @param user                                user's reference variable
	 * @UserNotFoundException                     It raised due to invalid user details
	 * created by                                 Shaik Abdul Rahiman
	 * Created date                               26-03-2021
	 ***********************************************************************/

	@Override
	public Boolean  signOut(User user) throws UserNotFoundException {
		Boolean status=false;
		Optional<User> resultUser=userRepository.findByuserName(user.getUserName());

		if (resultUser.isEmpty()) {
			throw new UserNotFoundException("User Not Found");
		}
		else if(resultUser.get().getPassword().equals(user.getPassword())) {
			 status = true;
		}
		return status;
	}
	/******************************************************************************
	 * Method                                changePassword
	 * Description                           It is used to change the password
	 * @param user                           User's reference variable
	 * @throws PasswordNotMatchException     It is raised due to invalid password
	 * @UserNotFoundException                It is raised due to invalid user details
	 * created by                            Shaik Abdul Rahiman
	 * created date                          26-03-2021
	 ********************************************************************************/

	@Override
	public User changePassword(String newPassword, User user) throws UserNotFoundException, PasswordNotMatchException {
		Optional<User> resultUser=userRepository.findByuserName(user.getUserName());
		if(resultUser.isPresent()) {
			if((resultUser.get().getPassword().equals(user.getPassword())))
			{
				user.setPassword(newPassword);
				return userRepository.save(user);
			}
			else
			{
				throw new PasswordNotMatchException("Password Not matched please enter valid password");
			}
		}
		else
		{
			throw new UserNotFoundException("User Not Found");
		}	
	}
	
	@Override
	public User viewUser(String userName) throws UserNotFoundException {
		try {
			Optional<User> user = userRepository.findByuserName(userName);
			if (user.isPresent()) {
				return user.get();
			} else {
				throw new UserNotFoundException("User is not found for Id " + userName);
			}

		} catch (Exception e) {
			throw new UserNotFoundException(e.getMessage());

		}

	}

	/****************************
	 * Method:                          viewAllUser
     *Description:                      To display all the users
	 * @throws EmptyEntityListException 
	 * @returns List<User>            - It returns all the users present in database
                *Created By                                - B.Sai Kiran
                *Created Date                            - 24-MARCH-2021                           
	 
	 ****************************/
	@Override
	public List<User> viewAllUser() {
		try {
			List<User> userList = userRepository.findAll();
			if (userList.isEmpty()) {
				throw new EmptyEntityListException(" No Users found");
			} else {
				return userList;
			}
		} catch (Exception e) {
			throw new EmptyEntityListException(e.getMessage());
		}

	}

	/****************************
	 * Method:                          validateUser
     *Description:                      It is used to verify username and password of user
     * @param username:                 username for validating user.
     *  @param password:                password for validating user.
	 * @throws EntityNotFoundException 
	 * @returns User                  - it returns user details if the details are correct else it throws UserNotFoundException
	 * @throws UserNotFoundException -  It is raised due to invalid username or password.
                *Created By                                - B.Sai Kiran
                *Created Date                            - 24-MARCH-2021                           
	 
	 ****************************/
	@Override
	public User validateUser(String username, String password) throws UserNotFoundException {
		try {
			Optional<User> user = userRepository.findByuserName(username);
			if (user.isPresent()) {
				if (username.equals(user.get().getUserName()) && (password.equals(user.get().getPassword()))) {
					return user.get();
				} else {
					throw new UserNotFoundException("Password does not match for " + username);
				}
			} else {
				throw new UserNotFoundException("User is not there with username :" + username);
			}
		} catch (Exception e) {
			throw new UserNotFoundException(e.getMessage());
		}
	}

	/****************************
	 * Method:                          addUser
     *Description:                      It is used to add user into user_details table
     * @param user:                     User's reference variable.
	 * @throws EntityCreationException 
	 * @returns User                    It returns user with details
                *Created By                                - B.Sai Kiran
                *Created Date                            - 24-MARCH-2021                           
	 
	 ****************************/
	@Override
	public User addUser(User user) {
		try {
			return userRepository.save(user);
		} catch (Exception e) {
			throw new EntityCreationException("Valid details of user are not entered");
		}
	}

	
	

	

	/****************************
	 * Method:                          removeUser
     *Description:                      It is used to remove user
     *@param user:                      User's reference variable.
	 * @throws EntityDeletionException 
	 * @returns User                    It returns the user that has been deleted
	 * @throws UserNotFoundException:   It is raised due to invalid user.
                * Created By                                - B.Sai Kiran
                *Created Date                            - 24-MARCH-2021                           
	 
	 ****************************/
	@Override
	public User removeUser(String userName) {
		try {
			Optional<User> deletedUser = userRepository.findById(userName);
			if (deletedUser.isPresent()) {
				userRepository.delete(deletedUser.get());
			}
			return deletedUser.get();
		} catch (Exception e) {
			throw new EntityDeletionException("User with id "+userName+" does not Exists.");
		}

	}

}