package com.hostmdy.movie.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

import com.hostmdy.movie.domain.Viewer;

public interface ViewerService {
	
	Viewer saveViewer(Viewer viewer);
	
	Optional<Viewer> createViewer(Viewer viewer) throws NoSuchAlgorithmException, InvalidKeySpecException;
	
	Optional<Viewer> verifyViewer(String name, String password) throws NoSuchAlgorithmException, InvalidKeySpecException;
	
	Viewer getViewerByPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException;

}
