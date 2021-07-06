package edu.it;

import org.springframework.stereotype.Component;

@Component
public class AutoInstanciable {
    public AutoInstanciable() {
        System.out.println("SE HA INSTANCIADO LA CLASE AUTOINSTANCIABLE*****");
    }
}
