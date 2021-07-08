package edu.it.components;

import com.bolivarsoft.sensorclima.SensorClima;
import com.bolivarsoft.sensorvelocidad.SensorVelocidad;
import edu.it.services.EvaluadorDeMultas;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SensorPermanente implements CommandLineRunner {
    EvaluadorDeMultas evaluadorDeMultas;

    public SensorPermanente(EvaluadorDeMultas evaluadorDeMultas) {
        this.evaluadorDeMultas = evaluadorDeMultas;
    }

    public void run(String... args) throws Exception {
        var sc = new SensorClima();
        var sv = new SensorVelocidad();
        var tc = sc.sensar();


        System.out.println(tc);

        for (;;) {
            var datosVehiculo = sv.sensarVehiculo();
            System.out.println(datosVehiculo.patente);
            System.out.println(datosVehiculo.tipoVehiculo);
            System.out.println(datosVehiculo.velocidadMedida);
            evaluadorDeMultas.evaluar(tc, datosVehiculo);
            System.out.println("");
        }
    }
}
