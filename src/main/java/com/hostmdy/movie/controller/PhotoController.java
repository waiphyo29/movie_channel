package com.hostmdy.movie.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hostmdy.movie.domain.Movie;
import com.hostmdy.movie.domain.Photos;
import com.hostmdy.movie.service.MovieService;
import com.hostmdy.movie.service.PhotosService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/image")
public class PhotoController {
	
	private MovieService movieService;
	private PhotosService photosService;
	
	public PhotoController(MovieService movieService, PhotosService photosSrevice) {
		super();
		this.movieService = movieService;
		this.photosService = photosSrevice;
	}
	
	@GetMapping("/movie/{movieId}/show")
	public void showMovieImage(@PathVariable Long movieId,HttpServletResponse response) {
		Optional<Movie> movieOpt = movieService.getMovieById(movieId);
		if(movieOpt.isEmpty()) {
			throw new NullPointerException("### imageController movie null exception ###");
		}
		Movie movie = movieOpt.get();
		byte[] imageBytes = new byte[movie.getImage().length];
		int i = 0;
		for (final byte b : movie.getImage()) {
			imageBytes[i++] = b;
		}
		InputStream ls = new ByteArrayInputStream(imageBytes);
		response.setContentType("imge/jpeg");
		try {
			IOUtils.copy(ls, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping("/photo/{photoId}/show")
	public void showMoviephoto(@PathVariable Long photoId,HttpServletResponse response) {
		Optional<Photos> photoOpt = photosService.getPhotoById(photoId);
		if(photoOpt.isEmpty()) {
			throw new NullPointerException("### imageController movie null exception ###");
		}
		Photos photo = photoOpt.get();
		byte[] imageBytes = new byte[photo.getPhoto().length];
		int i = 0;
		for (final byte b : photo.getPhoto()) {
			imageBytes[i++] = b;
		}
		InputStream ls = new ByteArrayInputStream(imageBytes);
		response.setContentType("imge/jpeg");
		try {
			IOUtils.copy(ls, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping("/photo/{movieId}/form")
	public String showPhotoForm(@PathVariable Long movieId, Model model) {
		model.addAttribute("photos", photosService.getPhotosByMovieId(movieId));
		model.addAttribute("movieId", movieId);
		return "/photo/bg-photo-form";
	}
	
	@PostMapping("/photo/{movieId}/new")
	public String createPhoto(@PathVariable Long movieId, @RequestParam MultipartFile photoFile) throws IOException {
		System.out.println("inside controller");
		if(photoFile.isEmpty()) {
			System.out.println("file is empty");
		}
		Photos photo = new Photos();
		photo.setPhoto(photosService.changeImageToByteArray(photoFile));
		photosService.createPhoto(photo, movieId);
		return "redirect:/image/photo/"+movieId+"/form";
	}
	
	@GetMapping("/photo/{movieId}/delete/{photoId}")
	public String deletePhoto(@PathVariable Long movieId, @PathVariable Long photoId) {
		photosService.deletePhotoById(photoId);
		return "redirect:/image/photo/"+movieId+"/form";
	}

}
