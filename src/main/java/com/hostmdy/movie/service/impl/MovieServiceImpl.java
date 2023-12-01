package com.hostmdy.movie.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hostmdy.movie.domain.Generes;
import com.hostmdy.movie.domain.Movie;
import com.hostmdy.movie.domain.ReleaseYear;
import com.hostmdy.movie.domain.Type;
import com.hostmdy.movie.repository.GeneresRepository;
import com.hostmdy.movie.repository.MovieRepository;
import com.hostmdy.movie.repository.ReleaseYearRepository;
import com.hostmdy.movie.service.MovieService;
@Service
public class MovieServiceImpl implements MovieService{
	
	private final MovieRepository movieRepository;
	private final ReleaseYearRepository releaseYearRepository;
	private final GeneresRepository generesRepository;

	public MovieServiceImpl(MovieRepository movieRepository, ReleaseYearRepository releaseYearRepository, GeneresRepository generesRepository) {
		super();
		this.movieRepository = movieRepository;
		this.releaseYearRepository = releaseYearRepository;
		this.generesRepository = generesRepository;
	}

	@Override
	public Movie saveMovie(Movie movie) {
		// TODO Auto-generated method stub
		return movieRepository.save(movie);
	}

	@Override
	public Movie createMovie(Movie movie, Set<Generes> generes, ReleaseYear year) {
		// TODO Auto-generated method stub
		movie.setGeneres(generes);
		if(releaseYearRepository.findByReleasedYear(year.getReleasedYear()).isEmpty()) {
			ReleaseYear releaseYear = new ReleaseYear();
			releaseYear.setReleasedYear(year.getReleasedYear());
			releaseYearRepository.save(releaseYear);
		}
		movie.setReleaseYear(year);
		if(movie.getView() == null) {
			movie.setView(0);
		}
		return saveMovie(movie);
	}

	@Override
	public List<Movie> getAllMovies() {
		// TODO Auto-generated method stub
		List<Movie> movies = (List<Movie>) movieRepository.findAll();
		Collections.shuffle(movies);
		return movies;
	}

	@Override
	public List<Movie> getAllMoviesByOrder() {
		// TODO Auto-generated method stub
		List<Movie> movies = movieRepository.findAllByOrderByReleaseDateDesc().stream()
				.sorted((m1,m2) -> (m1.getReleaseYear().getReleasedYear() > m2.getReleaseYear().getReleasedYear())? -1 : 1).toList();
		return movies;
	}

	@Override
	public Optional<Movie> getMovieById(Long movieId) {
		// TODO Auto-generated method stub
		return movieRepository.findById(movieId);
	}

	@Override
	public Movie getMovieByTitle(String title) {
		// TODO Auto-generated method stub
		return movieRepository.findByTitle(title);
	}

	@Override
	public void deleteMovieById(Long movieId) {
		// TODO Auto-generated method stub
		movieRepository.deleteById(movieId);
	}

	@Override
	public List<Movie> getMoviesOf2023() {
		// TODO Auto-generated method stub
		Optional<ReleaseYear> releaseYearOpt = releaseYearRepository.findByReleasedYear(2023);
		if(releaseYearOpt.isEmpty()) {
			throw new RuntimeException("### ReleaseYear 2023 is not found ###");
		}
		return movieRepository.findByReleaseYear(releaseYearOpt.get());
	}

	@Override
	public List<Movie> getMoviesByReleaseYear(Long releaseYearId) {
		// TODO Auto-generated method stub
		Optional<ReleaseYear> releaseYearOpt = releaseYearRepository.findById(releaseYearId);
		if(releaseYearOpt.isEmpty()) {
			throw new RuntimeException("### ReleaseYear not found with releaseYearId :"+releaseYearId+" ###");
		}
		return movieRepository.findByReleaseYear(releaseYearOpt.get());
	}

	@Override
	public List<Movie> getMoviesBygenere(Long genereId) {
		// TODO Auto-generated method stub
		Optional<Generes> genereOpt = generesRepository.findById(genereId);
		if(genereOpt.isEmpty()) {
			throw new RuntimeException("### No Genere found with genereId : "+genereId+" ###");
		}
		return movieRepository.findByGeneres(genereOpt.get());
	}

	@Override
	public List<Movie> getOnlyMovie() {
		// TODO Auto-generated method stub
		List<Movie> movies = (List<Movie>) movieRepository.findAll();
		return movies.stream().filter(movie -> movie.getType().equals(Type.MOVIE)).toList();
	}

	@Override
	public List<Movie> getOnlySerie() {
		// TODO Auto-generated method stub
		List<Movie> movies = (List<Movie>) movieRepository.findAll();
		return movies.stream().filter(movie -> movie.getType().equals(Type.SERIES)).toList();
	}

}
