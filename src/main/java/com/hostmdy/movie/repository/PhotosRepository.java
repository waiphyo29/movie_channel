package com.hostmdy.movie.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.movie.domain.Movie;
import com.hostmdy.movie.domain.Photos;

public interface PhotosRepository extends CrudRepository<Photos, Long>{
	
	List<Photos> findByMovie(Movie movie);

}
