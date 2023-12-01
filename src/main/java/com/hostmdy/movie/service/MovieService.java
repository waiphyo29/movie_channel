package com.hostmdy.movie.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.hostmdy.movie.domain.Generes;
import com.hostmdy.movie.domain.Movie;
import com.hostmdy.movie.domain.ReleaseYear;

public interface MovieService {
	
	Movie saveMovie(Movie movie);
	
	Movie createMovie(Movie movie, Set<Generes> generes, ReleaseYear year);
	
	List<Movie> getAllMovies();
	
	List<Movie> getAllMoviesByOrder();
	
	List<Movie> getMoviesOf2023();
	
	List<Movie> getMoviesByReleaseYear(Long releaseYearId);
	
	List<Movie> getMoviesBygenere(Long genereId);
	
	Optional<Movie> getMovieById(Long movieId);
	
	Movie getMovieByTitle(String title);
	
	List<Movie> getOnlyMovie();
	
	List<Movie> getOnlySerie();
	
	void deleteMovieById(Long movieId);

}
