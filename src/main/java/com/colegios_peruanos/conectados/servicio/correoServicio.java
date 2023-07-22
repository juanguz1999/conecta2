package com.colegios_peruanos.conectados.servicio;

import com.colegios_peruanos.conectados.modelos.Comunicado;
import com.colegios_peruanos.conectados.modelos.Usuario;
import java.text.ParseException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class correoServicio {
    
    @Autowired
    private JavaMailSender mail;

    public void enviar_correo(Comunicado comunicado, Usuario usuario) throws MessagingException, ParseException {

        try {
            
            //datos del correo
            String TipoComunicado = comunicado.getTipo();
            String descripcion = comunicado.getDescripcion();
            String curso = comunicado.getCursoID().getNombreCurso();
            String grado = comunicado.getGradoID().getNombreGrado();
            String seccion = comunicado.getSeccionID().getNombreSeccion();

            //datos del usuario
            String correo= usuario.getCorreoElectronico();
            String Nombres=usuario.getNombre()+" "+usuario.getApellido();

            // Otros campos relacionados con la mesa de ayuda
            MimeMessage email = mail.createMimeMessage();
            MimeMessageHelper helper;

            helper = new MimeMessageHelper(email, true);

            // Datos que se obtendran: Nombre y apellido de usuario, numero de ticket, asunto, hora de ticket.
            helper.setTo("u21101038@utp.edu.pe"); // variara segun el correo del usuario
            helper.setFrom("e0001@alincoln.edu.pe"); // no cambia
            helper.setSubject("Comunicado Colegios Peruanos");
            String content = "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<head>\n"
                    + "<style>\n"
                    + "table {\n"
                    + "  border-collapse: collapse;\n"
                    + "  width: 100%;\n"
                    + "}\n"
                    + "\n"
                    + "th, td {\n"
                    + "  text-align: left;\n"
                    + "  padding: 8px;\n"
                    + "}\n"
                    + "\n"
                    + "tr:nth-child(even){background-color: #f2f2f2}\n"
                    + "\n"
                    + "th {\n"
                    + "  background-color: #4CAF50;\n"
                    + "  color: white;\n"
                    + "}\n"
                    + "</style>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "\n"
                    + "<h2> Estimad@ " + Nombres + " se te enviamos el comunicado a su correo: " + correo + "</h2>\n"
                    + "\n"
                    + "<table>\n"
                    + "  <tr>\n"
                    + "    <th>Numero de Ticket</th>\n"
                    + "    <th>Descripción</th>\n"
                    + "    <th>Categoria</th>\n"
                    + "    <th>Urgencia</th>\n"
                    + "    <th>Estado</th>\n"
                    + "  </tr>\n"
                    + "  <tr>\n"
                    + "    <td>" + TipoComunicado + "</td>\n"
                    + "    <td>" + descripcion + "</td>\n"
                    + "    <td>" + curso + "</td>\n"
                    + "    <td>" + grado + "</td>\n"
                    + "    <td>" + seccion + "</td>\n"
                    + "  </tr>\n"
                    + "</table>\n"
                    + "\n"
                    + "</body>\n"
                    + "</html>";
            helper.setText(content, true);

            mail.send(email);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
