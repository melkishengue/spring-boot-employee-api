package com.melkishengue.springbootapp.repositories;

import com.melkishengue.springbootapp.entities.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface AddressRepository extends CrudRepository<Address, Integer> {
    Iterable<Address> findByEmployeeId(int employee_id);
}