package servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.Controladora;
import logica.Reparacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SvAutomovil", urlPatterns = {"/SvAutomovil"})
public class SvAutomovil extends HttpServlet {
    Controladora control = new Controladora();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        {
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        crearAutomovil(request);

        response.sendRedirect("servicio.jsp");
    }

    private void crearAutomovil(HttpServletRequest request) {
        List<Reparacion> reparaciones = new ArrayList<>();
        String placa = request.getParameter("placa");
        String marca = request.getParameter("marca");
        String anioFab = request.getParameter("anioFab");


        HttpSession misession = request.getSession(true);
        misession.setAttribute("placa", placa);
        control.crearAutomovil(placa, marca, anioFab, reparaciones);
    }
}