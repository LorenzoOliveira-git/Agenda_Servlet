<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%@ page import="com.agenda.MODEL.JavaBeans"%>
<%@ page import="java.util.ArrayList" %>
<%ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>)
        request.getAttribute("contatos");
%>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <title>Agenda de Contatos</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h1>Agenda de Contatos</h1>
    <a href="novo.html" class="botao">Novo Contato</a>
    <table id="tabela">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Telefone</th>
                <th>E-mail</th>
                <th>Opções</th>
            </tr>
        </thead>
        <tbody>
            <%for (int i =0; i< lista.size(); i++){ %>
                <tr>
                    <td><%=lista.get(i).getIdcon()%></td>
                    <td><%=lista.get(i).getNome()%></td>
                    <td><%=lista.get(i).getFone()%></td>
                    <td><%=lista.get(i).getEmail()%></td>
                    <td><a href="modificar?id=<%=lista.get(i).getIdcon()%>"
                           class="botao"></a></td>
                </tr>
            <%}%>
        </tbody>
    </table>
</body>
</html>
