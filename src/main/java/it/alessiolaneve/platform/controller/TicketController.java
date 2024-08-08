package it.alessiolaneve.platform.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.alessiolaneve.platform.model.Category;
import it.alessiolaneve.platform.model.Note;
import it.alessiolaneve.platform.model.Ticket;
import it.alessiolaneve.platform.repository.CategoryRepository;
import it.alessiolaneve.platform.repository.NoteRepository;
import it.alessiolaneve.platform.repository.TicketRepository;
import it.alessiolaneve.platform.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class TicketController {
    
    @Autowired
    TicketRepository ticketRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    CategoryRepository categoryRepository;
    
    @Autowired
    NoteRepository noteRepository;
    
    @GetMapping("/home")
    public String index(Model model, @RequestParam(name = "title", required = false) String title) {
        
        List<Ticket> tickets = new ArrayList<>();
        
        if(title == null || title.isBlank()) {
            tickets = ticketRepository.findAll();
        } else {
            tickets = ticketRepository.findByTitleContainingIgnoreCase(title);
        }
        model.addAttribute("tickets", tickets);
        
        return "tickets/index";
    }
    
    @GetMapping("/show/{id}")
    public String show(@PathVariable("id") Integer ticketId, Model model) {
        model.addAttribute("ticket", ticketRepository.findById(ticketId).orElse(null));
        Note note = new Note();
        model.addAttribute("note", note);
        return "tickets/show";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ticket", ticketRepository.findById(id).orElse(null));
        return "tickets/edit";
    }
    
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "tickets/edit";
        }
        ticketRepository.save(ticket);
        return "redirect:/tickets";
    }
    
    @PostMapping("/create")
    public String store(
        @Valid @ModelAttribute("ticket") Ticket formTicket,
       BindingResult bindingResult,
       Model model){
        
       if(bindingResult.hasErrors()) {
          return "tickets/create";
       }

       ticketRepository.save(formTicket);

       return "redirect:/tickets";
    }
    

    @GetMapping("/tickets/status")
    public String getTicketsByStatus(@RequestParam(value = "status", required = false, defaultValue = "NUOVO") Ticket.Status status, Model model) {
        List<Ticket> tickets = ticketRepository.findByStatus(status);
        model.addAttribute("tickets", tickets);
        model.addAttribute("status", status);
        return "tickets/searchByStatus";
    }
    
    @GetMapping("/tickets/category")
    public String getTicketsByCategory(@RequestParam("category") String category, Model model) {
        List<Ticket> tickets = ticketRepository.findByCategoryName(category);
        model.addAttribute("tickets", tickets);
        model.addAttribute("category", category);
        return "tickets/searchByCategory";
    }
    
    @PostMapping("/tickets/{id}/addNote")
    public String addNote(@PathVariable("id") Integer ticketId, @Valid @ModelAttribute("note") Note note, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "tickets/show";
        }
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        if (ticket != null) {
            note.setTicket(ticket);
            noteRepository.save(note);
        }
        return "redirect:/show/" + ticketId;
    }
}
