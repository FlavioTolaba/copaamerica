package edu.it.repository;

import com.google.gson.Gson;
import edu.it.entities.Ticket;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class GrabadorTicketFormatoJson implements GrabadorTicket {
    @Value("${ticket.path}")
    private String pathTicket;

    public void grabar(Ticket tkt) {
        String strTkt = new Gson().toJson(tkt);
        System.out.println(strTkt);

        String nomArchivo = String.join("", pathTicket, "/", tkt.id, ".json");
        try {
            FileUtils.write(new File(nomArchivo), strTkt, "utf-8");
        } catch (IOException ex) {
            Logger.getLogger(GrabadorTicketFormatoJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
