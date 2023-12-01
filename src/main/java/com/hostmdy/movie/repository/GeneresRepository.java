package com.hostmdy.movie.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.movie.domain.Generes;

public interface GeneresRepository extends CrudRepository<Generes, Long>{
	
	Optional<Generes> findByGenere(String genere);
	
	Set<Generes> findAll();
	
	Set<Generes> findAllByOrderByGenere();

}
