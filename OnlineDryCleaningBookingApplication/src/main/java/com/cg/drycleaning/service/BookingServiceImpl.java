package com.cg.drycleaning.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.drycleaning.beans.Booking;
import com.cg.drycleaning.dao.IBookingRepository;
import com.cg.drycleaning.exception.EmptyEntityListException;
import com.cg.drycleaning.exception.EntityCreationException;
import com.cg.drycleaning.exception.EntityDeletionException;
import com.cg.drycleaning.exception.EntityNotFoundException;
import com.cg.drycleaning.exception.EntityUpdationException;

/*********************************************************************************************
 *         @author       	   D. Manoj Kumar Reddy
 *         Description    	   It is a user service implementation class that defines the methods
 *                             mentioned in its interface.
 *         Version             1.0
 *         Created Date 	   23-MARCH-2021
 ********************************************************************************************/

@Service
@Transactional
public class BookingServiceImpl implements IBookingService {

	@Autowired
	private IBookingRepository bookingRepository;

	/*******************************************************************************************
	 * Method:                          	addBooking
     *Description:                      	To add the booking details
     *@param booking:                       Booking's reference variable.
	 *@returns booking                    - Adds booking data else throws Entity Creation Exception 
	 *@throws  Entity Creation Exception  - It is raised when we cannot create booking details
     *Created By                          - D. Manoj Kumar Reddy
     *Created Date                        - 23-MARCH-2021                           
	 
	 *****************************************************************************************/

	@Override
	public Booking addBooking(Booking booking) {
		try {
			Booking booking1 = bookingRepository.save(booking);
			return booking1;
		} catch (Exception e) {
			throw new EntityCreationException("Failed to Create Booking.");
		}
	}

	/******************************************************************************************
	 * Method:                          	removeBooking
     *Description:                      	To remove the booking details
     *@param booking:                       Booking's reference variable.
	 * @returns booking                  	- Removes booking data else throws Entity Deletion Exception 
	 * @throws  Entity Deletion Exception  	- It is raised when we cannot remove booking details
     *Created By                            - D. Manoj Kumar Reddy
     *Created Date                          - 23-MARCH-2021                           
	 
	 *****************************************************************************************/

	@Override
	public Booking removeBooking(long bookingId) {
		Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
		Booking booking = null;
		if (optionalBooking.isPresent()) {
			booking = optionalBooking.get();
			bookingRepository.deleteById(bookingId);
			return booking;
		} else {
			throw new EntityDeletionException("Booking With Id " + bookingId + " does Not Exist.");
		}

	}

	/*******************************************************************************************
	 * Method:                          	updateBooking
     *Description:                      	To update the booking details
     *@param booking:                       Booking's reference variable.
	 * @returns booking                 	- Updates booking data else throws Entity Updation Exception 
	 * @throws  Entity Updation Exception   - It is raised when we cannot update booking details
     *Created By                            - D. Manoj Kumar Reddy
     *Created Date                          - 23-MARCH-2021                           
	 *******************************************************************************************/

	@Override
	public Booking updateBooking(long bookingId, Booking booking) {
		Optional<Booking> optionalBooking = null;
		Booking booking1 = null;

		optionalBooking = bookingRepository.findById(bookingId);
		if (optionalBooking.isPresent()) {
			booking1 = bookingRepository.save(booking);
			return booking1;
		} else {
			throw new EntityUpdationException("Booking With Id " + bookingId + "is Not Found.");
		}
	}

	/***********************************************************************************
	 * Method:                          getBooking
     *Description:                      To display the booking by bookingId (Primary key)
     *@param bookingId:                 bookingId of the user.
	 * @returns booking              	- if booking with bookingId presents it returns booking else throws EntityNotFoundException
	 * @throws EntityNotFoundException 	-  It is raised due to invalid bookingId 
     *Created By                   		- D. Manoj Kumar Reddy
     *Created Date                 		- 23-MARCH-2021                           
	 
	 ***********************************************************************************/

	@Override
	public Booking getBooking(long bookingId) {
		Booking booking1 = null;

		Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
		if (optionalBooking.isPresent()) {
			booking1 = optionalBooking.get();
			return booking1;
		} else {
			throw new EntityNotFoundException("Booking With Id " + bookingId + " does Not Exist.");
		}
	}

	/*********************************************************************************
	 * Method:                          getAllBookings
     *Description:                      To display all the bookings
	 * @returns List<Booking>           - It returns all the bookings present in database
     *Created By                        - D. Manoj Kumar Reddy
     *Created Date                      - 23-MARCH-2021                           
	 
	 **********************************************************************************/

	@Override
	public List<Booking> getAllBookings() {
		List<Booking> allBookings = new ArrayList<Booking>();
		allBookings = bookingRepository.findAll();
		if (!allBookings.isEmpty()) {
			return allBookings;
		} else {
			throw new EmptyEntityListException("No Bookings Found.");
		}
	}

	/**********************************************************************************
	 * Method:                          getBookingsByDate
     *Description:                      To display the booking by localDate
     *@param localDate:                 localDate of the booking.
	 *@returns List<Booking>              	- if booking with localDate is present then it returns booking else throws EmptyEntityListException
	 * @throws EmptyEntityListException 	-  It is raised due to invalid  localDate 
     *Created By                   		- D. Manoj Kumar Reddy
     *Created Date                 		- 23-MARCH-2021                           
	 
	 ********************************************************************************/

	@Override
	public List<Booking> getBookingsByDate(LocalDate localDate) {
		List<Booking> bookings = new ArrayList<Booking>();

		bookings = bookingRepository.getBookingsByDate(localDate);
		if (!bookings.isEmpty()) {
			return bookings;
		} else {
			throw new EmptyEntityListException("No Bookings Found.");
		}
	}

	/******************************************************************************
	 * Method:                          getBookingsByCustomer
     *Description:                      To display the booking by customerId
     *@param customerId:                customerId of the booking.
	 *@returns List<Booking>            - if booking with customerId is present then it returns booking else throws EmptyEntityListException
	 * @throws EmptyEntityListException -  It is raised due to invalid customerId 
     *Created By                   		- D. Manoj Kumar Reddy
     *Created Date                 		- 23-MARCH-2021                           
	 
	 ***************************************************************************/

	@Override
	public List<Booking> getBookingsByCustomer(String customerId) {
		List<Booking> bookings = new ArrayList<Booking>();

		bookings = bookingRepository.getBookingsByCustomer(customerId);
		if (!bookings.isEmpty()) {
			return bookings;
		} else {
			throw new EmptyEntityListException("No Bookings Found.");
		}
	}

}
