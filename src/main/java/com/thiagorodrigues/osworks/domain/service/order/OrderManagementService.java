package com.thiagorodrigues.osworks.domain.service.order;

import java.time.OffsetDateTime;

import com.thiagorodrigues.osworks.domain.exception.DomainException;
import com.thiagorodrigues.osworks.domain.exception.EntityNotFound;
import com.thiagorodrigues.osworks.domain.model.Client;
import com.thiagorodrigues.osworks.domain.model.OrderService;
import com.thiagorodrigues.osworks.domain.model.StatusOrderService;
import com.thiagorodrigues.osworks.domain.repository.ClientRepository;
import com.thiagorodrigues.osworks.domain.repository.OrderServiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderManagementService {

    @Autowired
    private OrderServiceRepository orderServiceRepository;

    @Autowired
    private ClientRepository clientRepository;

    public OrderService add(OrderService orderService) {
        Client client = clientRepository.findById(orderService.getClient().getId())
                .orElseThrow(() -> new DomainException("Client not found!"));

        orderService.setClient(client);
        orderService.setStatus(StatusOrderService.OPEN);
        orderService.setOpenDate(OffsetDateTime.now());

        return orderServiceRepository.save(orderService);
    }

    public OrderService finishOrder(Long orderServiceId) {
        OrderService orderService = orderServiceRepository.findById(orderServiceId)
                .orElseThrow(() -> new EntityNotFound("Order not found"));

        orderService.endOrderService();

        return orderServiceRepository.save(orderService);
    }

}
