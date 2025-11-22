<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" %>
<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>Agenda de contatos</title>--%>
<%--    <link rel="stylesheet" href="style.css">--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>Editar Contato</h1>--%>
<%--<form action="update" name="update">--%>
<%--    <table>--%>
<%--        <tr>--%>
<%--            <td><input type="text" name="nome" class="caixa1"--%>
<%--                       value="<%=request.getAttribute("nome")%>"></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td><input type="text" name="fone" class="caixa2"--%>
<%--                       value="<%=request.getAttribute("fone")%>"></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td><input type="text" name="email" class="caixa1"--%>
<%--                       value="<%=request.getAttribute("email")%>"></td>--%>
<%--        </tr>--%>
<%--    </table>--%>
<%--    <input type="submit" value="adicionar" class="botao" onclick="validar()">--%>
<%--</form>--%>
<%--<script src="scripts/validador.js"></script>--%>
<%--</body>--%>
<%--</html>--%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Editar contato</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
</head>
<body>
<header>
    <a href="main" class="logo"><img src="img/logoAPP.png"
                                  alt="logo Meu contato"></a>
    <a href=""><i class="bi bi-person-circle"></i></a>
</header>
<div class="containerEditar">
    <main class="criarContato">
        <h1>Editar contato</h1>
        <form action="update" name="update">
            <input type="hidden" name="idcon" value="<%=request.getAttribute("idcon")%>" id="esconder">
            <div>
                <input type="text" name="nome" placeholder="Nome"
                       class="caixa" value="<%=request.getAttribute("nome")%>">
                <i class="bi bi-person-fill"></i>
            </div>
            <div>
                <input type="text" name="fone" placeholder="Telefone"
                       class="caixa" value="<%=request.getAttribute("fone")%>">
                <i class="bi bi-telephone-fill"></i>
            </div>
            <div>
                <input type="text" name="email" placeholder="E-mail"
                       class="caixa" value="<%=request.getAttribute("email")%>">
                <i class="bi bi-envelope-fill"></i>
            </div>
            <input type="submit" value="Adicionar" class="botao"
                   onclick="validar()">
        </form>
    </main>
</div>
<script src="scripts/validador.js"></script>
</body>
</html>
