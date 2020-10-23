package com.thiagorodrigues.osworks.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.thiagorodrigues.osworks.api.model.AddOrderService;
import com.thiagorodrigues.osworks.api.model.OrderServiceModel;
import com.thiagorodrigues.osworks.domain.model.OrderService;
import com.thiagorodrigues.osworks.domain.repository.OrderServiceRepository;
import com.thiagorodrigues.osworks.domain.service.order.OrderManagementService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders-service")
public class OrderServiceController {

    @Autowired
    private OrderManagementService orderManagementService;

    @Autowired
    private OrderServiceRepository orderServiceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderServiceModel add(@Valid @RequestBody AddOrderService addOrderService) {
        OrderService orderService = toEntity(addOrderService);
        return toModel(orderManagementService.add(orderService));
    }

    @GetMapping
    public List<OrderServiceModel> list() {
        return toCollectionModel(orderServiceRepository.findAll());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderServiceModel> find(@PathVariable Long orderId) {
        Optional<OrderService> order = orderServiceRepository.findById(orderId);

        if(order.isPresent()) {
            OrderServiceModel orderModel = toModel(order.get());
            return ResponseEntity.ok(orderModel);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{orderServiceId}/close")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finishOrder(@PathVariable Long orderServiceId) {
        orderManagementService.finishOrder(orderServiceId);
    }

    private OrderServiceModel toModel(OrderService orderService) {
        return modelMapper.map(orderService, OrderServiceModel.class);
    }

    private List<OrderServiceModel> toCollectionModel(List<OrderService> ordersService) {
        return ordersService.stream()
                .map(orderService -> toModel(orderService))
                .collect(Collectors.toList());
    }

    private OrderService toEntity(AddOrderService addOrderService) {
        return modelMapper.map(addOrderService, OrderService.class);
    }
}
