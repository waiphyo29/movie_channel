package com.hostmdy.movie.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hostmdy.movie.domain.Movie;
import com.hostmdy.movie.domain.Photos;
import com.hostmdy.movie.repository.MovieRepository;
import com.hostmdy.movie.repository.PhotosRepository;
import com.hostmdy.movie.service.PhotosService;

@Service
public class PhotosServiceImpl implements PhotosService{
	
	private final PhotosRepository photosRepository;
	private final MovieRepository movieRepositoy;

	public PhotosServiceImpl(PhotosRepository photosRepository, MovieRepository movieRepositoy) {
		super();
		this.photosRepository = photosRepository;
		this.movieRepositoy = movieRepositoy;
	}

	@Override
	public Photos savePhoto(Photos photo) {
		// TODO Auto-generated method stub
		return photosRepository.save(photo);
	}

	@Override
	public Photos createPhoto(Photos photo, Long movieId) {
		// TODO Auto-generated method stub
		Optional<Movie> movieOpt = movieRepositoy.findById(movieId);
		if(movieOpt.isEmpty()) {
			throw new NullPointerException("### No movie found with movieid : "+movieId+" ###");
		}
		Movie movie = movieOpt.get();
		movie.getPhotos().add(photo);
		photo.setMovie(movie);
		return savePhoto(photo);
	}

	@Override
	public Optional<Photos> getPhotoById(Long PhotoId) {
		// TODO Auto-generated method stub
		return photosRepository.findById(PhotoId);
	}

	@Override
	public List<Photos> getPhotosByMovieId(Long movieId) {
		// TODO Auto-generated method stub
		Optional<Movie> movieOpt = movieRepositoy.findById(movieId);
		if(movieOpt.isEmpty()) {
			throw new NullPointerException("### No movie found with movieid : "+movieId+" ###");
		}
		return photosRepository.findByMovie(movieOpt.get());
	}

	@Override
	public byte[] changeImageToByteArray(MultipartFile imageFile) throws IOException {
		// TODO Auto-generated method stub
		byte[] imageBytes = new byte[imageFile.getBytes().length];
		int i = 0;
		for (final byte b : imageFile.getBytes()) {
			imageBytes[i++] = b;
		}
		return imageBytes;
	}

	@Override
	public void deletePhotoById(Long photoId) {
		// TODO Auto-generated method stub
		photosRepository.deleteById(photoId);
	}

}
