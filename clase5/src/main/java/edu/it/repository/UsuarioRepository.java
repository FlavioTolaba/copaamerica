package edu.it.repository;

import edu.it.entities.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    public List<Usuario> findByNombreStartingWith(String n);
    public List<Usuario> findByNombre(String n);
}
