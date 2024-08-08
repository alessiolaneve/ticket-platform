package it.alessiolaneve.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.alessiolaneve.platform.model.Category;
import it.alessiolaneve.platform.model.Ticket;
import it.alessiolaneve.platform.model.Ticket.Status;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

	List<Ticket> findByTitleContainingIgnoreCase(String title);

	List<Ticket> findByCategoryName(String categoryName);

	List<Ticket> findByStatus(Status status);

}
