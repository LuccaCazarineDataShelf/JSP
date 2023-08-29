<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Exibe Detalhes do Calcado</title>
</head>
<body>

<h2>Detalhes do Calcado Cadastrado</h2>
   <table>
    <tr>
        <td>Tamanho:</td>
        <td>${tamanho}</td>
    </tr>
    <tr>
        <td>Categoria:</td>
        <td>${categoria}</td>
    </tr>
    <tr>
        <td>Cor:</td>
        <td>${cor}</td>
    </tr>
    <tr>
        <td>Preço:</td>
        <td>${preco}</td>
    </tr>
    <tr>
        <td>DataCadastro:</td>
        <td>${dataCadastro}</td>
    </tr>
    <tr>
        <td>QtdEstoque:</td>
        <td>${qtdEstoque}</td>
    </tr>
    <tr>
        <td>Descrição:</td>
        <td>${descricao}</td>
    </tr>
    <tr>
        <td>CalcadoId:</td>
        <td>${calcadoId}</td>
    </tr>

</table>
<a href="/calcadoModel/calcadoModel">Cadastro</a>
<a href="/calcadoModel/listarCalcadoModels">Lista</a>

</body>
</html>