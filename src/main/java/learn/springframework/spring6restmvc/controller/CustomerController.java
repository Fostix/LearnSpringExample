package learn.springframework.spring6restmvc.controller;

import learn.springframework.spring6restmvc.model.Customer;
import learn.springframework.spring6restmvc.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class CustomerController {
    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";
    private final CustomerService customerService;

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity updateCustomerPatchById(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer) {

        customerService.patchCustomerById(customerId, customer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity deleteById(@PathVariable("customerId") UUID customerId) {

        customerService.deleteCustomerById(customerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity updateById(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer) {

        customerService.updateCustomerById(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity handlePost(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveNewCustomer(customer);


        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", CUSTOMER_PATH_ID + "/" + savedCustomer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = CUSTOMER_PATH)
    public List<Customer> listCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(value = CUSTOMER_PATH_ID)
    public Customer getCustomerById(@PathVariable("customerId")UUID customerId) {

        return customerService.getCustomerById(customerId);
    }

}
