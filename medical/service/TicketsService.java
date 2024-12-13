package com.medical.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medical.entity.Customers;
import com.medical.entity.Tickets;
import com.medical.repository.CustomerRepository;
import com.medical.repository.TicketsRepository;
import com.medical.service.CustomerService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketsService {

	@Autowired
    private  TicketsRepository ticketsRepository;
    @Autowired
    private CustomerService customerService ;
    
    

 

    // Create a new ticket using RequestBody
    public Tickets createTicket(Tickets ticketRequest) {
//        Tickets ticket = Tickets.builder()
//                .customerName(ticketRequest.getCustomerName())
//                .email(ticketRequest.getEmail())
//                .issueDescription(ticketRequest.getIssueDescription())
//                .priority(ticketRequest.getPriority())
//                .status("OPEN")
//                .createdAt(LocalDateTime.now())
//                .build();
  

        return ticketsRepository.save(ticketRequest);
    }

    // Get tickets by status
    public List<Tickets> getTicketsByStatus(String status) {
        return ticketsRepository.findByStatus(status);
    }

    // Get tickets by status
    public List<Tickets> getTicketsByCustomer(long id) {
    	Customers customer = customerService.getCustomerById(id);
    	
        return ticketsRepository.findByCustomerId(customer);
    }
    // Update ticket status using RequestBody
    public Tickets updateTicketStatus(Long ticketId, String status) {
        Tickets ticket = ticketsRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));

        ticket.setStatus(status);
        ticket.setUpdatedAt(LocalDateTime.now());
        return ticketsRepository.save(ticket);
    }



	public Tickets getTicketsById(Long ticketid) {
		return ticketsRepository.findById(ticketid).orElse(null);
	}

	public List<Tickets> getAllTickets() {
		
		return  ticketsRepository.findAll();
	}
}
