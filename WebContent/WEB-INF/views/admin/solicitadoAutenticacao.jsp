
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:import url="../padrao/headerAdmin.jsp" />

<div class="container">
	<div class="tReinvidicados">
		<b><span class="glyphicon glyphicon-ok"></span>SOLICITAÇÃO DE
			AUTENTICAÇÃO DE ESTABELECIMENTO</b><br>
	</div>

	<div class="row">
		<div class="col-md-6 img">
			<b>Nome: ${estabelecimento.nomeFantasia }</b><br> Endereço:
			${estabelecimento.endereco }<br> Cidade:
			${estabelecimento.cidade } Estado: ${estabelecimento.estado } <br>
			Bairro: ${estabelecimento.bairro }CEP: ${estabelecimento.cep } <br>
			Telefone: ${estabelecimento.telefone } - E-mail:
			${estabelecimento.email } <br> <br>

			<c:if test="${estabelecimento.status == 'reivindicado' }">
				<a href="autenticarEstabelecimento?id=${estabelecimento.id }"><button
						class="btn btn-primary">AUTENTICAR</button></a>
				<a href="rejeitarAutenticacao?id=${estabelecimento.id }"><button
						class="btn btn-danger">INATIVAR</button></a>
			</c:if>
			<c:if test="${estabelecimento.status == 'publico' }">
				<div style="color: #d9534f">
					<b>O estabelecimento voltou a ser público e não autenticado.</b>
				</div>
			</c:if>
			<c:if test="${estabelecimento.status == 'autenticado' }">
				<div style="color: #337ab7">
					<b>Estabelecimento autenticado.</b>
				</div>
			</c:if>
		</div>
		<div class="col-md-6 img">
			<div class="autenticaImg">
				<img
					src="image/estabelecimentos/${estabelecimento.imagem}">
			</div>
		</div>
	</div>
</div>

<c:import url="../padrao/rodape.jsp" />