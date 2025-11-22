<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Login</title>
</head>
<body>
    <div class="containerCadastro">
        <div class="login">
            <div class="informacoes">
                <h1>Faça seu cadastro</h1>
                <form action="validarCadastro" method="post">
                    <input type="text" name="email" id="email" class="caixa" placeholder="E-mail">
                    <br>
                    <input type="password" name="senha" id="senha" class="caixa" placeholder="Senha">
                    <br>
                    <input type="submit" value="Enviar" id="botao">
                </form>
            </div>
        </div>
    </div>

</body>
</html>