package ch.basler.playground.spring.data.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ch.basler.playground.spring.data.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

  List<Customer> findByLastName(String lastName);

  Customer findById(long id);
}