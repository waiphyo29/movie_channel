package com.hostmdy.movie.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.movie.domain.Viewer;

public interface ViewerRepository extends CrudRepository<Viewer, Long>{
	
	Optional<Viewer> findByName(String name);
	
	Optional<Viewer> findByEmail(String email);
	
	Optional<Viewer> findByPassword(String password);

}
