package com.hostmdy.movie.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hostmdy.movie.domain.Episodes;
import com.hostmdy.movie.domain.Movie;
import com.hostmdy.movie.service.EpisodesService;
import com.hostmdy.movie.service.MovieService;

@Controller
@RequestMapping("/episode")
public class EpisodeController {
	private final MovieService movieService;
	private final EpisodesService episodesService;
	
	
	public EpisodeController(EpisodesService episodesService, MovieService movieService) {
		super();
		this.movieService = movieService;
		this.episodesService = episodesService;
	}
	
	@GetMapping("/{movieId}/form")
	public String showCreateForm(Model model,@PathVariable Long movieId) {
		Optional<Movie> movieOpt = movieService.getMovieById(movieId);
		if(movieOpt.isEmpty()) {
			throw new NullPointerException("## no movie found with movieId :"+movieId+" ##");
		}
		Episodes episode = new Episodes();
		model.addAttribute("episode",episode);
		model.addAttribute("movieId",movieId);
		return "episode/episode-form";
	}
	
	@PostMapping("/{movieId}/create")
	public String createEpisode(@ModelAttribute Episodes episode, @PathVariable Long movieId) {
		Episodes createdEp = episodesService.createEpisode(episode, movieId);
		if(createdEp == null) {
			throw new NullPointerException("## Fail to add ep "+episode.getEpisode()+" of movieId : "+movieId+" ##");
		}
		return "redirect:/movie/"+movieId+"/detail";
	}
	
	@GetMapping("/{movieId}/update/{episodeId}")
	public String updateEpisode(Model model, @PathVariable Long movieId, @PathVariable Long episodeId) {
		Optional<Movie> movieOpt = movieService.getMovieById(movieId);
		if(movieOpt.isEmpty()) {
			throw new NullPointerException("## no movie found with movieId :"+movieId+" ##");
		}
		Optional<Episodes> episodeOpt = episodesService.getEpisodeById(episodeId);
		if(episodeOpt.isEmpty()) {
			throw new NullPointerException("## no episode found with episodeId :"+episodeId+" ##");
		}
		model.addAttribute("episode",episodeOpt.get());
		model.addAttribute("movieId",movieId);
		return "episode/episode-form";
	}
	
	@GetMapping("/{movieId}/delete/{episodeId}")
	public String deleteEpisode(@PathVariable Long episodeId, @PathVariable Long movieId) {
		episodesService.deleteEpisodeById(episodeId);
		return "redirect:/movie/"+movieId+"/detail";
	}

}
