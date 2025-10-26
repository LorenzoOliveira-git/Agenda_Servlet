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
<form action="inserir" name="">
    <table>
        <tr>
            <td><input type="text" name="idcon" class="caixa3"></td>
        </tr>
        <tr>
            <td><input type="text" name="nome" class="caixa1"></td>
        </tr>
        <tr>
            <td><input type="text" name="fone" class="caixa2"></td>
        </tr>
        <tr>
            <td><input type="text" name="email" class="caixa1"></td>
        </tr>
    </table>
    <input type="button" value="adicionar" class="botao" onclick="validar()">
</form>
<script src="scripts/validador.js"></script>
</body>
</html>
