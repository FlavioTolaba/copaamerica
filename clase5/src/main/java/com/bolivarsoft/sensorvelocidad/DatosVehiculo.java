package com.bolivarsoft.sensorvelocidad;

public class DatosVehiculo {
    public String patente;
    public TipoVehiculo tipoVehiculo;
    public int velocidadMedida;
    
    @Override
    public String toString() {
        return 
            patente + " >> " + 
            tipoVehiculo + " " + 
            velocidadMedida;
    }
}
