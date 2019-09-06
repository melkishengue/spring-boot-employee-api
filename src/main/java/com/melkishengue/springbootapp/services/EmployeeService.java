package com.melkishengue.springbootapp.services;

import com.melkishengue.springbootapp.repositories.EmployeeRepository;
import com.melkishengue.springbootapp.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public class EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee createEmployee(Employee e) {
        return employeeRepository.save(e);
    }

    public Optional<Employee> getEmployee(int id) {
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(Employee e, int id) {
        Optional<Employee> ret = employeeRepository.findById(id);

        if (ret.isPresent()) {
            e.setId(ret.get().getId());
            employeeRepository.save(e);
        }

        return employeeRepository.findById(id).get();
    }
}
