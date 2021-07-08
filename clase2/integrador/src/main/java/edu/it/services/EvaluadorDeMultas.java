package edu.it.services;

import com.bolivarsoft.sensorclima.TipoClima;
import static com.bolivarsoft.sensorclima.TipoClima.LLUVIAS_MODERADAS;
import static com.bolivarsoft.sensorclima.TipoClima.LLUVIAS_TORRENCIALES;
import static com.bolivarsoft.sensorclima.TipoClima.NORMAL;
import com.bolivarsoft.sensorvelocidad.DatosVehiculo;
import static com.bolivarsoft.sensorvelocidad.TipoVehiculo.Auto;
import static com.bolivarsoft.sensorvelocidad.TipoVehiculo.Camion;
import static com.bolivarsoft.sensorvelocidad.TipoVehiculo.Moto;
import static com.bolivarsoft.sensorvelocidad.TipoVehiculo.Tractor;
import edu.it.entities.Ticket;
import edu.it.model.ClimaTipoVehiculo;
import edu.it.repository.GrabadorTicket;
import java.util.HashMap;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EvaluadorDeMultas {
    private Logger logger = LoggerFactory.getLogger(EvaluadorDeMultas.class);
    private GrabadorTicket grabadorTicket;
    private HashMap<ClimaTipoVehiculo, Integer> limites = new HashMap<>();

    public EvaluadorDeMultas(GrabadorTicket grabadorTicket) {
        this.grabadorTicket = grabadorTicket;

        limites.put(new ClimaTipoVehiculo(NORMAL, Auto), 130);
        limites.put(new ClimaTipoVehiculo(NORMAL, Camion), 90);
        limites.put(new ClimaTipoVehiculo(NORMAL, Moto), 130);
        limites.put(new ClimaTipoVehiculo(NORMAL, Tractor), 60);

        limites.put(new ClimaTipoVehiculo(LLUVIAS_MODERADAS, Auto), 110);
        limites.put(new ClimaTipoVehiculo(LLUVIAS_MODERADAS, Camion), 80);
        limites.put(new ClimaTipoVehiculo(LLUVIAS_MODERADAS, Moto), 110);
        limites.put(new ClimaTipoVehiculo(LLUVIAS_MODERADAS, Tractor), 60);

        limites.put(new ClimaTipoVehiculo(LLUVIAS_TORRENCIALES, Auto), 90);
        limites.put(new ClimaTipoVehiculo(LLUVIAS_TORRENCIALES, Camion), 70);
        limites.put(new ClimaTipoVehiculo(LLUVIAS_TORRENCIALES, Moto), 90);
        limites.put(new ClimaTipoVehiculo(LLUVIAS_TORRENCIALES, Tractor), 60);
    }
    private Integer obtenerLimite(TipoClima tipoClima, DatosVehiculo datosVehiculo) {
        var climaTipoV = new ClimaTipoVehiculo(tipoClima, datosVehiculo.tipoVehiculo);
        return limites.get(climaTipoV);
    }
    private Boolean esMulta(TipoClima tipoClima, DatosVehiculo datosVehiculo) {
        var climaTipoV = new ClimaTipoVehiculo(tipoClima, datosVehiculo.tipoVehiculo);
        Integer limitePermitido = limites.get(climaTipoV);

        return (datosVehiculo.velocidadMedida > limitePermitido);
    }
    public void evaluar(TipoClima tipoClima, DatosVehiculo datosVehiculo) {
        System.out.println("Evaluando la patente: " + datosVehiculo.patente);

        logger.info(tipoClima.name());
        logger.info(datosVehiculo.patente);
        logger.info(datosVehiculo.tipoVehiculo.name());
        logger.info(datosVehiculo.velocidadMedida+"");

        if (!esMulta(tipoClima, datosVehiculo)) {
            return;
        }

        var tkt = new Ticket(
                UUID.randomUUID().toString(),
                System.currentTimeMillis() / 1000,
                datosVehiculo.patente,
                datosVehiculo.tipoVehiculo,
                tipoClima,
                obtenerLimite(tipoClima, datosVehiculo),
                datosVehiculo.velocidadMedida
        );

        grabadorTicket.grabar(tkt);
    }
}
