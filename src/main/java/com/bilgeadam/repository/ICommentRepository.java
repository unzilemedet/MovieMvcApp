package com.bilgeadam.repository;

import com.bilgeadam.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByMovieId(Integer id);

    List<Comment> findByMovieIdAndDateBetween(Integer movieId, LocalDate start, LocalDate end);

    List<Comment> findByUserId(Integer uid);
    List<Comment> findAllByUserIdAndDateBetween(Integer uid, LocalDate start,LocalDate end);
}
