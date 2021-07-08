package edu.it.entities;

import com.bolivarsoft.sensorclima.TipoClima;
import com.bolivarsoft.sensorvelocidad.TipoVehiculo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ticket {
    @Id
    public String id;
    @Column(name="timestmp")
    public Long timeStamp;
    public String patente;
    @Column(name="tipo_vehiculo")
    public TipoVehiculo tipoVehiculo;
    @Column(name="tipo_clima")
    public TipoClima tipoClima;
    @Column(name="limite_permitido")
    public Integer limitePermitido;
    @Column(name="velocidad_medida")
    public Integer velocidadMedida;

    public Ticket(String id, Long timeStamp, String patente, TipoVehiculo tipoVehiculo, TipoClima tipoClima, Integer limitePermitido, Integer velocidadMedida) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.patente = patente;
        this.tipoVehiculo = tipoVehiculo;
        this.tipoClima = tipoClima;
        this.limitePermitido = limitePermitido;
        this.velocidadMedida = velocidadMedida;
    }

    public Ticket() {
    }
}
