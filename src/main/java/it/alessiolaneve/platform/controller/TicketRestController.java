package it.alessiolaneve.platform.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.alessiolaneve.platform.model.Category;
import it.alessiolaneve.platform.model.Ticket;
import it.alessiolaneve.platform.response.Payload;
import it.alessiolaneve.platform.service.TicketService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {

	@Autowired
	private TicketService ticketService;

	@GetMapping("{id}")
	public ResponseEntity<Payload<Ticket>> get(@PathVariable("id") Integer idTicket) {

		Optional<Ticket> ticket = ticketService.findbyId(idTicket);

		if (ticket.isPresent()) {
			return ResponseEntity.ok(new Payload<Ticket>(ticket.get(), null, HttpStatus.OK));
		} else {
			return new ResponseEntity<Payload<Ticket>>(
					new Payload<Ticket>(null, "Ticket #" + idTicket + " non trovato", HttpStatus.NOT_FOUND),
					HttpStatus.NOT_FOUND);
		}
	}

	
	@GetMapping
	public ResponseEntity<Payload<List<Ticket>>> getTickets() {
		List<Ticket> lista = ticketService.findAll();
		return ResponseEntity.ok(new Payload<List<Ticket>>(lista, null, HttpStatus.OK));
	}
	
	@PostMapping("/create")
	public ResponseEntity store(@Valid @RequestBody Ticket ticket) {
		try {
			Ticket ticketNew = ticketService.save(ticket);
			return ResponseEntity.ok(ticketNew);
		} catch (Exception e) {
			return new ResponseEntity<>("Errore nel salvataggio del libro", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Integer idTicket,
			@Valid @RequestBody Ticket ticket) {
		try {
			Ticket ticketUpdated = ticketService.update(idTicket, ticket);
			
			return ResponseEntity.ok(ticketUpdated);
		} catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), 
					HttpStatus.NOT_FOUND);
		} catch(Exception e) {
			return new ResponseEntity<>("Errore nel salvataggio del libro", 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
		try {
			ticketService.delete(id);
			return ResponseEntity.ok("Cancellazione effettuata");
		} catch(Exception e) {
			return new ResponseEntity<>("Errore nel salvataggio del ticket", 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/status")
    public List<Ticket> getTicketsByStatus(@RequestParam(value = "status", required = false, defaultValue = "NUOVO") Ticket.Status status) {
        return ticketService.findByStatus(status);
    }
	
	@GetMapping("/category")
    public ResponseEntity<Payload<List<Ticket>>> getTicketsByCategory(@RequestParam("category") String category) {
        List<Ticket> tickets = ticketService.findTicketsByCategory(category);
        return ResponseEntity.ok(new Payload<List<Ticket>>(tickets, null, HttpStatus.OK));
    }
}



