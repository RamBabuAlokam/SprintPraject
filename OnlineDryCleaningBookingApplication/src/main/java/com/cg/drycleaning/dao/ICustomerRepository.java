package com.cg.drycleaning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.drycleaning.beans.Customer;

/************************************************************************************
 *         @author          Ram Babu Alokam.
 *         Description      It is a Customer Repository that extends JPA Repository for performing
 *                          CRUD operations  
 *         Version          1.0
 *         Created Date     24-MARCH-2021
 ************************************************************************************/

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, String> {

}