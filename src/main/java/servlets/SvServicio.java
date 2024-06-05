package servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.Automovil;
import logica.Controladora;
import logica.Reparacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SvServicio", urlPatterns = {"/SvServicio"})
public class SvServicio extends HttpServlet {

    Controladora control = new Controladora();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Reparacion> listaReparaciones = new ArrayList<>();

        listaReparaciones = control.getReparaciones();

        HttpSession misession = request.getSession();
        misession.setAttribute("listaReparaciones", listaReparaciones);


        response.sendRedirect("VerServicio.jsp");

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        crearReparacion(request);


        response.sendRedirect("index.jsp");
    }

    private void crearReparacion(HttpServletRequest request) {
        String descripcion = request.getParameter("descripcion");
        String costo = request.getParameter("costo");
        String tipoReparacion = request.getParameter("tipo");
        String placa = (String) request.getSession().getAttribute("placa");
        Automovil automovil = control.encontrarAuto(placa);
        control.crearReparacion(descripcion, costo, tipoReparacion, automovil);
    }

}

