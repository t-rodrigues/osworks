package com.thiagorodrigues.osworks.domain.repository;

import com.thiagorodrigues.osworks.domain.model.OrderService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderServiceRepository extends JpaRepository<OrderService, Long> {

}
