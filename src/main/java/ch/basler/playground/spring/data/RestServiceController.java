package ch.basler.playground.spring.data;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.basler.playground.spring.data.dao.CustomerRepository;
import ch.basler.playground.spring.data.model.Customer;

@RestController
@RequestMapping(value = "/springdata")
public class RestServiceController {
  private static final Logger LOG = LoggerFactory.getLogger(RestServiceController.class);

  private CustomerRepository repository;

  @Autowired
  RestServiceController(CustomerRepository repository) {
    this.repository = repository;
  }

  @RequestMapping("/insertInto")
  public String insertInto() {
    String resultMessage = null;
    Iterable<Customer> allCustomers = repository.findAll();
    if (allCustomers.iterator().hasNext()) {
      resultMessage = "Customers already exist";
    } else {
      // save a few customers
      repository.save(new Customer("Jack", "Bauer"));
      repository.save(new Customer("Chloe", "O'Brian"));
      repository.save(new Customer("Kim", "Bauer"));
      repository.save(new Customer("David", "Palmer"));
      repository.save(new Customer("Michelle", "Dessler"));
      resultMessage = "inserted five customers";
    }
    LOG.info(resultMessage);
    return resultMessage;
  }

  @RequestMapping("/findAll")
  public String findAll() {
    String resultMessage = StreamSupport.stream(repository.findAll().spliterator(), false)//
        .map(c -> c.toString())//
        .collect(Collectors.joining("<br>"));

    LOG.info(resultMessage);
    return resultMessage;
  }

  @RequestMapping("/findById")
  public String findById(Long id) {
    Optional<Customer> customer = repository.findById(id);

    LOG.info(customer.toString());
    return customer.toString();
  }

  @RequestMapping("/findByLastName")
  public String findByLastName(String lastName) {
    String resultMessage = StreamSupport.stream(repository.findByLastName(lastName).spliterator(), false)//
        .map(c -> c.toString())//
        .collect(Collectors.joining("\n"));

    LOG.info(resultMessage);
    return resultMessage;
  }

}
