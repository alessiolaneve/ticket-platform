package it.alessiolaneve.platform.service;

import java.util.List;
import java.util.Optional;

import it.alessiolaneve.platform.model.Category;
import it.alessiolaneve.platform.model.Ticket;


public interface TicketService {

	
	public Ticket save(Ticket ticket);
	
	public Ticket update(Integer id, Ticket ticket);
	
	public void delete(Integer id);
	
    public List<Ticket> findAll();

    public Optional<Ticket> findbyId(Integer id);

    public List<Ticket> findByCategoryName(String categoryName);

    public List<Ticket> findByStatus(Ticket.Status status);
    
    public List<Ticket> findByTitleContaining(String userInput);

	public List<Ticket> findByCategory(Category category);

	public List<Ticket> findTicketsByCategory(String category);

}
