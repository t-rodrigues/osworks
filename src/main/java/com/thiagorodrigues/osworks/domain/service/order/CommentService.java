package com.thiagorodrigues.osworks.domain.service.order;

import java.time.OffsetDateTime;

import com.thiagorodrigues.osworks.domain.exception.EntityNotFound;
import com.thiagorodrigues.osworks.domain.model.Comment;
import com.thiagorodrigues.osworks.domain.model.OrderService;
import com.thiagorodrigues.osworks.domain.repository.CommentsRepository;
import com.thiagorodrigues.osworks.domain.repository.OrderServiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private OrderServiceRepository orderServiceRepository;

    public Comment addComment(Long orderServiceId, String description) {
        OrderService orderService = orderServiceRepository.findById(orderServiceId)
                        .orElseThrow(() -> new EntityNotFound("Order not found"));

        System.out.println(orderServiceId);
        Comment comment = new Comment();
        comment.setDescription(description);
        comment.setOrderService(orderService);
        comment.setDate(OffsetDateTime.now());

        return commentsRepository.save(comment);
    }

}
