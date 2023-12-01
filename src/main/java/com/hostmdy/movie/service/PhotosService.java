package com.hostmdy.movie.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.hostmdy.movie.domain.Photos;

public interface PhotosService {
	
	Photos savePhoto(Photos photo);
	
	Photos createPhoto(Photos photo, Long movieId);
	
	Optional<Photos> getPhotoById(Long PhotoId);
	
	List<Photos> getPhotosByMovieId(Long movieId);
	
	byte[] changeImageToByteArray(MultipartFile imageFile) throws IOException;
	
	void deletePhotoById(Long photoId);

}
