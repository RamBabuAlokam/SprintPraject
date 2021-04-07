package com.cg.drycleaning.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.drycleaning.beans.Booking;
import com.cg.drycleaning.service.IBookingService;

/***********************************************************************************
 *          @author          D. Manoj Kumar Reddy
 *          Description      It is a BookingServiceTest class used to test the methods in 
 *                           Booking Service class.   
 *          Version          1.0
 *          Created Date     22-MARCH-2020
 ***********************************************************************************/

@SpringBootTest
public class BookingServiceTest {

	@Autowired
	private IBookingService iBookingService;

	/*************************************************************
	 * Method:        addBookingTest
     *Description:    To test the addBooking Method of BookingService
     *Created By    - D. Manoj Kumar Reddy
     *Created Date  - 22-MARCH-2021                           
	 
	 ************************************************************/

	@Test
	public void addBookingTest() {
		Booking booking1 = new Booking(1001L, LocalDate.now(), null, "Drycleaning", null);
		assertEquals(1001L, iBookingService.addBooking(booking1).getBookingId());
	}

	/*************************************************************
	 * Method:        removeBookingTest
     *Description:    To test the removeBooking Method of BookingService
     *Created By    - D. Manoj Kumar Reddy
     *Created Date  - 22-MARCH-2021                           
	 
	 *************************************************************/

	@Test
	public void removeBookingTest() {
		Booking booking1 = new Booking(1002L, LocalDate.now(), null, "Drycleaning", null);
		iBookingService.addBooking(booking1);
		assertEquals("Drycleaning", iBookingService.removeBooking(1002L).getServiceType());
	}

	/***********************************************************
	 * Method:        updateBookingTest
     *Description:    To test the updateBooking Method of BookingService
     *Created By    - D. Manoj Kumar Reddy
     *Created Date  - 22-MARCH-2021                           
	 
	 ***********************************************************/

	@Test
	public void updateBookingTest() {
		Booking booking1 = new Booking(1003L, LocalDate.now(), null, "Drycleaning", null);
		iBookingService.addBooking(booking1);
		booking1.setBookingId(1004L);
		assertEquals(1004L, iBookingService.updateBooking(1003L, booking1).getBookingId());
	}

	/**********************************************************
	 * Method:        getBookingTest
     *Description:    To test the getBooking Method of BookingService
     *Created By    - D. Manoj Kumar Reddy
     *Created Date  - 22-MARCH-2021                           
	 
	 **********************************************************/

	@Test
	public void getBookingTest() {
		Booking booking1 = new Booking(1001L, LocalDate.now(), null, "Drycleaning", null);
		iBookingService.addBooking(booking1);
		assertEquals("Drycleaning", iBookingService.getBooking(1001L).getServiceType());
	}

	/**************************************************************
	 * Method:        getAllBookingTest
     *Description:    To test the getAllBooking Method of BookingService
     *Created By    - D. Manoj Kumar Reddy
     *Created Date  - 22-MARCH-2021                           
	 
	 *************************************************************/

	@Test
	public void getAllBookingTest() {
		assertEquals(6, iBookingService.getAllBookings().size());
	}

	/*************************************************************
	 * Method:        getBookingsBydateTest
     *Description:    To test the getBookingsBydate Method of BookingService
     *Created By    - D. Manoj Kumar Reddy
     *Created Date  - 22-MARCH-2021                           
	 
	 ************************************************************/

	@Test
	public void getBookingsByDateTest() {
		Booking booking1 = new Booking(1001L, LocalDate.now(), null, "Drycleaning", null);
		iBookingService.addBooking(booking1);
		assertEquals(1001L, iBookingService.getBookingsByDate(LocalDate.now()).get(0).getBookingId());
	}

	/**************************************************************
	 * Method:        getBookingsByCustomerTest
     *Description:    To test the getBookingsByCustomer Method of BookingService
     *Created By    - D. Manoj Kumar Reddy
     *Created Date  - 22-MARCH-2021                           
	 
	 *************************************************************/

	@Test
	public void getBookingsByCustomerTest() {
		Booking booking1 = new Booking(1001L, LocalDate.now(), null, "Drycleaning", null);
		iBookingService.addBooking(booking1);
		assertEquals("DryClean", iBookingService.getBookingsByCustomer("Santu").get(0).getServiceType());
	}
}
