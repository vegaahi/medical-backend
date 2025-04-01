package com.medical.controller;

import com.medical.entity.Customers;
import com.medical.entity.Tickets;
import com.medical.service.CustomerService;
import com.medical.service.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping()
public class TicketsController {

    private final TicketsService ticketsService;
    private final CustomerService customerService;

    @Autowired
    public TicketsController(TicketsService ticketsService , CustomerService customerService) {
        this.ticketsService = ticketsService;
		this.customerService = customerService;
    }

    // Create a new ticket (using RequestBody)
    @PostMapping("/customers/tickets/post")
    public Tickets createTicket(@RequestBody Tickets ticketRequest ) throws Exception {
    	
    	  Long customerId = ticketRequest.getCustomerId().getId();
    	    Customers customer = customerService.getCustomerById(customerId);

    	    if (customer == null) {
    	        throw new Exception("Customer not found with ID: " + customerId);
    	    }

    	    ticketRequest.setCustomerId(customer);
            ticketRequest.setCreatedAt(LocalDateTime.now());
    	    return ticketsService.createTicket(ticketRequest);
    }

    // Get tickets by status
    @GetMapping("/customers/tickets/status/{ticketid}")
    public Tickets getTicketsByStatus(@PathVariable Long ticketid) {
        return ticketsService.getTicketsById(ticketid);
    }
    
    
    @GetMapping("/admins/tickets/get/all")
    public List<Tickets> getAllTickets(){
		return ticketsService.getAllTickets();
    	
    }

    @GetMapping("/customers/tickets/get/{id}")
    public List<Tickets> getTicketsByCustomer(@PathVariable long id) {
        return ticketsService.getTicketsByCustomer(id);
    }
    
    // Update ticket status (using RequestBody for status)
    @PutMapping("/admins/tickets/{ticketid}/{status}")
    public Tickets updateTicketStatus(@PathVariable Long ticketId, @PathVariable String status) {
        return ticketsService.updateTicketStatus(ticketId, status);
    }
}
