package com.lufoxt.movieseller.service;

import com.lufoxt.movieseller.entity.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    void registerNewCustomer(CustomerDto customerDto);

    List<CustomerDto> findAllCustomers();

    CustomerDto findCustomerById(Long customerId);
}
