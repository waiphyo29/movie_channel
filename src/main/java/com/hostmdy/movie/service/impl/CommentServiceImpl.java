package com.hostmdy.movie.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.movie.domain.Comment;
import com.hostmdy.movie.domain.Movie;
import com.hostmdy.movie.domain.Viewer;
import com.hostmdy.movie.repository.CommentRepository;
import com.hostmdy.movie.repository.MovieRepository;
import com.hostmdy.movie.repository.ViewerRepository;
import com.hostmdy.movie.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService{
	
	private final ViewerRepository viewerRepository;
	private final MovieRepository movieRepository;
	private final CommentRepository commentRepository;

	public CommentServiceImpl(ViewerRepository viewerRepository, MovieRepository movieRepository,
			CommentRepository commentRepository) {
		super();
		this.viewerRepository = viewerRepository;
		this.movieRepository = movieRepository;
		this.commentRepository = commentRepository;
	}

	@Override
	public Comment saveComment(Comment comment) {
		// TODO Auto-generated method stub
		return commentRepository.save(comment);
	}

	@Override
	public Comment createComment(Comment comment, Long movieId, Long viewerId) {
		// TODO Auto-generated method stub
		Optional<Movie> movieOpt = movieRepository.findById(movieId);
		if(movieOpt.isEmpty()) {
			throw new NullPointerException("## inside commentServiceImpl(create) No movie found with movieId : "+movieId+" ##");
		}
		Optional<Viewer> viewerOpt = viewerRepository.findById(viewerId);
		if(viewerOpt.isEmpty()) {
			throw new NullPointerException("## inside commentServiceImpl No viewer found with viewerId : "+viewerId+" ##");
		}
		Movie movie = movieOpt.get();
		comment.setMovie(movie);
		movie.getComments().add(comment);
		Viewer viewer = viewerOpt.get();
		comment.setViewer(viewer);
		viewer.getComments().add(comment);
		comment.setRecord(LocalDate.now());
		comment.setRecordTime(LocalTime.now());
		return saveComment(comment);
	}

	@Override
	public Comment getCommentById(Long commentId) {
		// TODO Auto-generated method stub
		Optional<Comment> commentOpt = commentRepository.findById(commentId);
		if(commentOpt.isEmpty()) {
			throw new NullPointerException("## inside commentServiceImpl no comment found ##");
		}
		return commentOpt.get();
	}

	@Override
	public List<Comment> getCommentsByMovie(Long movieId) {
		// TODO Auto-generated method stub
		Optional<Movie> movieOpt = movieRepository.findById(movieId);
		if(movieOpt.isEmpty()) {
			throw new NullPointerException("## inside commentServiceImpl(getByMovie) No movie found with movieId : "+movieId+" ##");
		}
		List<Comment> comments = commentRepository.findByMovie(movieOpt.get()).stream()
				.sorted((com1,com2) -> (com1.getRecordTime().compareTo(com2.getRecordTime()) == 1)? -1 : 1).toList();
		return comments;
	}

	@Override
	public Comment getCommentByViewer(Viewer viewer) {
		// TODO Auto-generated method stub
		Optional<Comment> commentOpt = commentRepository.findByViewer(viewer);
		if(commentOpt.isEmpty()) {
			throw new NullPointerException("## inside commentServiceImpl(getByViewer) No comment found ##");
		}
		return commentOpt.get();
	}

	@Override
	public void deleteCommentById(Long commentId) {
		// TODO Auto-generated method stub
		commentRepository.deleteById(commentId);
	}

}
