package edu.it.repository;

import edu.it.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class GrabarTicketEnVariosFormatos implements GrabadorTicket {
    @Autowired
    private GrabadorTicketFormatoJson grabadorTicketFormatoJson;

    @Autowired
    private TicketRepository ticketRepository;

    public void grabar(Ticket tkt) {
        try {
            grabadorTicketFormatoJson.grabar(tkt);
        }
        catch (Exception ex) {
        }

        try {
            ticketRepository.save(tkt);
        }
        catch (Exception ex) {
        }
    }

}
