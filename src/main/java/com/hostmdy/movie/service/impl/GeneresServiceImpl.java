package com.hostmdy.movie.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hostmdy.movie.domain.Generes;
import com.hostmdy.movie.repository.GeneresRepository;
import com.hostmdy.movie.service.GeneresService;

@Service
public class GeneresServiceImpl implements GeneresService{
	
	private final GeneresRepository generesRepository;

	public GeneresServiceImpl(GeneresRepository generesRepository) {
		super();
		this.generesRepository = generesRepository;
	}

	@Override
	public Optional<Generes> getGenereById(Long genereId) {
		// TODO Auto-generated method stub
		return generesRepository.findById(genereId);
	}

	@Override
	public Optional<Generes> getGenereByname(String genere) {
		// TODO Auto-generated method stub
		return generesRepository.findByGenere(genere);
	}

	@Override
	public Set<Generes> getAllGeneres() {
		// TODO Auto-generated method stub
		return generesRepository.findAllByOrderByGenere();
	}

}
