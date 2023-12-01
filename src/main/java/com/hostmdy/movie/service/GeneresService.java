package com.hostmdy.movie.service;

import java.util.Optional;
import java.util.Set;

import com.hostmdy.movie.domain.Generes;

public interface GeneresService {
	
	Optional<Generes> getGenereById(Long genereId);
	
	Optional<Generes> getGenereByname(String genere);
	
	Set<Generes> getAllGeneres();

}
