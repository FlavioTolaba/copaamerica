package edu.it.aspect;

import com.bolivarsoft.sensorclima.TipoClima;
import com.bolivarsoft.sensorvelocidad.DatosVehiculo;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectoTraceable {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(AspectoTraceable.class);

    @Value("${trace.path}")
    private String pathTrace;

    @Before("@annotation(edu.it.annotations.Traceable)")
    public void antesDeEjecutarTraceable(JoinPoint joinPoint) {
        Object[] dosArgumentos = joinPoint.getArgs();
        TipoClima tc = (TipoClima)dosArgumentos[0];
        DatosVehiculo dv = (DatosVehiculo)dosArgumentos[1];

        var strCSV = String.join(";",
                tc.name(),
                dv.patente,
                dv.tipoVehiculo.name(),
                String.valueOf(dv.velocidadMedida)
                );

        strCSV = strCSV + System.lineSeparator();

        File f = new File(pathTrace);

        try {
            FileUtils.write(f, strCSV, "utf-8", true);
        }
        catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }
}
