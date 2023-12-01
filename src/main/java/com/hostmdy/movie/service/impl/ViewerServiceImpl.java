package com.hostmdy.movie.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.movie.crypto.PasswordEncoder;
import com.hostmdy.movie.crypto.PasswordValidator;
import com.hostmdy.movie.domain.Viewer;
import com.hostmdy.movie.repository.ViewerRepository;
import com.hostmdy.movie.service.ViewerService;
@Service
public class ViewerServiceImpl implements ViewerService{
	
	private ViewerRepository viewerRepository;

	public ViewerServiceImpl(ViewerRepository viewerRepository) {
		super();
		this.viewerRepository = viewerRepository;
	}

	@Override
	public Viewer saveViewer(Viewer viewer) {
		// TODO Auto-generated method stub
		return viewerRepository.save(viewer);
	}

	@Override
	public Optional<Viewer> createViewer(Viewer viewer) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// TODO Auto-generated method stub
		Optional<Viewer> viewerOpt = viewerRepository.findByName(viewer.getName());
		Optional<Viewer> viewerOptByemail = viewerRepository.findByEmail(viewer.getEmail());
		if(viewerOpt.isPresent() || viewerOptByemail.isPresent()) {
			return Optional.empty();
		}
		String password = PasswordEncoder.encode(viewer.getPassword());
		viewer.setRole("user");
		viewer.setPassword(password);
		return Optional.of(saveViewer(viewer));
	}

	@Override
	public Optional<Viewer> verifyViewer(String name, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// TODO Auto-generated method stub
		Optional<Viewer> viewerOpt = viewerRepository.findByName(name);
		if(viewerOpt.isEmpty()) {
			return Optional.empty();
		}
		boolean isExist = PasswordValidator.validatePassword(password, (viewerOpt.get()).getPassword());
		if(!isExist) {
			return Optional.empty();
		}
		return viewerOpt;
	}

	@Override
	public Viewer getViewerByPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// TODO Auto-generated method stub
		Optional<Viewer> viewerOpt  = viewerRepository.findByPassword(PasswordEncoder.encode(password));
		if(viewerOpt.isEmpty()) {
			throw new NullPointerException("## No Viewer found with password "+password+" ##");
		}
		return viewerOpt.get();
	}

}
