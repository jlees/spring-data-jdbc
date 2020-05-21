package com.lees.springdatajdbc;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.lees.springdatajdbc.config.DatabaseConfig;
import com.lees.springdatajdbc.entity.Customer;
import com.lees.springdatajdbc.service.CustomerService;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfig.class)
public class CustomerServiceTest {

    @Autowired 
    private CustomerService customerService;

    /**
     * This test uses Spring Data JDBC ( https://spring.io/projects/spring-data-jdbc )
     * to create an implementation of the CustomerRepository interface referenced by the
     * CustomerService component to perform CRUD operations on Customer instances.
     * 
     * The CustomerService's findById() and save() methods wrap corresponding methods 
     * defined by CustomerRepository's parent CrudRepository interface.
     * 
     * The CustomerService's findByName() method invokes CustomerRepository's equivalent method 
     * which executes the SELECT SQL statement defined by the method's @Query annotation.
     */
    @Test
    public void testCustomerService() {

	    Customer customer = new Customer();
        customer.setDob(LocalDate.of(1904, 5, 14));
        customer.setFirstName("Anthony");
        customer.setLastName("Stark");

        Customer saved = customerService.save(customer);
        assertThat(saved.getId()).isNotNull();

        String firstName = "Tony";
        saved.setFirstName(firstName);
        customerService.save(saved);

        Optional<Customer> customerById = customerService.findById(saved.getId());
        assertThat(customerById).isNotEmpty();
        assertThat(customerById.get().getFirstName()).isEqualTo(firstName);	
        
        List<Customer> customersByName = customerService.findByName(firstName);
        assertThat(customersByName.size()).isEqualTo(1);
        assertThat(customersByName.get(0).getFirstName()).isEqualTo(firstName);	
    }
}