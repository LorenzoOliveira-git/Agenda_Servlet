package com.agenda.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import java.util.Arrays;

@WebServlet(urlPatterns = {"/controller", "/main", "/inserir", "/modificar",
    "/update", "/deletar", "/report"})
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
            listarContato(request,response);
        } else if(acao.equals("/update")){
            modificarContato(request,response);
        } else if(acao.equals("/deletar")){
            deletarContato(request,response);
        } else if(acao.equals("/report")){
            gerarRelatorio(request,response);
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
        request.getRequestDispatcher("agenda.jsp").forward(request,response);
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

    protected void listarContato(HttpServletRequest request,
                                 HttpServletResponse response) throws ServletException, IOException{
//        Setar a variável JavaBeans com o id que veio do request
        contato.setIdcon(Integer.parseInt(request.getParameter("idcon")));
//        Selecionando o contato correspondente ao id
        dao.selecionarContato(contato);
//        Setar os atributos do formulário com o JavaBeans
        request.setAttribute("idcon",contato.getIdcon());
        request.setAttribute("nome",contato.getNome());
        request.setAttribute("fone",contato.getFone());
        request.setAttribute("email",contato.getEmail());
//        encaminhas os atributos para a página editar.jsp
        request.getRequestDispatcher("editar.jsp").forward(request,response);
    }

    protected void modificarContato(HttpServletRequest request,
                                 HttpServletResponse response) throws ServletException, IOException{
//        Setando os parametros no JavaBeans
        contato.setIdcon(Integer.parseInt(request.getParameter("idcon")));
        contato.setNome(request.getParameter("nome"));
        contato.setFone(request.getParameter("fone"));
        contato.setEmail(request.getParameter("email"));

//        Modificando um contato com o método da classe DAO
        dao.modificarContato(contato);

//        mandando para a página agenda.jsp
        response.sendRedirect("main");
    }

//    Deletar contato
    protected void deletarContato(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException{
//        setando o id na classe javabens
        contato.setIdcon(Integer.parseInt(request.getParameter("id")));

//        usar o método da classe DAO de deletar contato
        dao.deletarContato(contato);

//        redirecionar para a página agenda.jsp
        response.sendRedirect("main");
    }

    protected void gerarRelatorio(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException{
        Document documento = new Document();
        try{
//            tipo do conteúdo
            response.setContentType("apllication/pdf");
//            nome do documento
            response.addHeader("Content-Disposition","inline; filename="+
                "contatos.pdf");
//        criar o documento
            PdfWriter.getInstance(documento,response.getOutputStream());
//            abrir o documento para adicionar o conteúdo
            documento.open();
            documento.add(new Paragraph("Lista de Contatos:"));
            documento.add(new Paragraph(" "));
//            criar uma tabela
            PdfPTable tabela = new PdfPTable(3);
//            cabeçalho
            PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
            PdfPCell col2 = new PdfPCell(new Paragraph("Telefone"));
            PdfPCell col3 = new PdfPCell(new Paragraph("E-mail"));
            tabela.addCell(col1);
            tabela.addCell(col2);
            tabela.addCell(col3);
//            popular a tabela com os contatos
            ArrayList<JavaBeans> lista = dao.listarContatos();
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
}
