package com.hostmdy.movie.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hostmdy.movie.domain.Viewer;
import com.hostmdy.movie.service.ViewerService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class ViewerController {

	private final ViewerService viewerService;

	public ViewerController(ViewerService viewerService) {
		super();
		this.viewerService = viewerService;
	}

	@PostMapping("/signup")
	public String createUser(HttpSession session, @RequestParam String name, @RequestParam String email,
			@RequestParam String password, Model model) throws NoSuchAlgorithmException, InvalidKeySpecException {
		Viewer createViewer = new Viewer();
		createViewer.setName(name);
		createViewer.setEmail(email);
		createViewer.setPassword(password);
		Optional<Viewer> viewerOpt = viewerService.createViewer(createViewer);
		if (viewerOpt.isEmpty()) {
			session.setAttribute("message", "Fail to SignUp. Try again!");
		}else {
			session.setAttribute("message", "Successifully SignUp!");
			session.setAttribute("viewer", viewerOpt.get());
		}
		return "redirect:/";
	}
	
	@PostMapping("/signin") 
	public String verifyUser(HttpSession session, @RequestParam String name, @RequestParam String password, Model model) throws NoSuchAlgorithmException, InvalidKeySpecException {
		Optional<Viewer> viewerOpt = viewerService.verifyViewer(name, password);
		if(viewerOpt.isEmpty()) {
			session.setAttribute("message", "Name or Password is wrong!");
		}else {
			session.setAttribute("message", "Successifully SignUp!");
			session.setAttribute("viewer", viewerOpt.get());
		}
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
