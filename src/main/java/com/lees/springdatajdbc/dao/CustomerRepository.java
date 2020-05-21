package com.lees.springdatajdbc.dao;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lees.springdatajdbc.entity.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Long> {

	@Query("select id, first_name, last_name, dob from customer where upper(first_name) like upper(:name)")
	List<Customer> findByName(@Param("name") final String name);	
	
}