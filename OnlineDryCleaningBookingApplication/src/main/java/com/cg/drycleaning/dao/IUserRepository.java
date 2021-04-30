package com.cg.drycleaning.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.drycleaning.beans.User;
/**********
 *          @author          Shaik Abdul Rahiman
 *          Description      It is a user repository interface that extends jpa repository 
 *                           that contains inbuilt methods for various operations
 *          Version             1.0
 *          Created Date     24-MARCH-2021
 **********/
@Repository
public interface IUserRepository extends JpaRepository<User, String> {
	Optional<User> findByuserName(String name);
}
