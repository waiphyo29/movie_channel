package com.hostmdy.movie.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hostmdy.movie.domain.Comment;
import com.hostmdy.movie.domain.Viewer;
import com.hostmdy.movie.service.CommentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("comment")
public class CommentController {
	
	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}
	
	@PostMapping("/{movieId}/create")
	public String createComment(@RequestParam String comment, @PathVariable Long movieId, @RequestParam Long commentId, HttpSession httpSession) {
		Viewer viewer = (Viewer) httpSession.getAttribute("viewer");
		if(commentId <= 0) {
		Comment createComment = new Comment();
		createComment.setWriter(viewer.getName());
		createComment.setComment(comment);
		commentService.createComment(createComment, movieId, viewer.getId());
		}else {
			Comment newComment = commentService.getCommentById(commentId);
			newComment.setComment(comment);
			commentService.createComment(newComment, movieId, viewer.getId());
		}
		return "redirect:/movie/"+movieId+"/detail";
	}
	
	@GetMapping("/{movieId}/delete/{commentId}")
	public String deleteComment(@PathVariable Long commentId, @PathVariable Long movieId, HttpSession httpSession) {
//		Viewer viewer = (Viewer) httpSession.getAttribute("viewer");
//		Comment comment = commentService.getCommentByViewer(viewer);
//		if(comment.getId() != commentId) {
//			throw new NullPointerException("## Not match with commentId and commentId By Viewer (inside CommentController) ##");
//		}
		commentService.deleteCommentById(commentId);
		return "redirect:/movie/"+movieId+"/detail";
	}
	
//	@GetMapping("/{movieId}/edit/{commentId}")
//	public String editComment(@PathVariable Long commentId, @PathVariable Long movieId, @RequestParam String newComment, HttpSession httpSession) {
//		Comment comment = commentService.getCommentById(commentId);
//		comment.setComment(newComment);
//		commentService.createComment(comment, movieId, movieId);
//		return "redirect:/movie/"+movieId+"/detail";
//	}
	

}
