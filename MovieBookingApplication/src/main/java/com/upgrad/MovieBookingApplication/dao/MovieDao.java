package com.upgrad.MovieBookingApplication.dao;
/*
* This DAO layer will be used to talk to the database.
* It is a part of layered architecture that holds the logic of the database
* */


import com.upgrad.MovieBookingApplication.entitites.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDao extends JpaRepository<Movie,Integer> {
    //JpaRepository has all the functionalities for basic CRUD operations

    /*
    JpaRepository takes the Type of Entity(Movie.class) and type of the primary key (movieID is an integer)
    * */
}
