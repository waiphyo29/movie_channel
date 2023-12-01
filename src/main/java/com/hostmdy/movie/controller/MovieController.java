package com.hostmdy.movie.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hostmdy.movie.domain.Comment;
import com.hostmdy.movie.domain.Episodes;
import com.hostmdy.movie.domain.Generes;
import com.hostmdy.movie.domain.Movie;
import com.hostmdy.movie.domain.Photos;
import com.hostmdy.movie.domain.ReleaseYear;
import com.hostmdy.movie.domain.Synopsis;
import com.hostmdy.movie.domain.Type;
import com.hostmdy.movie.service.CommentService;
import com.hostmdy.movie.service.EpisodesService;
import com.hostmdy.movie.service.GeneresService;
import com.hostmdy.movie.service.MovieService;
import com.hostmdy.movie.service.PhotosService;
import com.hostmdy.movie.service.ReleaseYearService;

@Controller
@RequestMapping("/movie")
public class MovieController {

	private final MovieService movieService;
	private final GeneresService generesService;
	private final ReleaseYearService releaseYearService;
	private final EpisodesService episodesService;
	private final PhotosService photosService;
	private final CommentService commentService;

	public MovieController(MovieService movieService, ReleaseYearService releaseYearService,
			GeneresService generesService, PhotosService photosService, EpisodesService episodesService, CommentService commentService) {
		super();
		this.movieService = movieService;
		this.generesService = generesService;
		this.releaseYearService = releaseYearService;
		this.episodesService = episodesService;
		this.photosService = photosService;
		this.commentService = commentService;
	}
	
	private void reUseCode(Model model) {
		List<Movie> sortedMovies = movieService.getAllMovies().stream().
				sorted((movie1,movie2) -> movie1.getView() > movie2.getView() ? -1 : 1).toList();
		List<Movie> popularList = new ArrayList<>();
		int i = 0;
		for (final Movie movie : sortedMovies) {
			if(i >= 12) {
				break;
			}
			popularList.add(movie);
			i++;
		}
		model.addAttribute("popularList",popularList);
		model.addAttribute("genereList",  generesService.getAllGeneres());
		model.addAttribute("releaseYearList",  releaseYearService.getAllReleaseYear());
	}
	
	@GetMapping("/{movieId}/detail")
	public String showMoiveDetail(@PathVariable Long movieId,Model model) {
		Optional<Movie> movieOpt = movieService.getMovieById(movieId);
		if(movieOpt.isEmpty()) {
			throw new NullPointerException("## inside movieController movie is not found ##");
		}
		model.addAttribute("photos",photosService.getPhotosByMovieId(movieId));
		model.addAttribute("movie",movieOpt.get());
		model.addAttribute("comments",commentService.getCommentsByMovie(movieId));
		reUseCode(model);
		return "movie/movie-detail";
	}

	@GetMapping("/form")
	public String showForm(Model model) {
		Movie movie = new Movie();
		movie.setSynopsis(new Synopsis());
		movie.setType(Type.MOVIE);
		reUseCode(model);
		model.addAttribute("movie", movie);
		return "movie/movie-form";
	}

	@PostMapping("/create")
	public String createMovie(@ModelAttribute Movie movie, @RequestParam String[] generesList,
			@RequestParam int releaseYear, @RequestParam MultipartFile imageFile) throws IOException {
		Set<Generes> generes = new HashSet<>();
		
		for (final String genere : generesList) {
			Optional<Generes> genereOpt = generesService.getGenereByname(genere);
			if (genereOpt.isEmpty()) {
				throw new NullPointerException("## genere not found ##");
			}
			generes.add(genereOpt.get());
		}
		
		Optional<ReleaseYear> releaseYearOpt = releaseYearService.getReleaseYearByReleasedYear(releaseYear);
		if (releaseYearOpt.isEmpty()) {
			throw new NullPointerException("## releaseYear not found ##");
		}
		
		if (!imageFile.isEmpty()) {
			byte[] imageBytes = photosService.changeImageToByteArray(imageFile);
			movie.setImage(imageBytes);
		}else if(movie.getId() != null) {
			Optional<Movie> movieOpt = movieService.getMovieById(movie.getId());
			Movie oldMovie = movieOpt.get();
			movie.setImage(oldMovie.getImage());
		}
		
		if(movie.getId() == null) {
			movieService.createMovie(movie, generes, releaseYearOpt.get());
			return "redirect:/episode/"+movie.getId()+"/form";
		}else {
			movieService.createMovie(movie, generes, releaseYearOpt.get());
			return "redirect:/movie/"+movie.getId()+"/detail";
		}
	}
	
	
	@GetMapping("/{movieId}/update")
	public String updateMovie(@PathVariable Long movieId, Model model) {
		Optional<Movie> movieOpt = movieService.getMovieById(movieId);
		if(movieOpt.isEmpty()) {
			throw new NullPointerException("## inside movieController movieId : "+movieId+" is not found ##");
		}
		reUseCode(model);
		model.addAttribute("movie",movieOpt.get());
		return "movie/movie-form";
	}
	
	@GetMapping("/{movieId}/delete")
	public String deleteMovie(@PathVariable Long movieId) {
		Optional<Movie> movieOpt = movieService.getMovieById(movieId);
		if(movieOpt.isEmpty()) {
			throw new NullPointerException("## inside movieController movieId : "+movieId+" is not found ##");
		}
		List<Episodes> episodesList = episodesService.getEpisodesByMovieId(movieId);
		if(!episodesList.isEmpty()) {
			List<Episodes> deleteList = new ArrayList<>();
			deleteList.addAll(episodesList);
			for (final Episodes episode : deleteList) {
				episodesService.deleteEpisodeById(episode.getId());
			}
		}
		List<Photos> photosList = photosService.getPhotosByMovieId(movieId);
		if(!photosList.isEmpty()) {
			List<Photos> deleteList = new ArrayList<>();
			deleteList.addAll(photosList);
			for (final Photos photo : deleteList) {
				photosService.deletePhotoById(photo.getId());
			}
		}
		List<Comment> commentList = commentService.getCommentsByMovie(movieId);
		if(!commentList.isEmpty()) {
			List<Comment> deleteList = new ArrayList<>();
			deleteList.addAll(commentList);
			for (final Comment comment : deleteList) {
				commentService.deleteCommentById(comment.getId());
			}
		}
		movieService.deleteMovieById(movieId);
		return "redirect:/";
	}
	
	@GetMapping("/{movieId}/view")
	public String addView(@PathVariable Long movieId) {
		Optional<Movie> movieOpt = movieService.getMovieById(movieId);
		if(movieOpt.isEmpty()) {
			throw new NullPointerException("## inside movieController movieId : "+movieId+" is not found ##");
		}
		Movie movie = movieOpt.get();
		movie.setView(movie.getView()+1);
		movieService.saveMovie(movie);
		return "redirect:/movie/"+movieId+"/detail";
	}

}
