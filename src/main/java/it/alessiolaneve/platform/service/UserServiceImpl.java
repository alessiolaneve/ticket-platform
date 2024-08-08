package it.alessiolaneve.platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.alessiolaneve.platform.model.User;
import it.alessiolaneve.platform.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	public User saveUser(User user) {
        return userRepository.save(user);
    }
	
	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> findbyId(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<User> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findBySurname(String surname) {
		// TODO Auto-generated method stub
		return null;
	}
}
