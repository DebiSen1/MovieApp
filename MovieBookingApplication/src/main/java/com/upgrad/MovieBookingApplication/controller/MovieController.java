package com.upgrad.MovieBookingApplication.controller;

import com.upgrad.MovieBookingApplication.dto.MovieDTO;
import com.upgrad.MovieBookingApplication.entitites.Movie;
import com.upgrad.MovieBookingApplication.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;


@RestController
@RequestMapping(value = {"/movie_app/v1"})
public class MovieController{

    @Autowired
    private MovieService movieService;

    @Autowired
    private ModelMapper modelMapper;

    /*
    * method for creating a movie
    * https://localhost:8080/movie_app/v1/createMovie ---> REST Endpoint
    * */

    @PostMapping(value = {"/createMovie"},consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createMovie(@RequestBody MovieDTO movieDTO){

        //convert MovieDTO to Movie Entity
        Movie newMovie = modelMapper.map(movieDTO,Movie.class);
        Movie savedMovie = movieService.acceptMovieDetails(newMovie);

        MovieDTO savedMovieDTO = modelMapper.map(savedMovie,MovieDTO.class);

        return new ResponseEntity(savedMovieDTO, HttpStatus.CREATED);
        
    }

    /*
     * method for retrieving a movie
     * https://localhost:8080/movie_app/v1/getMovie/{movieId} ---> REST Endpoint
     * */

    @GetMapping(value = {"/getMovie/{movieId}"},produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getMovie(@PathVariable(name = "movieId") int id){
        Movie responseMovie = movieService.getMovieDetailsBasedOnId(id);
        MovieDTO responseMovieDTO = modelMapper.map(responseMovie,MovieDTO.class);

        return new ResponseEntity(responseMovieDTO,HttpStatus.OK);
    }

    /*
     * method for retrieving all movies
     * https://localhost:8080/movie_app/v1/allMovies ---> REST Endpoint
     * */

    @GetMapping(value = {"/allMovies"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllMovies(){
        List<Movie> movieList = movieService.getALlMovieDetails();
        List<MovieDTO> movieDTOList = new LinkedList<>();

        for(Movie movie:movieList){
            movieDTOList.add(modelMapper.map(movie,MovieDTO.class));
        }

        return new ResponseEntity(movieDTOList,HttpStatus.OK);
    }

    /*
     * method for updating a movie
     * https://localhost:8080/movie_app/v1/updateMovie/{movieId} ---> REST Endpoint
     * */

    @PutMapping(value = {"/updateMovie/{movieId}"},produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateMovie(@PathVariable(name = "movieId")int id, @RequestBody MovieDTO movieDTO){
        Movie newMovie = modelMapper.map(movieDTO,Movie.class);
        Movie updatedMovie = movieService.updateMovieDetails(id,newMovie);
        MovieDTO updatedMovieDTO = modelMapper.map(updatedMovie,MovieDTO.class);

        return new ResponseEntity(updatedMovieDTO,HttpStatus.ACCEPTED);
    }

    /*
     * method for updating a movie
     * https://localhost:8080/movie_app/v1/deleteMovie/{movieId} ---> REST Endpoint
     * */

    @DeleteMapping(value = {"/deleteMovie/{movieId}"},produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteMovie(@PathVariable(name = "movieId")int id){
       boolean isDeletedMovie = movieService.deleteMovieDetailsBasedOnId(id);

       if(isDeletedMovie==true){
           System.out.println("Movie is deleted.");
       }
       else{
           System.out.println("Movie is does not exist.");
       }
    }

}
