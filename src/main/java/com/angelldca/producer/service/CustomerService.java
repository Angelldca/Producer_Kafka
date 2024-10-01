package com.angelldca.producer.service;


import com.angelldca.producer.Customer.Customer;
import com.angelldca.producer.events.CustomerCreatedEvent;
import com.angelldca.producer.events.Event;
import com.angelldca.producer.events.EventType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class CustomerService {

    private final KafkaTemplate<String, Event<?>> producer;
    @Value("${TOPIC_CUSTOMER_NAME:customers}")
    public String topicCustomer;

    public CustomerService(KafkaTemplate<String, Event<?>> producer) {
        super();
        this.producer = producer;
    }
    public Customer publish(Customer customer){
        CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent();
        customerCreatedEvent.setType(EventType.CREATED);
        customerCreatedEvent.setData(customer);
        customerCreatedEvent.setId(UUID.randomUUID().toString());
        customerCreatedEvent.setDate(new Date());

        this.producer.send(topicCustomer,customerCreatedEvent);

        return customer;
    }


}
