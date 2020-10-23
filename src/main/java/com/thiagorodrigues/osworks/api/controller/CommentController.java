package com.thiagorodrigues.osworks.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.thiagorodrigues.osworks.api.model.AddComment;
import com.thiagorodrigues.osworks.api.model.CommentModel;
import com.thiagorodrigues.osworks.domain.exception.EntityNotFound;
import com.thiagorodrigues.osworks.domain.model.Comment;
import com.thiagorodrigues.osworks.domain.model.OrderService;
import com.thiagorodrigues.osworks.domain.repository.OrderServiceRepository;
import com.thiagorodrigues.osworks.domain.service.order.CommentService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders-service/{orderServiceId}/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private OrderServiceRepository orderServiceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<CommentModel> list(@PathVariable Long orderServiceId) {
        OrderService orderService = orderServiceRepository.findById(orderServiceId)
            .orElseThrow(() -> new EntityNotFound("Order not found."));

        return toCollectionModel(orderService.getComments());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentModel addComment (@PathVariable Long orderServiceId,
            @Valid @RequestBody AddComment addComment) {
        Comment comment = commentService.addComment(orderServiceId, addComment.getDescription());

        return toModel(comment);
    }

    private CommentModel toModel(Comment comment) {
        return modelMapper.map(comment, CommentModel.class);
    }

    private List<CommentModel> toCollectionModel(List<Comment> comments) {
        return comments.stream()
                .map(comment -> toModel(comment))
                .collect(Collectors.toList());
    }
}
