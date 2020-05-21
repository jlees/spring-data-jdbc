This project provides an example of using the Spring Data JDBC sub-project ( https://spring.io/projects/spring-data-jdbc ) to perform CRUD operations on Customer instances.

The Spring Data JDBC sub-project is a simple, limited, opinionated ORM that aims at being conceptually easy compared to the Spring Data JPA sub-project.

In the project's src/main/java sub-folder, the com.lees.springdatajdbc.config.DatabaseConfig class creates Spring managed beans at startup that must be registered for Spring Data JDBC and an in-memory H2 database that includes a Customer table.

Instances of the project's com.lees.springdatajdbc.entity.Customer POJO class map to records in the Customer table in the in-memory H2 database.

The com.lees.springdatajdbc.service.CustomerService component defines findById(), findByName() and save() methods that wrap and invoke corresponding methods from its CustomerRepository reference.

The com.lees.springdatajdbc.dao.CustomerRepository interface extends Spring Data JDBC's org.springframework.data.repository.CrudRepository interface for the Customer entity class.

CustomerRepository gets its findById() and save() methods from its parent CrudRepository interface. CustomerRepository directly defines the findByName() method, decorating it with a @Query annotation that determines the SELECT SQL statement that is executed in the Spring Data JDBC proxy's implementation of the method.

In the project's src/test/java sub-folder, the com.lees.springdatajdbc.CustomerServiceTest class has a testCustomerService() method that builds the in-memory H2 database and then creates, updates and selects Customer entity instances using the CustomerService and CustomerRepository components.