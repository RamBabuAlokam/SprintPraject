package com.cg.drycleaning.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.drycleaning.beans.Customer;
import com.cg.drycleaning.service.ICustomerService;

/************************************************************************************
 *          @author          Ram Babu Alokam.
 *          Description      It is a Controller class that provides the Restful End points and controls 
                             data flow.  
 *          Version          1.0
 *          Created Date     26-MARCH-2021
 ************************************************************************************/
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/v1")
public class CustomerRest {

	@Autowired
	private ICustomerService iCustomerService;
	/***************************************************************************************
	 * Method: 					addCustomer
	 * Description: 			It is used to add Customer into Customer table 
	 * @param customer: 		customer reference variable.
	 * @returns Customer        It returns Customer with details
	 * @PostMapping:            It is used to handle POST type of request method
	 * @RequestBody:            It maps the HttpRequest body to a transfer or domain object
	 * Created By               Ram Babu Alokam
	 * Created Date             26-MARCH-2021
	 * 
	 ***************************************************************************************/

	@PostMapping("/customer")
	public Customer addCustomer(@RequestBody Customer customer) {
		Customer customer2 = null;
		customer2 = this.iCustomerService.addCustomer(customer);
		return customer2;
	}

	/***************************************************************************************
	 * Method: 					    getAllCustomers
	 * Description: 			    It is used to get all Customers from Customer table 
	 * @param custId: 		        custId of Customer
	 * @returns List<FlatBookings > It returns all the FlatBookings  present in database
	 * @GetMapping:                 It is used to handle GET type of request method.
	 * Created By                   Ram Babu Alokam
	 * Created Date                 26-MARCH-2021
	 * 
	 ***************************************************************************************/

	@GetMapping("/customer")
	public List<Customer> getAllCustomers() {
		List<Customer> customers = this.iCustomerService.getAllCustomers();
		return customers;
	}
	
	/***************************************************************************************
	 * Method: 					getCustomer
	 * Description: 			It is used to get Customer from Customer table 
	 * @param custId: 		    custId of Customer
	 * @returns Customer        It returns Customer with details
	 * @GetMapping:             It is used to handle GET type of request method.
	 * @PathVariable:           It is used for data passed in the URI and transfer its values
	 * Created By               Ram Babu Alokam
	 * Created Date             26-MARCH-2021
	 * 
	 ***************************************************************************************/


	@GetMapping("/customer/{id}")
	public Customer getCustomer(@PathVariable("id") String custId) {
		Customer customer = this.iCustomerService.getCustomer(custId);
		return customer;
	}

	/***************************************************************************************
	 * Method: 					removeCustomer
	 * Description: 			It is used to remove Customer from Customer table 
	 * @param custId: 		    custId of Customer
	 * @returns Customer        It returns Customer with details
	 * @DeleteMapping:          It is used to handle DELETE type of request method.
	 * @RequestBody:            It maps the HttpRequest body to a transfer or domain object
	 * Created By               Ram Babu Alokam
	 * Created Date             26-MARCH-2021
	 * 
	 ***************************************************************************************/

	@DeleteMapping("/customer/{id}")
	public Customer removeCustomer(@PathVariable("id") String custId) {
		Customer customer = this.iCustomerService.removeCustomer(custId);
		return customer;
	}

	/***************************************************************************************
	 * Method: 					updateCustomer
	 * Description: 			It is used to update Customer into Customer table 
	 * @param custId: 		    custId of Customer
	 * @returns Customer        It returns Customer with details
	 * @DeleteMapping:          It is used to handle DELETE type of request method.
	 * @RequestBody:            It maps the HttpRequest body to a transfer or domain object
	 * Created By               Ram Babu Alokam
	 * Created Date             26-MARCH-2021
	 * 
	 ***************************************************************************************/

	@PutMapping("/customer")
	public Customer updateCustomer(@RequestBody Customer customer) {
		Customer customer2 = this.iCustomerService.updateCustomer(customer);
		return customer2;
	}

}
