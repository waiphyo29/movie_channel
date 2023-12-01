package com.hostmdy.movie.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.movie.domain.Episodes;
import com.hostmdy.movie.domain.Movie;

public interface EpisodesRepository extends CrudRepository<Episodes, Long>{
	
	List<Episodes> findBymovie(Movie movie);

}
