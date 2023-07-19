package com.bilgeadam.repository;

import com.bilgeadam.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IGenreRepository extends JpaRepository<Genre, Integer> {
    Optional<Genre> findOptionalByName(String name);
}
