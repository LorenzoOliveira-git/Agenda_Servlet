package com.agenda.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/controller", "/main"})
public class Controller extends HttpServlet{
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getServletPath();
        if (acao.equals("/main")){
            contatos(request,response);
        }
    }

//        Listar contatos
    protected void contatos(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("agenda.jsp");
    }
}
