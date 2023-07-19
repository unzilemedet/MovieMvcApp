package com.bilgeadam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String language;
    private String image;
    private String name;
    private String country;
    private double rating;
    @Lob
    private String summary;
    private LocalDate premiered;
    private String url;
    @ElementCollection //ManyToMany
    private List<Integer> genreId;
    @ElementCollection(fetch = FetchType.EAGER) //OneToMany
    private List<Integer> commentId;
}