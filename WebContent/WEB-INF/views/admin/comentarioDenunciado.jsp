
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:import url="../padrao/headerAdmin.jsp" />

<div class="container">
	<div class="adminConteudo">
		<div class="tReinvidicados">
			<b><span class="glyphicon glyphicon-alert"></span> COMENT�RIO
				DENUNCIADO<br> </b>
		</div>

		<b>Estabelecimento: ${comentario.nomeFantasia }<br></b> E-mail:
		${comentario.email } - Telefone: ${comentario.telefone }<br>
		<br> <b>Coment�rio Reportado:<br>
			${comentario.comentario }<br>
		<br>
		</b>

		<c:if test="${comentario.status == 'reportado' }">
			<a href="ativarComentario?id=${comentario.id }"><button
					class="btn btn-primary">ATIVAR</button></a>
			<a href="inativarComentario?id=${comentario.id }"><button
					class="btn btn-danger">INATIVAR</button></a>
		</c:if>
		<c:if test="${comentario.status == 'inativo' }">
			<div style="color: #d9534f">
				<b>Coment�rio inativo.</b>
			</div>
		</c:if>
		<c:if test="${comentario.status == 'publico' }">
			<div style="color: #337ab7">
				<b>O coment�rio voltou a ser p�blico para todos os usu�rios.</b>
			</div>
		</c:if>

	</div>
</div>

<c:import url="../padrao/rodape.jsp" />