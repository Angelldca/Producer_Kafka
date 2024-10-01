package com.angelldca.producer.controller;

import com.angelldca.producer.Customer.Customer;
import com.angelldca.producer.service.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class ProducerController {

    private final CustomerService customerService;

    public ProducerController(CustomerService customerService) {

        this.customerService = customerService;
    }
   @PostMapping
    public Customer save(@RequestBody Customer customer){
        return customerService.publish(customer);
    }

    @GetMapping
    public String saludar(){
        return "Hola Producer";}
}
