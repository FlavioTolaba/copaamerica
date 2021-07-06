package edu.it.services;

import com.bolivarsoft.sensorclima.TipoClima;
import com.bolivarsoft.sensorvelocidad.DatosVehiculo;
import edu.it.entities.Ticket;
import edu.it.repository.GrabadorTicket;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class EvaluadorDeMultas {
    private GrabadorTicket grabadorTicket;

    public EvaluadorDeMultas(GrabadorTicket grabadorTicket) {
        this.grabadorTicket = grabadorTicket;
    }

    public Boolean esMulta(TipoClima tipoClima, DatosVehiculo datosVehiculo) {
        return true;
    }
    public void evaluar(TipoClima tipoClima, DatosVehiculo datosVehiculo) {
        System.out.println("Evaluando la patente: " + datosVehiculo.patente);

        // Si en virtud de la logica, sale que este vehiculo es
        // candidato para multa? creo un objeto ticket y lo grabo en
        // formato json y SQL

        var tkt = new Ticket(
                UUID.randomUUID().toString(),
                System.currentTimeMillis() / 1000,
                datosVehiculo.patente,
                datosVehiculo.tipoVehiculo,
                tipoClima,
                100,
                datosVehiculo.velocidadMedida
        );

        grabadorTicket.grabar(tkt);
    }
}
