package edu.it.aspect;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectoMedicionDeTiempo {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(AspectoMedicionDeTiempo.class);

    @Value("${performance.path}")
    private String pathPerformance;

    @Around("@annotation(edu.it.annotations.Medible)")
    public void encapsular(ProceedingJoinPoint joinPoint) {
        try {
            long ts1 = System.currentTimeMillis();
            joinPoint.proceed();
            long ts2 = System.currentTimeMillis();
            var diff = String.valueOf(ts2 - ts1);

            var strOut = String.join(": ", joinPoint.getSignature().getName(), diff);
            strOut = strOut + System.lineSeparator();

            var f = new File(pathPerformance);
            FileUtils.write(f, strOut, "utf-8", true);
        }
        catch (Throwable ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }
}
