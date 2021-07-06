package edu.it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        return () -> System.out.println("Esto es un bean nuevo");
    }
}

interface AA {
    public void m1();
}

// @Component
// @Primary
class A1 implements AA {
    public void m1() {
        System.out.println("Es una adaptacion de m1 *****");
    }
}

// @Component
class CulquierOtroNombre implements AA {
    public void m1() {
        System.out.println("Estoy en m1");
    }
}

@Component
class B {
    AA a;

    public B(AA a) {
        this.a = a;
    }

    public void llamar10Vecesm1() {
        for (int x : new int[10]) {
            a.m1();
        }
    }
}
