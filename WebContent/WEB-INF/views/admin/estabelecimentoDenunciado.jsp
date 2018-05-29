
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:import url="../padrao/headerAdmin.jsp" />

<div class="container">
	<div class="adminConteudo">
		<div class="tReinvidicados">
			<b><span class="glyphicon glyphicon-alert"></span>ESTABELECIMENTO
				DENUNCIADO</b><br>
		</div>
		<b>Nome: ${estabelecimento.nomeFantasia }</b><br> Endereço:
		${estabelecimento.endereco }<br> Cidade: ${estabelecimento.cidade }
		Estado: ${estabelecimento.estado } <br> Bairro:
		${estabelecimento.bairro }CEP: ${estabelecimento.cep } <br>
		Telefone: ${estabelecimento.telefone } E-mail: ${estabelecimento.email }
		<br> <br> <b>Motivo da denuncia:
			${estabelecimento.motivoDenuncia }</b><br> <br> Comentário:<br>
		${estabelecimento.comentarioDenuncia }<br> <br>

		<c:if test="${estabelecimento.status == 'denunciado' }">
			<a href="rejeitarAutenticacao?id=${estabelecimento.id }"><button
					class="btn btn-primary">REJEITAR DENUNCIA</button></a>
			<a href="deletarAutenticacao?id=${estabelecimento.id }"><button
					class="btn btn-danger">DELETAR ESTABELECIMENTO</button></a>
		</c:if>
		<c:if test="${estabelecimento.status == 'inativo' }">
			<div style="color: #d9534f">
				<b>Estabelecimento inativo.</b>
			</div>
		</c:if>
		<c:if test="${estabelecimento.status == 'publico' }">
			<div style="color: #337ab7">
				<b>Estabelecimento voltou a ser publico para todos os usuários.</b>
			</div>
		</c:if>
	</div>
</div>
<c:import url="../padrao/rodape.jsp" />