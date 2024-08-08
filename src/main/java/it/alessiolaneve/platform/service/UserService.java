package it.alessiolaneve.platform.service;

import java.util.List;
import java.util.Optional;

import it.alessiolaneve.platform.model.User;

public interface UserService {

	public User save(User user);
	
    public List<User> findAll();

    public Optional<User> findbyId(Integer id);

    public List<User> findByName(String name);
    
    public List<User> findBySurname(String surname);
}
