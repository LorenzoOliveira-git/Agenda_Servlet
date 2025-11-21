<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agenda de contatos</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h1>Editar Contato</h1>
<form action="update" name="update">
    <table>
        <tr>
            <td><input type="text" name="idcon" id="caixa3" readonly
                       value="<%=request.getAttribute("idcon")%>"></td>
        </tr>
        <tr>
            <td><input type="text" name="nome" class="caixa1"
                       value="<%=request.getAttribute("nome")%>"></td>
        </tr>
        <tr>
            <td><input type="text" name="fone" class="caixa2"
                       value="<%=request.getAttribute("fone")%>"></td>
        </tr>
        <tr>
            <td><input type="text" name="email" class="caixa1"
                       value="<%=request.getAttribute("email")%>"></td>
        </tr>
    </table>
    <input type="submit" value="adicionar" class="botao" onclick="validar()">
</form>
<script src="scripts/validador.js"></script>
</body>
</html>
