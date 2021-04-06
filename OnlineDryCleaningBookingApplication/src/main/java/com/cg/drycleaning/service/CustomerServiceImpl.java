package com.cg.drycleaning.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.drycleaning.beans.Customer;
import com.cg.drycleaning.dao.ICustomerRepository;
import com.cg.drycleaning.exception.EmptyEntityListException;
import com.cg.drycleaning.exception.EntityCreationException;
import com.cg.drycleaning.exception.EntityDeletionException;
import com.cg.drycleaning.exception.EntityNotFoundException;
import com.cg.drycleaning.exception.EntityUpdationException;
import com.cg.drycleaning.validators.InputValidator;

/************************************************************************************
 *          @author          Ram Babu Alokam.
 *          Description      It is a service class that implements ICustomerServices Interface and provides the services for adding a new Customer, 
                             deleting Customer,updating Customer and viewing all the Customers.  
 *          Version          1.0
 *          Created Date     25-MARCH-2021
 ************************************************************************************/

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepository;
	@Autowired
	private InputValidator inputValidator;

	/*************************************************************************************
	 *Method:                           addCustomer
     *Description:                      To add the details Customer
     *@param customer                 - Customer reference variable
	 *@returns Customer               - It returns the Customer
	 *@throws EntityCreationException - It is raised due failure in Creation of customer.
     *Created By                      - Ram Babu Alokam
     *Created Date                    - 25-MARCH-2021                           
	 
	 *************************************************************************************/
	@Override
	public Customer addCustomer(Customer customer) {

		if (!inputValidator.nameValidator(customer.getName()))
			throw new EntityCreationException("Enter a Valid Customer Name.");
		if (!inputValidator.userIdValidator(customer.getUserId()))
			throw new EntityCreationException("Enter a Valid UserId.");
		if (!inputValidator.contactValidator(customer.getContactNo()))
			throw new EntityCreationException("Enter a valid Contact Number.");
		if (!inputValidator.emailValidator(customer.getEmail()))
			throw new EntityCreationException("Enter a valid Email.");
		Customer customer2 = customerRepository.save(customer);
		return customer2;

	}

	/*************************************************************************************
	 *Method:                           removeCustomer
     *Description:                      To remove the details Customer
     *@param custId                   - Customer Id
	 *@returns Customer               - It returns the Customer
	 *@throws EntityDeletionException - It is raised due failure in deletion of customer.
     *Created By                      - Ram Babu Alokam
     *Created Date                    - 25-MARCH-2021                           
	 
	 *************************************************************************************/

	@Override
	public Customer removeCustomer(String custId) {

		Optional<Customer> optionalCustomer = customerRepository.findById(custId);
		Customer customer = null;
		if (optionalCustomer.isPresent()) {
			customer = optionalCustomer.get();
			customerRepository.deleteById(custId);
			return customer;
		} else {
			throw new EntityDeletionException("Customer With Id " + custId + " does Not Exist.");
		}

	}

	/*************************************************************************************
	 *Method:                           updateCustomer
     *Description:                      To update the details Customer
     *@param customer                 - Customer reference variable
	 *@returns Customer               - It returns the Customer with updated details
	 *@throws EntityUpdationException - It is raised due failure in updation of customer.
     *Created By                      - Ram Babu Alokam
     *Created Date                    - 25-MARCH-2021                           
	 
	 *************************************************************************************/

	@Override
	public Customer updateCustomer(String custId, Customer customer) {
		Optional<Customer> optionalCustomer = null;
		Customer customer2 = null;
		optionalCustomer = customerRepository.findById(custId);
		if (optionalCustomer.isPresent()) {
			customer2 = customerRepository.save(customer);
			return customer2;
		} else {
			throw new EntityUpdationException("Customer With Id " + custId + " does Not Exist.");
		}

	}

	/*************************************************************************************
	 *Method:                           getCustomer
     *Description:                      To display the Customer by using id of the customer
     *@param custId                   - Customer Id
	 *@returns Customer               - It returns the Customer with given custId
	 *@throws EntityUpdationException - It is raised because customer with given id does not exist.
     *Created By                      - Ram Babu Alokam
     *Created Date                    - 25-MARCH-2021                           
	 
	 *************************************************************************************/

	@Override
	public Customer getCustomer(String custId) {
		Optional<Customer> optionalCustomer = null;
		Customer customer = null;
		optionalCustomer = customerRepository.findById(custId);
		if (optionalCustomer.isPresent()) {
			customer = optionalCustomer.get();
			return customer;
		} else {
			throw new EntityNotFoundException("Customer With Id " + custId + " does Not Exist.");
		}

	}

	/*************************************************************************************
	 *Method:                             getAllCustomers
     *Description:                        To display all the Customers
	 *@returns List<Customer>           - It returns all the Customers present in database
	 *@throws EmptyEntityListException  - It is raised due to no Customers available.
     *Created By                        - Ram Babu Alokam
     *Created Date                      - 25-MARCH-2021                           
	 
	 *************************************************************************************/
	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> allCustomers = new ArrayList<Customer>();
		allCustomers = customerRepository.findAll();
		if (!allCustomers.isEmpty()) {
			return allCustomers;
		} else {
			throw new EmptyEntityListException("No Customers Found.");
		}

	}

}
