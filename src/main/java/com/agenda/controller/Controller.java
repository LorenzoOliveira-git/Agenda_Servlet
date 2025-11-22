package com.agenda.controller;

import com.agenda.MODEL.Usuario;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.agenda.MODEL.DAO;
import com.agenda.MODEL.Contato;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/cadastro","/login","/controller",
        "/main", "/inserir","/modificar", "/update", "/deletar", "/report",
        "/deslogar"})
public class Controller extends HttpServlet{

    DAO dao = new DAO();
    Contato contato = new Contato();
    Usuario usuario = new Usuario();

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException{
        String acao = request.getServletPath();
        if (acao.equals("/cadastro")){
            fazerCadastro(request,response);
        } else if (acao.equals("/login")){
            fazerLogin(request,response);
        } else if(acao.equals("/main")) {
            contatos(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getServletPath();
        if(acao.equals("/main")) {
            contatos(request, response);
        } else if (acao.equals("/inserir")) {
            novoContato(request, response);
        } else if( acao.equals("/modificar")){
            listarContato(request,response);
        } else if(acao.equals("/update")){
            modificarContato(request,response);
        } else if(acao.equals("/deletar")){
            deletarContato(request,response);
        } else if(acao.equals("/report")){
            gerarRelatorio(request,response);
        } else if(acao.equals("/deslogar")){
            deslogar(request,response);
        }  else {
            response.sendRedirect("index.jsp");
        }
    }

    protected void fazerCadastro(HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException{
        String email = (String) request.getAttribute("email");
        String senha = (String) request.getAttribute("senha");

        usuario.setEmail(email);
        usuario.setSenha(senha);

        dao.inserirConta(usuario);
        int id = usuario.getId();

        if(id != 0){
            HttpSession session = request.getSession(true);
            session.setAttribute("id",id);
            response.sendRedirect("main");
        }else{
            response.sendRedirect("cadastro.jsp");
        }
    }

    protected void fazerLogin(HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException{
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        usuario.setSenha(senha);
        usuario.setEmail(email);

        HttpSession session = request.getSession(true);
        dao.verificarLogin(usuario);
        int id = usuario.getId();

        if(id != 0){
            session.setAttribute("id",id);
            response.sendRedirect("main");
        }else{
            response.sendRedirect("index.jsp");
        }
    }

    protected void contatos(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        contato.setIdusuario((Integer) session.getAttribute("id"));

        ArrayList<Contato> lista = dao.listarContatos(contato);

        request.setAttribute("contatos",lista);
        request.getRequestDispatcher("agenda.jsp").forward(request,response);
    }

    protected void novoContato(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession(false);

        String nome = request.getParameter("nome");
        String fone = request.getParameter("fone");
        String email = request.getParameter("email");

        contato.setIdusuario((Integer) session.getAttribute("id"));
        contato.setNome(nome);
        contato.setFone(fone);
        contato.setEmail(email);

        dao.inserirContato(contato);

        response.sendRedirect("main");
    }

    protected void listarContato(HttpServletRequest request,
                                 HttpServletResponse response) throws ServletException, IOException{

        contato.setIdcon(Integer.parseInt(request.getParameter("idcon")));

        dao.selecionarContato(contato);

        request.setAttribute("idcon",contato.getIdcon());
        request.setAttribute("nome",contato.getNome());
        request.setAttribute("fone",contato.getFone());
        request.setAttribute("email",contato.getEmail());

        request.getRequestDispatcher("editar.jsp").forward(request,response);
    }

    protected void modificarContato(HttpServletRequest request,
                                 HttpServletResponse response) throws ServletException, IOException{

        contato.setIdcon(Integer.parseInt(request.getParameter("idcon")));
        contato.setNome(request.getParameter("nome"));
        contato.setFone(request.getParameter("fone"));
        contato.setEmail(request.getParameter("email"));

        dao.modificarContato(contato);

        response.sendRedirect("main");
    }

    protected void deletarContato(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException{

        contato.setIdcon(Integer.parseInt(request.getParameter("idcon")));

        dao.deletarContato(contato);

        response.sendRedirect("main");
    }

    protected void gerarRelatorio(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException{
        Document documento = new Document();
        try{
            response.setContentType("apllication/pdf");
            response.addHeader("Content-Disposition","inline; filename="+
                "contatos.pdf");

            PdfWriter.getInstance(documento,response.getOutputStream());

            documento.open();
            documento.add(new Paragraph("Lista de Contatos:"));
            documento.add(new Paragraph(" "));

            PdfPTable tabela = new PdfPTable(3);

            PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
            PdfPCell col2 = new PdfPCell(new Paragraph("Telefone"));
            PdfPCell col3 = new PdfPCell(new Paragraph("E-mail"));
            tabela.addCell(col1);
            tabela.addCell(col2);
            tabela.addCell(col3);

            HttpSession session = request.getSession(false);
            int idusuario = (Integer) session.getAttribute("id");
            contato.setIdusuario(idusuario);
            ArrayList<Contato> lista = dao.listarContatos(contato);
            for(int i = 0; i< lista.size(); i++){
                tabela.addCell(lista.get(i).getNome());
                tabela.addCell(lista.get(i).getFone());
                tabela.addCell(lista.get(i).getEmail());
            }
            documento.add(tabela);
            documento.close();

        }catch(Exception e){
            e.printStackTrace();
            documento.close();
        }
    }

    protected void deslogar(HttpServletRequest request,
                            HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession(false);
        session.invalidate();
        response.sendRedirect("index.jsp");
    }
}
