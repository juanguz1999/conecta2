package com.colegios_peruanos.conectados.controladores;

import com.colegios_peruanos.conectados.dao.usuarioDao;
import com.colegios_peruanos.conectados.modelos.Usuario;
import com.colegios_peruanos.conectados.servicio.usuarioServicio;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class usuarioController {
    @Autowired
    private usuarioDao usuarioDao;

    @Autowired
    private usuarioServicio usuarioservicio;

    @GetMapping("/VerificarDatosGuardadosAdmin/{email}")
    public String VerificarDatosGuardados(@PathVariable String email) {

        // Verificar que el valor de tipoUsuario sea válido
        Usuario usuarioExistente = usuarioservicio.buscarPorCorreo(email);
        System.out.println("--------------" + email);
        if (usuarioExistente == null) {
            return "redirect:/guardarUser";
        } else {
            return "redirect:/vistaprincipal";
        }
    }

    @GetMapping("/VerificarDatosGuardadosDocente/{email}")
    public String VerificarDatosGuardadosDc(@PathVariable String email) {

        // Verificar que el valor de tipoUsuario sea válido
        Usuario usuarioExistente = usuarioservicio.buscarPorCorreo(email);
        System.out.println("--------------" + email);
        if (usuarioExistente == null) {
            return "redirect:/docente/guardarUserDocente";
        } else {
            return "redirect:/vistaprincipal";
        }
    }

    @GetMapping("/VerificarDatosGuardadosEstudiante/{email}")
    public String VerificarDatosGuardadosEs(@PathVariable String email) {

        // Verificar que el valor de tipoUsuario sea válido
        Usuario usuarioExistente = usuarioservicio.buscarPorCorreo(email);
        System.out.println("--------------" + email);
        if (usuarioExistente == null) {
            return "redirect:/estudiante/guardarUserEstudiante";
        } else {
            return "redirect:/vistaprincipal";
        }
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(HttpServletRequest request) {

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correoElectronico = request.getParameter("correoElectronico");
        String tipoUsuario = request.getParameter("tipoUsuario");

        // Verificar que el valor de tipoUsuario sea válido
        if (tipoUsuario != null && !tipoUsuario.isEmpty()) {
            tipoUsuario = tipoUsuario.toLowerCase();
            if (tipoUsuario.equals("docente") || tipoUsuario.equals("estudiante") ||
                    tipoUsuario.equals("padre") || tipoUsuario.equals("administración")) {
                // El valor de tipoUsuario es válido, crear el objeto Usuario
                Usuario usuario = new Usuario();
                usuario.setId(null);
                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                usuario.setCorreoElectronico(correoElectronico);
                usuario.setContrasena(null);
                usuario.setTipoUsuario(tipoUsuario);
                usuario.setFechaRegistro(new Date());

                usuarioDao.save(usuario);
            } else {
                // El valor de tipoUsuario no es válido
            }
        } else {
            // El valor de tipoUsuario está vacío o no está presente
        }
        return "redirect:/vistaprincipal";
    }

    @PostMapping("/guardarUsuarioModificado")
    public String guardarUsuarioModificado(HttpServletRequest request) {

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correoElectronico = request.getParameter("correoElectronico");
        String tipoUsuario = request.getParameter("tipoUsuario");

        // Verificar que el valor de tipoUsuario sea válido
        Usuario usuarioExistente = usuarioservicio.buscarPorCorreo(correoElectronico);
        if (usuarioExistente == null) {
            if (tipoUsuario != null && !tipoUsuario.isEmpty()) {
                tipoUsuario = tipoUsuario.toLowerCase();
                if (tipoUsuario.equals("docente") || tipoUsuario.equals("estudiante") ||
                        tipoUsuario.equals("padre") || tipoUsuario.equals("administración")) {
                    // El valor de tipoUsuario es válido, crear el objeto Usuario
                    Usuario usuario = new Usuario();
                    usuario.setId(null);
                    usuario.setNombre(nombre);
                    usuario.setApellido(apellido);
                    usuario.setCorreoElectronico(correoElectronico);
                    usuario.setContrasena(null);
                    usuario.setTipoUsuario(tipoUsuario);
                    usuario.setFechaRegistro(new Date());

                    usuarioDao.save(usuario);
                } else {
                    // El valor de tipoUsuario no es válido
                }
            } else {
                // El valor de tipoUsuario está vacío o no está presente
            }
        } else {
            usuarioExistente.setNombre(nombre);
            usuarioExistente.setApellido(apellido);
            usuarioExistente.setCorreoElectronico(correoElectronico);
            usuarioExistente.setTipoUsuario(tipoUsuario);
            usuarioservicio.guardar(usuarioExistente);
        }

        return "redirect:/vistaprincipal";
    }

    @GetMapping("/administrarPerfiles")
    public String mostrarUsuarios(Model model, HttpServletRequest request) {
        List<Usuario> usuarios = usuarioservicio.listar();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("url", getFullUrl(request));
        return "administrarPerfil";
    }

    public String getFullUrl(HttpServletRequest request) {

        if (request.getQueryString() == null) {
            return request.getRequestURI();
        }

        return request.getRequestURI() + "?" + request.getQueryString();
    }

    @GetMapping("/eliminarUsuario")
    public String eliminarUsuario(Usuario usuario) {
        usuarioservicio.eliminar(usuario);
        return "redirect:/administrarPerfiles";
    }

    @GetMapping("/editarUsuario/{id}")
    public String editarUsuario(Usuario usuario, Model model) {
        usuario = usuarioservicio.buscar(usuario);
        model.addAttribute("usuario", usuario);
        return "editarUsuario";
    }

}