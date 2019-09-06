package com.melkishengue.springbootapp.controllers;

import com.melkishengue.springbootapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.melkishengue.springbootapp.entities.Employee;

import java.util.Optional;

@Controller
@RequestMapping(path="/employees")
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping(path={"/"})
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee e = employeeRepository.save(employee);
        return ResponseEntity.ok().body(e);
    }

    @GetMapping(path="/{id}/")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id) {
        Optional<Employee> ret = employeeRepository.findById(id);
        return ResponseEntity.ok().body(ret.get());
    }

    @GetMapping(path={"/"})
    public ResponseEntity<Iterable<Employee>> getAllEmployees() {
        return ResponseEntity.ok().body(employeeRepository.findAll());
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable int id) {
        Optional<Employee> ret = employeeRepository.findById(id);

        if (ret.isPresent()) {
            employee.setId(id);

            employeeRepository.save(employee);
            return ResponseEntity.ok().body(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path={"/{id}"})
    public ResponseEntity<Object> saveEmployee(@PathVariable("id") int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (!employee.isPresent()) return ResponseEntity.notFound().build();

        employeeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
