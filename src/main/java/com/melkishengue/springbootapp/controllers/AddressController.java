package com.melkishengue.springbootapp.controllers;

import com.melkishengue.springbootapp.entities.Address;
import com.melkishengue.springbootapp.entities.Employee;
import com.melkishengue.springbootapp.repositories.AddressRepository;
import com.melkishengue.springbootapp.repositories.EmployeeRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
// @RequestMapping(path="/employees")
public class AddressController {

    private AddressRepository addressRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public AddressController(AddressRepository addressRepository, EmployeeRepository employeeRepository) {
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
    }


    @PostMapping(path={"/employees/{employee_id}/addresses/"})
    public ResponseEntity<Address> saveAddress(@RequestBody Address address, @PathVariable int employee_id) {
        Optional<Employee> ret = employeeRepository.findById(employee_id);
        if (ret.isPresent()) {
            address.setEmployee(ret.get());
            Address a = addressRepository.save(address);
            return ResponseEntity.ok().body(a);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/employees/{employee_id}/addresses/")
    public ResponseEntity<Iterable<Address>> getAllAddresses(@PathVariable int employee_id) {
        Iterable<Address> addresses = addressRepository.findByEmployeeId(employee_id);
        return ResponseEntity.ok().body(addresses);
    }

    @PutMapping(path="/addresses/{address_id}")
    public ResponseEntity<Address> updateAddress(@PathVariable int address_id, @RequestBody Address address) {
        Optional<Address> ret = addressRepository.findById(address_id);
        if (!ret.isPresent()) return ResponseEntity.notFound().build();

        address.setId(address_id);
        addressRepository.save(address);
        return ResponseEntity.ok().body(address);
    }

    @DeleteMapping(path="/addresses/{address_id}/")
    public ResponseEntity<Object> deleteAddress(@PathVariable int address_id) {
        Optional<Address> ret = addressRepository.findById(address_id);

        if (!ret.isPresent()) return ResponseEntity.notFound().build();

        addressRepository.deleteById(ret.get().getId());
        return ResponseEntity.ok().build();
    }
}
