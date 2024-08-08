package it.alessiolaneve.platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.alessiolaneve.platform.model.Category;
import it.alessiolaneve.platform.model.Ticket;
import it.alessiolaneve.platform.model.Ticket.Status;
import it.alessiolaneve.platform.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService{

	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Override
	public Ticket save(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	@Override
	public Ticket update(Integer id, Ticket inputTicket) {
		
		Optional<Ticket> ticketFound = ticketRepository.findById(id);
		
		if(ticketFound.isEmpty()) {
			throw new IllegalArgumentException("Il libro cercato con id " + id + " non esiste");
		}
		
		Ticket ticket = ticketFound.get();
		
		ticket.setCategory(inputTicket.getCategory());
		ticket.setDateUpdate(inputTicket.getDateUpdate());
		ticket.setDescription(inputTicket.getDescription());
		ticket.setNotes(inputTicket.getNotes());
		ticket.setStatus(inputTicket.getStatus());
		ticket.setTitle(inputTicket.getTitle());
		
		return ticketRepository.save(ticket);		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Ticket> findAll() {
		return ticketRepository.findAll();
	}

	@Override
	public Optional<Ticket> findbyId(Integer id) {
		return ticketRepository.findById(id);
	}

	@Override
	public List<Ticket> findByCategoryName(String categoryName) {
		return ticketRepository.findByCategoryName(categoryName);
	}

	@Override
	public List<Ticket> findByStatus(Status status) {
		return ticketRepository.findByStatus(status);
	}

	@Override
	public List<Ticket> findByTitleContaining(String userInput) {
		return ticketRepository.findByTitleContainingIgnoreCase(userInput);
	}

	public List<Ticket> findTicketsByCategory(String category) {
        return ticketRepository.findByCategoryName(category);
    }

	@Override
	public List<Ticket> findByCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
