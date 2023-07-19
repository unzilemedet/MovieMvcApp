package com.bilgeadam.service;

import com.bilgeadam.dto.response.AllGenresResponseDto;
import com.bilgeadam.entity.Genre;
import com.bilgeadam.repository.IGenreRepository;
import com.bilgeadam.utility.ICrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreService implements ICrudService<Genre, Integer> {

    private final IGenreRepository genreRepository;

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Iterable<Genre> saveAll(Iterable<Genre> t) {
        return genreRepository.saveAll(t);
    }

    @Override
    public Genre update(Genre genre) {
        return genreRepository.saveAndFlush(genre);
    }

    @Override
    public Genre delete(Integer integer) {
        return null;
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findById(Integer id) {
        return genreRepository.findById(id);
    }

    //DataImpl için gelen String genre değerinin Integer' a dönüştürülmesi
    public List<Integer> createGenresWithNames(List<String> genres) { //Drama Anime Action
        List<Integer> genreList=new ArrayList<>();
        for (String name:genres){
            Optional<Genre> genre=genreRepository.findOptionalByName(name);
            if (genre.isPresent()){
                genreList.add(genre.get().getId());
            }else{
                Genre myGenre= Genre.builder().name(name).build();
                save(myGenre);
                genreList.add(myGenre.getId());
            }
        }
        return genreList;
    }

    public String getGenreName(Integer id){
        return findById(id).get().getName();
    }
}
