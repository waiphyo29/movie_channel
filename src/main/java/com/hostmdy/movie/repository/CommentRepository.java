package com.hostmdy.movie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.movie.domain.Comment;
import com.hostmdy.movie.domain.Movie;
import com.hostmdy.movie.domain.Viewer;

public interface CommentRepository extends CrudRepository<Comment, Long>{

	List<Comment> findByMovie(Movie movie);
	
	Optional<Comment> findByViewer(Viewer viewer);
}
