<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%@ page import="com.agenda.MODEL.Contato"%>
<%@ page import="java.util.ArrayList" %>
<%ArrayList<Contato> lista = (ArrayList<Contato>)
        request.getAttribute("contatos");
%>
<%--<html lang="pt-br">--%>
<%--<head>--%>
<%--    <meta charset="utf-8">--%>
<%--    <title>Agenda de Contatos</title>--%>
<%--    <link rel="stylesheet" href="style.css">--%>
<%--</head>--%>
<%--<body>--%>
<%--    <h1>Agenda de Contatos</h1>--%>
<%--        <table id="tabela">--%>
<%--            <thead>--%>
<%--                <tr>--%>
<%--                    <th>Nome</th>--%>
<%--                    <th>Telefone</th>--%>
<%--                    <th>E-mail</th>--%>
<%--                    <th>Opções</th>--%>
<%--                </tr>--%>
<%--            </thead>--%>
<%--            <tbody>--%>
<%--                <%for (int i =0; i< lista.size(); i++){ %>--%>
<%--                    <tr>--%>
<%--                        <td><%=lista.get(i).getNome()%></td>--%>
<%--                        <td><%=lista.get(i).getFone()%></td>--%>
<%--                        <td><%=lista.get(i).getEmail()%></td>--%>
<%--                        <td><a href="modificar?idcon=<%=lista.get(i).getIdcon()%>"--%>
<%--                               class="botao">Alterar</a>--%>
<%--                            <a href="javascript: confirmar(<%=lista.get(i).getIdcon()%>)"--%>
<%--                               class="botao2">Excluir</a>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                <%}%>--%>
<%--            </tbody>--%>
<%--        </table>--%>
<%--    <script src="scripts/confirmador.js"></script>--%>
<%--</body>--%>
<%--</html>--%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap');
    </style>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <title>Agenda</title>
</head>
<body>
    <div class="containerAgenda">
        <header>
            <img src="img/logoAPP.png" alt="logo Meu contato">
            <a href="deslogar"><i class="bi bi-person-circle"></i></a>
        </header>
        <main>
            <aside>
                <div  class="botao">
                    <a href="novo.html">Novo Contato</a>
                </div>
                <div class="botao">
                    <a href="report">Relatório</a>
                </div>
            </aside>
            <div class="tabela">
                <table>
                    <thead>
                    <tr class="Inter">
                        <th>Nome</th>
                        <th>Telefone</th>
                        <th>E-mail</th>
                        <th>Opções</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%for (int i =0; i< lista.size(); i++){ %>
                        <tr>
                            <td><%=lista.get(i).getNome()%></td>
                            <td><%=lista.get(i).getFone()%></td>
                            <td><%=lista.get(i).getEmail()%></td>
                            <td><a href="modificar?idcon=<%=lista.get(i).getIdcon()%>"
                                   class="alterar"><i class="bi bi-pencil-square"></i></a>
                                <a href="javascript: confirmar(<%=lista.get(i).getIdcon()%>)"
                                   class="excluir"><i class="bi bi-trash"></i></a>
                            </td>
                        </tr>
                    <%}%>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
<script src="scripts/confirmador.js"></script>
</body>
</html>
