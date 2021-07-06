package edu.it.entities;

import com.bolivarsoft.sensorclima.TipoClima;
import com.bolivarsoft.sensorvelocidad.TipoVehiculo;


public class Ticket {
    public final String id;
    public final Long timeStamp;
    public final String patente;
    public final TipoVehiculo tipoVehiculo;
    public final TipoClima tipoClima;
    public final Integer limitePermitido;
    public final Integer velocidadMedida;

    public Ticket(String id, Long timeStamp, String patente, TipoVehiculo tipoVehiculo, TipoClima tipoClima, Integer limitePermitido, Integer velocidadMedida) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.patente = patente;
        this.tipoVehiculo = tipoVehiculo;
        this.tipoClima = tipoClima;
        this.limitePermitido = limitePermitido;
        this.velocidadMedida = velocidadMedida;
    }
}
