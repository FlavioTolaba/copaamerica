package edu.it.model;

import com.bolivarsoft.sensorclima.TipoClima;
import com.bolivarsoft.sensorvelocidad.TipoVehiculo;

public class ClimaTipoVehiculo {
    public final TipoClima tp;
    public final TipoVehiculo tv;

    public ClimaTipoVehiculo(TipoClima tp, TipoVehiculo tv) {
        this.tp = tp;
        this.tv = tv;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClimaTipoVehiculo other = (ClimaTipoVehiculo) obj;
        if (this.tp != other.tp) {
            return false;
        }
        if (this.tv != other.tv) {
            return false;
        }
        return true;
    }
}
