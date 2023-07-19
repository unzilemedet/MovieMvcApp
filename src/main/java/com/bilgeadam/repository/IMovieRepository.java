package com.bilgeadam.repository;

import com.bilgeadam.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByRatingGreaterThan(double rating);

    List<Movie> findByPremieredBefore(LocalDate premiered);

    //bir ratingden kaç tane olduğunu ve o ratingi beraber getiriniz
    @Query(value = "select rating, count(*) as FilmAdedi from movie where rating=?1 group by rating", nativeQuery = true)
    Object countByIdenticalRating(double rating);

    //bütün filmlerin ratinglerine göre gruplayan metot
    @Query(value = "select rating, count(rating) from movie group by rating", nativeQuery = true)
    Object[] countByRatingGroupByRating();

    //Filmler içerisinde puanları 7, 8, 9 olan filmleri getiriniz
    List<Movie> findByRatingIn(List<Double> ratings);

    //Hazır veri çekildiğinde denenebilir.
    @Query("""
            select m from Movie m
            where upper(m.country) like upper(concat('%', ?1)) and upper(m.name) like upper(concat('%', ?2))""")
    List<Movie> denemeQuery(String country, String name);

    List<Movie> findByNameContainsIgnoreCase(String name);




}
