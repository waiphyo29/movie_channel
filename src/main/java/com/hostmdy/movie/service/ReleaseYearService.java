package com.hostmdy.movie.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.movie.domain.ReleaseYear;

public interface ReleaseYearService {
	
	Optional<ReleaseYear> getReleaseYearById(Long yearId);
	
	Optional<ReleaseYear> getReleaseYearByReleasedYear(int releasedYear);
	
	List<ReleaseYear> getAllReleaseYear();

}
