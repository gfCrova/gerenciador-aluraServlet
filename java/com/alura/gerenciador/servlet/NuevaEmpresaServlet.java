package com.alura.gerenciador.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/NuevaEmpresa")
public class NuevaEmpresaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Empresa registrada");
		
		String nombreEmpresa = request.getParameter("nombre");
		String paramFechaAbertura = request.getParameter("fecha");
		
		Date fechaAbertura = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			fechaAbertura = sdf.parse(paramFechaAbertura);
		} catch (ParseException e) {
			throw new ServletException(e);
		}
		
		Empresa empresa = new Empresa();
		empresa.setNombre(nombreEmpresa);
		empresa.setFechaAbertura(fechaAbertura);
		
		DB baseDeDatos = new DB();
		baseDeDatos.agregarEmpresa(empresa);

		// Llamar al JSP
		//RequestDispatcher rd = request.getRequestDispatcher("/EmpresaRegistrada.jsp");
		request.setAttribute("empresas", empresa.getNombre());
		//rd.forward(request, response);
		
		//redireccionamiento al navegador
        response.sendRedirect("ListarEmpresas");
	}

}
