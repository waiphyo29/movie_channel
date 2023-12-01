package com.hostmdy.movie.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.movie.domain.Generes;
import com.hostmdy.movie.domain.Movie;
import com.hostmdy.movie.domain.ReleaseYear;

public interface MovieRepository extends CrudRepository<Movie, Long>{

	Movie findByTitle(String title);
	
	List<Movie> findByReleaseYear(ReleaseYear releaseYear);
	
	List<Movie> findByGeneres(Generes genere);
	
	List<Movie> findAllByOrderByReleaseDateDesc();
	
}
