package com.hostmdy.movie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.movie.domain.ReleaseYear;

public interface ReleaseYearRepository extends CrudRepository<ReleaseYear, Long>{
	
	Optional<ReleaseYear> findByReleasedYear(Integer releasedYear);
	
	List<ReleaseYear> findAllByOrderByReleasedYearDesc();

	
}
