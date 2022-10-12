package com.lufoxt.movieseller.service.impl;

import com.lufoxt.movieseller.entity.Customer;
import com.lufoxt.movieseller.entity.dto.CustomerDto;
import com.lufoxt.movieseller.exception.CustomerNotFoundException;
import com.lufoxt.movieseller.repository.CustomerRepository;
import com.lufoxt.movieseller.service.CustomerService;
import com.lufoxt.movieseller.util.converter.CustomerConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.lufoxt.movieseller.util.converter.CustomerConverter.convertCustomerDtoToCustomerEntity;
import static com.lufoxt.movieseller.util.converter.CustomerConverter.convertCustomerEntityToCustomerDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void registerNewCustomer(CustomerDto customerDto) {
        Customer savedCustomer = customerRepository.save(
                convertCustomerDtoToCustomerEntity(customerDto, Collections.emptySet(), Collections.emptySet()));

        log.info("Registered new customer with id: {}", savedCustomer.getId());
    }

    @Override
    public List<CustomerDto> findAllCustomers() {
        List<CustomerDto> allCustomers = customerRepository.findAll().stream()
                .map(CustomerConverter::convertCustomerEntityToCustomerDto)
                .collect(Collectors.toList());
        log.info("Found {} customers", allCustomers.size());
        return allCustomers;
    }

    @Override
    public CustomerDto findCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        log.info("Found customer with id: {}", customer.getId());
        return convertCustomerEntityToCustomerDto(customer);
    }


}
