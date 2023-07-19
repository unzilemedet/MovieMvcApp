package com.bilgeadam.utility;

import com.bilgeadam.entity.Comment;
import com.bilgeadam.entity.Movie;
import com.bilgeadam.entity.User;
import com.bilgeadam.service.CommentService;
import com.bilgeadam.service.GenreService;
import com.bilgeadam.service.MovieService;
import com.bilgeadam.service.UserService;
import com.bilgeadam.utility.data.Sample;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataImpl implements ApplicationRunner {

    private final MovieService movieService;
    private final GenreService genreService;
    private final CommentService commentService;
    private final UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*getAllMovies();
        createUser();
        setCommentsByMovieId();*/
    }

    public void getAllMovies(){
        try {
            URL url = new URL("https://api.tvmaze.com/shows");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String value = "";
            value = bufferedReader.readLine();
            //Gson --> Json formatın dönüştürülmüş hali
            Sample[] array = new Gson().fromJson(value, Sample[].class);

            Arrays.asList(array).forEach(x -> {
                Movie movie = null;
                if (x.network == null){
                    movie = Movie.builder()
                            .id(x.id)
                            .url(x.url)
                            .image(x.image.medium)
                            .language(x.language)
                            .premiered(LocalDate.parse(x.premiered))
                            .summary(x.summary)
                            .name(x.name)
                            .genreId(genreService.createGenresWithNames(x.genres))
                            .rating(x.rating.average)
                            .build();
                }else {
                    movie = Movie.builder()
                            .id(x.id)
                            .url(x.url)
                            .image(x.image.medium)
                            .language(x.language)
                            .premiered(LocalDate.parse(x.premiered))
                            .summary(x.summary)
                            .name(x.name)
                            .genreId(genreService.createGenresWithNames(x.genres))
                            .rating(x.rating.average)
                            .country(x.network.country.name)
                            .build();
                }
                movieService.save(movie);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUser(){
        //------------------------------User1---------------------------------------
        User user1 = User.builder()
                .name("Arda")
                .surname("Ağdemir")
                .email("arda@mail.com")
                .password("Arda*1234")
                .repassword("Arda*1234")
                .phone("05555555")
                .movieId(List.of(8, 3, 17, 18,9,85,78,127,1,120))
                .genreId(List.of(1,2,5,6))
                .build();
        List<Comment> comment1 = List.of(
            Comment.builder().content("İyi").date(LocalDate.now()).userId(1).movieId(18).build(),
            Comment.builder().content("Kötü").date(LocalDate.now()).userId(1).movieId(127).build(),
            Comment.builder().content("İdare Eder").date(LocalDate.now()).userId(1).movieId(78).build()
        );
        commentService.saveAll(comment1);
        user1.setCommentId(comment1.stream().map(x -> x.getId()).toList());

        //------------------------------User2---------------------------------------
        User user2 = User.builder()
                .name("Ayşe")
                .surname("Fatma")
                .email("ayse@mail.com")
                .password("Ayse*1234")
                .repassword("Ayse*1234")
                .phone("05555555")
                .movieId(List.of(100, 25, 42, 240,155,65))
                .genreId(List.of(5,9,13,18))
                .build();
        List<Comment> comment2 = List.of(
                Comment.builder().content("İyi").date(LocalDate.now()).userId(2).movieId(100).build(),
                Comment.builder().content("Kötü").date(LocalDate.now()).userId(2).movieId(25).build(),
                Comment.builder().content("Berbar").date(LocalDate.now()).userId(2).movieId(65).build()
        );
        commentService.saveAll(comment2);
        user2.setCommentId(comment2.stream().map(x -> x.getId()).toList());

        userService.saveAll(List.of(user1, user2));
    }

    public void setCommentsByMovieId(){
        commentService.findAll().stream().forEach(x -> {
            Movie movie = movieService.findById(x.getMovieId()).get();
            movie.getCommentId().add(x.getId());
            movieService.save(movie);
        });
    }
}
