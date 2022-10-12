package com.lufoxt.movieseller.controller;

import com.lufoxt.movieseller.entity.dto.CustomerDto;
import com.lufoxt.movieseller.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = customerService.findAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") long id) {
        CustomerDto customer = customerService.findCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> registerNewCustomer(@RequestBody CustomerDto customerDto) {
        customerService.registerNewCustomer(customerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
