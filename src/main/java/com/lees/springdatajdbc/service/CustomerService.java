package com.lees.springdatajdbc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.lees.springdatajdbc.dao.CustomerRepository;
import com.lees.springdatajdbc.entity.Customer;


@Component
@Transactional
public class CustomerService {

	@Autowired 
	private CustomerRepository customerRepository;
	
	public Optional<Customer> findById(final Long id){
		return customerRepository.findById(id);
	}
	
	public List<Customer> findByName(final String name){
		if (StringUtils.isEmpty(name)) {
			return new ArrayList<>();
		}
		return customerRepository.findByName(name);
	}
	
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}
	
}