package com.agenda.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = {"/validarLogin","/validarCadastro"})
public class Validar extends HttpServlet {
    String regexSenha = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\W\\_])" +
            "\\S{6,}$";
    String regexEmail = "^[\\w\\W]+\\@[a-zA-Z]+(\\.com|\\.br|\\.org)+$";

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException{
        String acao = request.getServletPath();

        if(acao.equals("/validarLogin")){
            validarLogin(request,response);
        } else if(acao.equals("/validarCadastro")){
            validarCadastro(request,response);
        }
    }

    protected void validarLogin(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException,
            IOException {
        Pattern patternSenha = Pattern.compile(regexSenha);
        Pattern patternEmail = Pattern.compile(regexEmail);

        Matcher matcherSenha =
                patternSenha.matcher(request.getParameter("senha"));
        Matcher matcherEmail =
                patternEmail.matcher(request.getParameter("email"));

        if( matcherEmail.find() && matcherSenha.find()){
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("senha", request.getParameter("senha"));
            request.getRequestDispatcher("login").forward(request,response);
        }else{
            response.sendRedirect("index.jsp");
        }
    }

    protected void validarCadastro(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException,
            IOException {
        Pattern patternSenha = Pattern.compile(regexSenha);
        Pattern patternEmail = Pattern.compile(regexEmail);

        Matcher matcherSenha =
                patternSenha.matcher(request.getParameter("senha"));
        Matcher matcherEmail =
                patternEmail.matcher(request.getParameter("email"));

        if( matcherEmail.find() && matcherSenha.find()){
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("senha", request.getParameter("senha"));
            request.getRequestDispatcher("cadastro").forward(request,response);
        }else{
            response.sendRedirect("cadastro.jsp");
        }
    }
}
