package edu.it.entities;

import javax.persistence.*;

@Entity
@Table(name="usuarios")
public class Usuario {
    @Id
    public String id;
    public String nombre;
    public String password;
    public String salt;
    public String apellido;
    public String pais;
}
