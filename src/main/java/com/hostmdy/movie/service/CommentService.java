package com.hostmdy.movie.service;

import java.util.List;

import com.hostmdy.movie.domain.Comment;
import com.hostmdy.movie.domain.Viewer;

public interface CommentService {
	
	Comment saveComment(Comment comment);
	
	Comment createComment(Comment comment, Long movieId, Long viewerId);
	
	Comment getCommentById(Long commentId);
	
	List<Comment> getCommentsByMovie(Long movieId);
	
	Comment getCommentByViewer(Viewer viewer);
	
	void deleteCommentById(Long commentId);

}
