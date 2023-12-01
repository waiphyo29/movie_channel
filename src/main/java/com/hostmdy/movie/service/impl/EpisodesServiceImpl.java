package com.hostmdy.movie.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.movie.domain.Episodes;
import com.hostmdy.movie.domain.Movie;
import com.hostmdy.movie.repository.EpisodesRepository;
import com.hostmdy.movie.repository.MovieRepository;
import com.hostmdy.movie.service.EpisodesService;

@Service
public class EpisodesServiceImpl implements EpisodesService{
	
	private final EpisodesRepository episodesRepository;
	private final MovieRepository movieRepository;

	public EpisodesServiceImpl(EpisodesRepository episodesRepository, MovieRepository movieRepository) {
		super();
		this.episodesRepository = episodesRepository;
		this.movieRepository = movieRepository;
	}

	@Override
	public Episodes saveEpisode(Episodes episode) {
		// TODO Auto-generated method stub
		return episodesRepository.save(episode);
	}

	@Override
	public Episodes createEpisode(Episodes episode, Long movieId) {
		// TODO Auto-generated method stub
		Optional<Movie> movieOpt = movieRepository.findById(movieId);
		if(movieOpt.isEmpty()) {
			throw new NullPointerException("### No movie found with movieid : "+movieId+" ###");
		}
		Movie movie = movieOpt.get();
		movie.getEpisodes().add(episode);
		episode.setMovie(movie);
		return saveEpisode(episode);
	}

	@Override
	public Optional<Episodes> getEpisodeById(Long episodeId) {
		// TODO Auto-generated method stub
		return episodesRepository.findById(episodeId);
	}

	@Override
	public List<Episodes> getEpisodesByMovieId(Long movieId) {
		// TODO Auto-generated method stub
		Optional<Movie> movieOpt = movieRepository.findById(movieId);
		if(movieOpt.isEmpty()) {
			throw new NullPointerException("### No movie found with movieid : "+movieId+" ###");
		}
		return episodesRepository.findBymovie(movieOpt.get());
	}

	@Override
	public void deleteEpisodeById(Long episodeId) {
		// TODO Auto-generated method stub
		episodesRepository.deleteById(episodeId);
	}

}
