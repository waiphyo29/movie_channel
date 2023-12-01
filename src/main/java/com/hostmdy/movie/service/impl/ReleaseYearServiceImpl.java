package com.hostmdy.movie.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.movie.domain.ReleaseYear;
import com.hostmdy.movie.repository.ReleaseYearRepository;
import com.hostmdy.movie.service.ReleaseYearService;
@Service
public class ReleaseYearServiceImpl implements ReleaseYearService{
	
	private final ReleaseYearRepository releaseYearRepository;

	public ReleaseYearServiceImpl(ReleaseYearRepository releaseYearRepository) {
		super();
		this.releaseYearRepository = releaseYearRepository;
	}

	@Override
	public Optional<ReleaseYear> getReleaseYearById(Long yearId) {
		// TODO Auto-generated method stub
		return releaseYearRepository.findById(yearId);
	}

	@Override
	public List<ReleaseYear> getAllReleaseYear() {
		// TODO Auto-generated method stub
		return releaseYearRepository.findAllByOrderByReleasedYearDesc();
	}

	@Override
	public Optional<ReleaseYear> getReleaseYearByReleasedYear(int releasedYear) {
		// TODO Auto-generated method stub
		return releaseYearRepository.findByReleasedYear(releasedYear);
	}

}
