<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="../padrao/header.jsp" />

<div id="Listestabelecimentos" class="container busca">
	<nav class="navbar navbar-default">

		<form id="formSearchCategorie" action="index" method="post" class="form-inline">

			<div class="form-group col-sm-2">
				<select style="width: 135px;"					
					class="form-control" name="id">
					<option value="0">Categorias</option>
					<c:forEach var="categorias" items="${categorias}">
						<option <c:if test="${idCat==categorias.id}">SELECTED</c:if> value="${categorias.id}">${categorias.nome}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group col-sm-6 ">
				<select style="width: 199px;" class="form-control" name="type">
					<option value="">Filtrar Por</option>
					<option <c:if test="${type=='M'}">SELECTED</c:if> value="M">Melhores Avaliados</option>
					<option <c:if test="${type=='P'}">SELECTED</c:if> value="P">Piores Avaliados</option>
				</select>
				<input style="position: relative;top:7px;left: 12px;" onclick="fm('#formSearchCategorie'); return false;" type="submit" class="btn btn-primary" value="Filtrar">
			</div>
			<div class="form-group col-sm-4">
				<div class="total">${total} Estabelecimento(s) Encontrado(s)</div>
			</div>

		</form>
	</nav>
</div>
<div class="rank">
	<c:if test="${ScrollTo}">
		<script>
			$(document).ready(function() {
				$('html, body').animate({
					scrollTop : ($("#Listestabelecimentos").offset().top - 60)
				}, 0);
			});
		</script>
	</c:if>
	<div class="ListEstabelecimentos container">
		<c:if test="${total > 0 && ((CatName != '' && CatName != null) || (type != '' && type != null))}">
			<h2>
				Exibindo: 
				<c:if test="${pesquisar != ''}"> <b>${pesquisar}</b></c:if>
				<c:if test="${type == 'M'}"> <b>Melhores Avaliados</b></c:if>
				<c:if test="${type == 'P'}"> <b>Piores Avaliados</b></c:if>
				<c:if test="${CatName != '' && type != ''}"> em </c:if>
				<c:if test="${CatName != ''}"><b>${CatName}</b></c:if>
			</h2>
		</c:if>
		<c:if test="${CatName == '' && total > 0}">
			<h2>
				<b>Estabelecimentos</b>
			</h2>
		</c:if>
		<div class="row">
			<c:if test="${total > 0 }">
				<c:forEach var="estabelecimento" items="${estabelecimentos}">
	
					<div class="col-sm-3 boxList">
	
						
							<c:if test="${estabelecimento.status != 'autenticado' }">
								<a href="estabelecimento?id=${estabelecimento.id}">
									<div class="empresaImg">
										<b>FOTO</b>
									</div>
								</a>
							</c:if>
							<c:if test="${estabelecimento.status == 'autenticado' }">
								<div class="empresaImgFoto">
									<a href="estabelecimento?id=${estabelecimento.id}">
										<img src="image/estabelecimentos/${estabelecimento.imagem}">
									</a>
								</div>
							</c:if>
	
	
						<div class="empresaNome">
							<b><a href="estabelecimento?id=${estabelecimento.id}">${estabelecimento.nomeFantasia}</a><br></b>
							<small>
								${estabelecimento.bairro}, ${estabelecimento.cidade} - ${estabelecimento.estado}
							</small>
						</div>
						<div class="avaliacao">
							<c:if test="${estabelecimento.statusGeral == 0}"><span class="grin" style="color:#999"> Não Avaliado </span></c:if>
							<c:if test="${estabelecimento.statusGeral > 0 && estabelecimento.statusGeral <= 2}"><span class="grin" style="color:#ff0000"> <span class="icon-frustrated"></span> <spam class="mInfo">Satisfação</spam><br> Ruim </span></c:if>
							<c:if test="${estabelecimento.statusGeral == 3}"><span class="grin" style="color:#ff8000"> <span class="icon-wondering"></span> <spam class="mInfo">Satisfação</spam><br> Aceitável</span></c:if>
							<c:if test="${estabelecimento.statusGeral > 3}"><span class="grin">  <span class="icon-grin"></span> <spam class="mInfo">Satisfação</spam><br> Ótimo</span></c:if>
						</div>
						<div class="uAvalicao">
							<a class="btn btn-primary" href="estabelecimento?id=${estabelecimento.id}">Mais Informações</a>
						</div>
					</div>
	
				</c:forEach>
			</c:if>
			<c:if test="${total == 0 }">
				<blockquote style="margin:120px 0px;">
					<h1>Ops, desculpe!</h1>
					<small>Nenhum item para sua pesquisa.</small>
				</blockquote>
			</c:if>
		</div>

	</div>
</div>

<c:import url="../padrao/rodape.jsp" />

