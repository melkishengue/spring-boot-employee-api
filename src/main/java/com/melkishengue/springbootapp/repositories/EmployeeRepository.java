package com.melkishengue.springbootapp.repositories;

import com.melkishengue.springbootapp.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


// This will be AUTO IMPLEMENTED by Spring into a Bean called EmployeeRepository
// CRUD refers Create, Read, Update, Delete

@Component
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}