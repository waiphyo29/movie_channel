package com.hostmdy.movie.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.hostmdy.movie.domain.Generes;
import com.hostmdy.movie.domain.Movie;
import com.hostmdy.movie.domain.ReleaseYear;
import com.hostmdy.movie.service.GeneresService;
import com.hostmdy.movie.service.MovieService;
import com.hostmdy.movie.service.ReleaseYearService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	private final GeneresService generesService;
	private final MovieService movieService;
	private final ReleaseYearService releaseYearService;
	
	public HomeController(GeneresService generesService, MovieService movieService,
			ReleaseYearService releaseYearService) {
		super();
		this.generesService = generesService;
		this.movieService = movieService;
		this.releaseYearService = releaseYearService;
	}
	
	private void reUse(Model model) {
		List<Movie> sortedMovies = movieService.getAllMovies().stream().
				sorted((movie1,movie2) -> movie1.getView() > movie2.getView() ? -1 : 1).toList();
		List<Movie> popularList = new ArrayList<>();
		int i = 0;
		for (final Movie movie : sortedMovies) {
			if(i > 10) {
				break;
			}
			popularList.add(movie);
			i++;
		}
		model.addAttribute("popularList",popularList);
		model.addAttribute("genereList",generesService.getAllGeneres());
		model.addAttribute("releaseYearList",releaseYearService.getAllReleaseYear());
	}

	@GetMapping({"/","/home","/index"})
	public String showHome(Model model, HttpSession session) {
		String message = "";
		if((message = (String) session.getAttribute("message")) != null) {
			session.setAttribute("message", null);
			model.addAttribute("message",message);
		}
		model.addAttribute("Movies",movieService.getAllMoviesByOrder());
		model.addAttribute("latest",true);
		reUse(model);
		return "home";
	}
	
	@GetMapping("/random")
	public String showRandom(Model model) {
		model.addAttribute("Movies",movieService.getAllMovies());
		model.addAttribute("latest",false);
		reUse(model);
		return "home";
	}
	
	@GetMapping("/movie")
	public String showMovies(Model model) {
		model.addAttribute("Movies",movieService.getOnlyMovie());
		model.addAttribute("isMovie",true);
		reUse(model);
		return "home";
	}
	
	@GetMapping("/serie")
	public String showSeries(Model model) {
		model.addAttribute("Movies",movieService.getOnlySerie());
		model.addAttribute("isMovie",false);
		reUse(model);
		return "home";
	}
	
	@GetMapping("/{yearId}/releaseYear")
	public String searchMoviesWithYear(@PathVariable Long yearId,Model model) {
		model.addAttribute("Movies",movieService.getMoviesByReleaseYear(yearId));
		Optional<ReleaseYear> releaseYearOpt = releaseYearService.getReleaseYearById(yearId);
		model.addAttribute("result",(releaseYearOpt.get()).getReleasedYear());
		reUse(model);
		return "home";
	}
	
	@GetMapping("/{genereId}/genere")
	public String searchMoviesWithGenere(@PathVariable Long genereId,Model model) {
		model.addAttribute("Movies",movieService.getMoviesBygenere(genereId));
		Optional<Generes> genereOpt = generesService.getGenereById(genereId);
		model.addAttribute("result",(genereOpt.get()).getGenere());
		reUse(model);
		return "home";
	}
	
	@GetMapping("/search")
	public String searchMoviesByTitle(@RequestParam String title,Model model) {
		List<Movie> searchList = movieService.getAllMovies().stream()
				.filter(movie -> (movie.getTitle().toUpperCase()).contains(title.toUpperCase())).toList();
		reUse(model);
		model.addAttribute("result",title);
		model.addAttribute("Movies",searchList);
		return "home";
	}

}
