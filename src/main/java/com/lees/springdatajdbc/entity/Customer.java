package com.lees.springdatajdbc.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class Customer {

    @Id
    private Long id;
    
    private String firstName;
    
    private String lastName;
    
    private LocalDate dob;

}