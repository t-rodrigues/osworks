package com.thiagorodrigues.osworks.domain.repository;

import com.thiagorodrigues.osworks.domain.model.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {

}
