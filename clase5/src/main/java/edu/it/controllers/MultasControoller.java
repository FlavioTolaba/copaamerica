package edu.it.controllers;

import edu.it.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multa")
public class MultasControoller {
    @Autowired
    private TicketRepository ticketRepo;

    @RequestMapping(method = RequestMethod.GET)
    public Object obtenerMultas() {
        return ticketRepo.findAll();
    }
    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public Object obtenerMulta(@PathVariable("id") String id) {
        return ticketRepo.findById(id);
    }
}
