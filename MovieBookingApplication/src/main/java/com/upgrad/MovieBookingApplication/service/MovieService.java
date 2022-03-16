package com.upgrad.MovieBookingApplication.service;

/*
* service class contains all the business logic, interacts with controller and DAO layer for data access and
* manipulation.
*
* @Author - Debi
* */

import com.upgrad.MovieBookingApplication.entitites.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface MovieService {

    //all the unimplemented methods needed for CRUD operations are here!

    public Movie acceptMovieDetails(Movie movie); //CREATE

    public List<Movie> acceptMultipleMovieDetails(List<Movie> movies); //CREATE (BULK)

    public Movie getMovieDetailsBasedOnId(int movieID); //GET

    public List<Movie> getALlMovieDetails(); //GET

    public Movie updateMovieDetails(int movieIdToBeUpdated, Movie movie); //UPDATE

    public boolean deleteMovieDetailsBasedOnId(int movieIdToBeDeleted); //DELETE

    public Page<Movie> getPaginatedMovieDetails(Pageable pageable);

}
