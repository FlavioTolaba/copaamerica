package edu.it;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Clase1Application {
    public static void main(String[] args) {
        SpringApplication.run(Clase1Application.class, args);
    }
}

@Component
class Z {
    private B b;

    public Z(B b) {
        this.b = b;
        b.llamar10Vecesm1();
    }
}

@Configuration
class claseFactory {
    @Bean
    public AA crearUnaInstanciaDeAA() {
        // return () -> System.out.println("Esto es un bean nuevo");
        return new A1();
    }
}

interface AA {
    public void m1();
}

@Component
@Primary
class A1 implements AA {
    @Value("${ticket.path}")
    private String path;

    public void m1() {
        System.out.println("Es una adaptacion de m1 ***** " + path);
    }
}

@Component
class CulquierOtroNombre implements AA {
    public void m1() {
        System.out.println("Estoy en m1");
    }
}

@Component
class B {
    // @Autowired
    private AA a;

    public B(AA a) {
        this.a = a;
    }

    public void llamar10Vecesm1() {
        for (int x : new int[10]) {
            a.m1();
        }
    }
}
