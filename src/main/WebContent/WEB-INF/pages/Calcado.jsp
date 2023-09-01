<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Cadastro de Calçado</title>
</head>
<body>

<h2>Formulário de Cadastro de Calçado</h2>
<form:form method = "POST" action = "/calcadoModel/addCalcadoModel">
    <table>
        <tr>
            <td><form:label path="tamanho">Tamanho><
            /form:label></td>
            <td><form:input path="tamanho" /></td>
        </tr>
        <tr>
            <td><form:label path="categoria">Categoria:<
            /form:label></td>
            <td><form:input path="categoria" /></td>
        </tr>
        <tr>
            <td><form:label path="cor">Cor:<
            /form:label></td>
            <td><form:input path="cor" /></td>
        </tr>
        <tr>
            <td><form:label path="preco">Preço:<
            /form:label></td>
            <td><form:textarea path="preco" /></td>
        </tr>
        <tr>
            <td><form:label path="dataCadastro">DataCadastro:<
            /form:label></td>
            <td><form:input path="dataCadastro" /></td>
        </tr>
        <tr>
            <td><form:label path="qtdEstoque">QtdEstoque:<
            /form:label></td>
            <td><form:textarea path="qtdEstoque" /></td>
        </tr>
        <tr>
            <td><form:label path="descricao">Descrição:<
            /form:label></td>
            <td><form:textarea path="descricao" /></td>
        </tr>
        <tr>
            <td><form:label path="categoria">Categoria<
            /form:label></td>
            <td><form:textarea path="categoria" /></td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
    </form:form>
    <a href="/calcadoModel/listarCalcadoModelss">Lista</a>

    </body>
    </html>
