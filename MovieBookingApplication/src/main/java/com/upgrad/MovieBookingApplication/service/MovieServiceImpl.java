package com.upgrad.MovieBookingApplication.service;

import com.upgrad.MovieBookingApplication.dao.MovieDao;
import com.upgrad.MovieBookingApplication.entitites.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/*
* This service implementation class will implement all the CRUD oprations specified in service interface
*
* @Auhor-Debi
* */

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieDao movieDao;  //to do the DB access or interaction DAO object in autowired here

    @Override
    public Movie acceptMovieDetails(Movie movieToSave) {
        return movieDao.save(movieToSave);  //saving the passed Movie object to the repository
    }

    @Override
    public List<Movie> acceptMultipleMovieDetails(List<Movie> moviesToSave) {

        List<Movie> listOfMoviesToSave = new LinkedList<>();

        for(Movie movie : moviesToSave){
            listOfMoviesToSave.add(movie);
        }

        return listOfMoviesToSave; //return the newly created movies list
    }

    @Override
    public Movie getMovieDetailsBasedOnId(int movieID) {
        Movie movieToFind = movieDao.findById(movieID).get(); //.get() is used to avoid casting to Optional type
        return movieToFind;
    }

    @Override
    public List<Movie> getALlMovieDetails() {
        return movieDao.findAll(); //returns list of all the objects
    }

    @Override
    public Movie updateMovieDetails(int movieIdToBeUpdated, Movie updatedMovie) {
        Movie movieToUpdate = getMovieDetailsBasedOnId(movieIdToBeUpdated); //get the desired movie

        //updating the movie's details with the passed movies's details
        movieToUpdate.setMovieName(updatedMovie.getMovieName());
        movieToUpdate.setCoverPhotoUrl(updatedMovie.getCoverPhotoUrl());
        movieToUpdate.setReleaseDate(updatedMovie.getReleaseDate());
        movieToUpdate.setDuration(updatedMovie.getDuration());
        movieToUpdate.setMovieDescription(updatedMovie.getMovieDescription());
        movieToUpdate.setTrailerUrl(updatedMovie.getTrailerUrl());

        return movieDao.save(movieToUpdate); //save the newly updated movie

    }

    @Override
    public boolean deleteMovieDetailsBasedOnId(int movieIdToBeDeleted) {
        Movie movieToDelete = movieDao.findById(movieIdToBeDeleted).get(); //get the movie you want to delete

        if(movieToDelete==null){
            return false;
        }
        else{
            movieDao.delete(movieToDelete);
            return true;
        }
    }

    @Override
    public Page<Movie> getPaginatedMovieDetails(Pageable pageable) {
        return movieDao.findAll(pageable);
    }
}
