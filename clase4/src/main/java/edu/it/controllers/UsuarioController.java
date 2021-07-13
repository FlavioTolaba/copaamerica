package edu.it.controllers;

import com.github.javafaker.Faker;
import edu.it.entities.Usuario;
import edu.it.repository.UsuarioRepository;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepo;

    @ResponseBody
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public Object login(@RequestBody Usuario u, HttpServletResponse response) {
        Integer respuesta;

        var lstUsu = usuarioRepo.findByNombre(u.nombre);

        if (lstUsu.size() == 0) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "";
        }

        if (lstUsu.size() > 1) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "";
        }

        u.salt = lstUsu.get(0).salt;
        String passwordEncriptado = calcularPassword(u);

        System.out.println(passwordEncriptado);
        System.out.println(lstUsu.get(0).password);

        if (lstUsu.get(0).password.equals(passwordEncriptado)==false) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return "";
        }

        response.setStatus(HttpServletResponse.SC_OK);
        return UUID.randomUUID().toString();
    }

    @ResponseBody
    @RequestMapping(value="/inventarusuario", method=RequestMethod.GET)
    public Object inventarUsuario() {
        Faker faker = new Faker();

        Usuario u = new Usuario();

        u.id = UUID.randomUUID().toString();
        u.nombre = faker.address().firstName();
        u.password = UUID.randomUUID().toString();
        u.salt = UUID.randomUUID().toString();
        u.apellido = faker.address().lastName();
        u.pais = faker.address().country();

        return u;
    }

    @ResponseBody
    @RequestMapping(value="/prueba", method=RequestMethod.POST)
    public Object prueba(@RequestBody Usuario u, HttpServletResponse response) {
        return calcularPassword(u);
    }

    private Object todosLosUsuarios() {
        return usuarioRepo.findAll();
    }

    private Object buscarXNombre(String nombre) {
        return usuarioRepo.findByNombreStartingWith(nombre);
    }

    private String calcularPassword(Usuario u) {
        String passClear = u.password + u.salt;
        String hex256 = Hex.encodeHexString(DigestUtils.sha256(passClear));
        return hex256;
    }

    @ResponseBody
    @RequestMapping(value="/usuario", method=RequestMethod.GET)
    public Object busquedaPorParams(
            @RequestParam(value = "nombre", required = false,
                    defaultValue = "noVino") String nombre) {

        if (nombre.equals("noVino")) {
            return todosLosUsuarios();
        }
        return buscarXNombre(nombre);
    }

    @RequestMapping(value="/usuario", method=RequestMethod.POST)
    public void doPost(@RequestBody Usuario u, HttpServletResponse response) {
        Integer respuesta = 200;

        try {
            u.password = calcularPassword(u);
            usuarioRepo.save(u);
            respuesta = HttpServletResponse.SC_CREATED;
        }
        catch (Exception ex) {
            System.out.println("Causa Raiz");
            System.out.println(ex.getCause());
            respuesta = HttpServletResponse.SC_BAD_REQUEST;
        }
        response.setStatus(respuesta);
    }

    @RequestMapping(value="/usuario/{id}", method=RequestMethod.PUT)
    public void doPut(@RequestBody Usuario u,
            @PathVariable String id,
            HttpServletResponse response) {

        Integer respuesta = 200;

        try {
            Optional<Usuario> optUsu = usuarioRepo.findById(id);
            if (optUsu.isPresent()) {
                usuarioRepo.save(u);
            }
            else {
                respuesta = HttpServletResponse.SC_NOT_FOUND;
            }
        }
        catch (Exception ex) {
            respuesta = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        }

        response.setStatus(respuesta);
    }

    @RequestMapping(value="/usuario/{id}", method=RequestMethod.DELETE)
    public void doDelete(@PathVariable String id, HttpServletResponse response) {
        Integer respuesta = 200;

        try {
            // un find para ver si existe el objeto
            // y si existe un remove
            Optional<Usuario> optUsu = usuarioRepo.findById(id);
            if (optUsu.isPresent()) {
                usuarioRepo.delete(optUsu.get());
            }
            respuesta = HttpServletResponse.SC_OK;
        }
        catch (Exception ex) {
            respuesta = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        }

        response.setStatus(respuesta);
    }
}
