package com.hostmdy.movie.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.movie.domain.Episodes;

public interface EpisodesService {
	
	Episodes saveEpisode(Episodes episode);
	
	Episodes createEpisode(Episodes episode,Long movieId);
	
	Optional<Episodes> getEpisodeById(Long episodeId);
	
	List<Episodes> getEpisodesByMovieId(Long movieId);
	
	void deleteEpisodeById(Long episodeId);

}
