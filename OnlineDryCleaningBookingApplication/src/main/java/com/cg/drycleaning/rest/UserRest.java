package com.cg.drycleaning.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.drycleaning.beans.User;
import com.cg.drycleaning.exception.PasswordNotMatchException;
import com.cg.drycleaning.exception.UserNotFoundException;
import com.cg.drycleaning.service.IUserService;

import io.swagger.annotations.Api;
/***********************************************************
 * @author              Shaik Abdul Rahiman
 * Description          It is a controller class that controls the data flow into model object 
 *                      and updates the view whenever data changes
 * Version              1.0
 * created date         24-03-2021
 *************************************************************/
@CrossOrigin("http://localhost:4200")
@Api(value = "Swagger2DemoRestController")
@RestController
@RequestMapping("/v1")
public class UserRest {
	@Autowired
	private IUserService iUserService;

	/****************************************************************************
	 * Method                         loginUser
	 * Description                    It is used to login into online application
	 * @param user                    user reference variable
	 * @return                        return true if user details is correct or else false
	 * @throws UserNotFoundException  it is raises due to invalid user details
	 ****************************************************************************/
	@PostMapping("/signin")
	public ResponseEntity<Boolean> loginUser(@RequestBody User user) throws UserNotFoundException{
		return new ResponseEntity<Boolean>(iUserService.signIn(user),HttpStatus.OK);
	}
	/*******************************************************************************
	 * Method                        logoutUser
	 * Description                   It is used to logout from online application
	 * @param user                   user reference variable
	 * @return                       true if user details are correct
	 * @throws UserNotFoundException It raises due to invalid user details
	 *******************************************************************************/
	@PostMapping("/signout")
	public ResponseEntity<Boolean> logoutUser(@RequestBody User user) throws UserNotFoundException{
		return new ResponseEntity<Boolean>(iUserService.signOut(user),HttpStatus.OK);	
	}
	/*******************************************************************************
	 * Method                        updatePassword
	 * Description                   It is used to update the password
	 * @param user                   user reference variable
	 * @return                       It will update the password by checking with old password
	 * @throws UserNotFoundException It raises due to invalid details
	 *******************************************************************************/
	
	@PutMapping("/updatepassword/{newPassword}")
	public ResponseEntity<User> updatePassword(@PathVariable String newPassword,@RequestBody User user) throws UserNotFoundException, PasswordNotMatchException{
		return new ResponseEntity<User>(iUserService.changePassword(newPassword, user),HttpStatus.OK);
	}
	
	@GetMapping("/users/{userName}")
	public User viewUser(@PathVariable String userName) throws UserNotFoundException {
		return iUserService.viewUser(userName);
	}

	/****************************
	 * Method: viewAllUser 
	 * Description: To display all the users
	 * 
	 * @returns List<User> - It returns all the users present in database
	 * @GetMapping: It is used to handle GET type of request method. 
	 * Created By -Shaik Abdul Rahiman 
	 * Created Date - 26-MARCH-2021
	 * 
	 ****************************/
	@GetMapping("/users")
	public List<User> viewAllUser() {
		return iUserService.viewAllUser();
	}

	/****************************
	 * Method: validateUser
	 *  Description: It is used to verify username and password of user
	 * 
	 * @param username: username for validating user.
	 * @param password: password for validating user.
	 * @returns User - it returns user details if the details are correct else it
	 *          throws UserNotFoundException
	 * @throws UserNotFoundException - It is raised due to invalid username or
	 *                               password.
	 * @GetMapping: It is used to is used to handle GET type of request method.
	 * @PathVariable: It is used for data passed in the URI and transfer its values
	 *                to parameter variables. 
	 * Created By - Shaik Abdul Rahiman 
	 * Created Date- 26-MARCH-2021
	 * 
	 ****************************/
	@GetMapping("/getvalidate/{username}/{password}")
	public User validateUser(@PathVariable String username, @PathVariable String password) throws UserNotFoundException {
		return iUserService.validateUser(username, password);
	}

	/****************************
	 * Method: addUser 
	 * Description: It is used to add user into user_details table
	 * 
	 * @param user: User's reference variable.
	 * @returns User It returns user with details
	 * @PostMapping: It is used to handle POST type of request method
	 * @RequestBody: It maps the HttpRequest body to a transfer or domain object
	 *               Created By - Shaik Abdul Rahiman 
	 *               Created Date - 26-MARCH-2021
	 * 
	 ****************************/
	@PostMapping("/users")
	public User addUser(@RequestBody User user) {
		return iUserService.addUser(user);
	}


	/****************************
	 * Method: removeUser 
	 * Description: It is used to remove user
	 * 
	 * @param user: User's reference variable.
	 * @returns User It returns the user that has been deleted
	 * @throws UserNotFoundException: It is raised due to invalid user.
	 * @DeleteMapping: It is used to handle DELETE type of request method.
	 * @RequestBody: It maps the HttpRequest body to a transfer or domain object
	 * Created By - Shaik Abdul Rahiman
	 * Created Date - 26-MARCH-2021
	 * 
	 ****************************/
	@DeleteMapping("/user/{userName}")
	public User removeUser(@PathVariable String userName) {
		return iUserService.removeUser(userName);
	}
}