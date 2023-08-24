<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Lista de Calcados Cadastrados</title>
</head>
<body>
    <h2>Lista de Calcados Cadastrados</h2>

    <c:if test="${not empty calcadoModels}">
        <table>
            <tr>
                <td>Tamanho</td>
                <td>Categoria</td>
                <td>Cor</td>
                <td>Preço</td>
                <td>DataCadastro</td>
                <td>QtdEstoque</td>
                <td>Descrição</td>
                <td>CalcadoId</td>

            </tr>


            <c:forEach var="listValue" items="${calcadoModels}">

                <tr>
                    <td>${listValue.tamanho}</td>
                    <td>${listValue.categoria}</td>
                    <td>${listValue.cor}</td>
                    <td>${listValue.preco}</td>
                    <td>${listValue.dataCadastro}</td>
                    <td>${listValue.qtdEstoque}</td>
                    <td>${listValue.descricao}</td>
                    <td>${listValue.calcdoId}</td>
                    <td><fmt:formatDate pattern="dd/MM/yyyy"
                    value="${listValue.dataNascimento}" /></td>
                </tr>
            </c:forEach>

        </table>

    </c:if>
    <a href="/calcadoModel/calcadoModel">Cadastro</a>

</body>
</html>