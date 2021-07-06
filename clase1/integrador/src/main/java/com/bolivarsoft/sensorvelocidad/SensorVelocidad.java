package com.bolivarsoft.sensorvelocidad;

public class SensorVelocidad {    
    public DatosVehiculo sensarVehiculo() {
        try { Thread.sleep(random(3000)); } catch( Exception ex) {}
        
        String[] letters = {"Camion", "Auto", "Tractor", "Moto"};
        int[] limiteVelocidad = {90, 130, 60, 130};
        
        DatosVehiculo v = new DatosVehiculo();
        
        int idx = random(4);
        
        switch (idx) {
            case 0:
                v.tipoVehiculo = TipoVehiculo.Auto;
                break;
            case 1:
                v.tipoVehiculo = TipoVehiculo.Moto;
                break;
            case 2:
                v.tipoVehiculo = TipoVehiculo.Camion;
                break;
            case 3:
                v.tipoVehiculo = TipoVehiculo.Tractor;
                break;
        }
        
        v.velocidadMedida = limiteVelocidad[idx] - 15 + (new java.util.Random().nextInt() % 25);
        v.patente = "A" + getLetter() + " " + get3Digit() + " " + getLetter() + getLetter();
        
        return v;
    }
    private int random(int base) {
        int x = new java.util.Random().nextInt() % base;
        return Math.abs(x);
    }
    private String get3Digit() {
        int x = random(999);
        if (x < 10) {
            return "00" + String.valueOf(x);
        }
        if (x < 100) {
            return "0" + String.valueOf(x);
        }
        return String.valueOf(x);
    }
    private String getLetter() {
        int x = random(24);
        String[] letters = {"A","B","C","D","E","F","G","H","I","J","K","M","M","L","O","P","Q","R","S","T","U","V","W","X","Y","Z"}; 
        
        return letters[x];
    }
}
