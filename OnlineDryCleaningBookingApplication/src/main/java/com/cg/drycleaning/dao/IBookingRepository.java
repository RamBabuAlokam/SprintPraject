package com.cg.drycleaning.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.drycleaning.beans.Booking;

/**********************************************************************************************
 *          @author         D. Manoj Kumar Reddy
 *          Description     IBookingRepository is an interface which extends JPA repository.
 *          				This class belongs to DAO layer. It is used to perform CRUD operations
 *          				with our entities by defining JPA repositories for automatic 
 *          				and intelligent implementations.                     					  
 *         	Version         1.0
 *         	Created Date    23-MARCH-2021
 **********************************************************************************************/

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Long> {

	@Query(value = "SELECT booking FROM Booking booking WHERE booking.customerDetails.userId=:customerId")
	List<Booking> getBookingsByCustomer(@Param(value = "customerId") String customerId);

	@Query(value = "SELECT booking FROM Booking booking WHERE booking.bookingDate=:localDate")
	List<Booking> getBookingsByDate(@Param(value = "localDate") LocalDate localDate);

}