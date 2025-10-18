package com.agenda.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.agenda.MODEL.DAO;
import com.agenda.MODEL.JavaBeans;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/controller", "/main", "/inserir", "/modificar"})
public class Controller extends HttpServlet{

    DAO dao = new DAO();
    JavaBeans contato = new JavaBeans();


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getServletPath();
        if (acao.equals("/main")) {
            contatos(request, response);
        } else if (acao.equals("/inserir")) {
            novoContato(request, response);
        } else if( acao.equals("/modificar")){

        }
        else {
            response.sendRedirect("index.html");
        }
    }

//        Listar contatos
    protected void contatos(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        ArrayList<JavaBeans> lista = dao.listarContatos();
//        Encaminhar a lista para o documento JSP
        request.setAttribute("contatos",lista);
        RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
        rd.forward(request,response);
    }
//    Novo Contato
    protected void novoContato(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException{

//        Armazenando os parâmetros dentro de variáveis
        String nome = request.getParameter("nome");
        String fone = request.getParameter("fone");
        String email = request.getParameter("email");

//        Setando os atributos dentro do objeto JavaBeans
        contato.setNome(nome);
        contato.setFone(fone);
        contato.setEmail(email);

//        método do CRUD inserir contato passando o objeto contato
        dao.inserirContato(contato);

        response.sendRedirect("main");
    }

    public void modificarContato(HttpServletRequest request,
                                 HttpServletResponse response){
        JavaBeans javaBeans = new JavaBeans();

        javaBeans.setIdcon(Integer.parseInt(request.getParameter("id")));

        dao.listarContatos(javaBeans.getIdcon());
    }
}
