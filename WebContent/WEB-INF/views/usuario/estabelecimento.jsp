<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="../padrao/header.jsp" />

<div class="container estabelecimentoShow">
	<c:if test="${not empty estabelecimento}">
				
		<div class="row">
			<div class="col-md-12">
				<h2>${ estabelecimento.nomeFantasia } <br> <small>${ estabelecimento.endereco }, ${ estabelecimento.bairro }, ${ estabelecimento.cidade } - ${ estabelecimento.estado } </small></h2>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-6 img">
				<img src="image/estabelecimentos/6/est.png">
			</div>
			<div class="col-md-6">
				<div class="avaliacao">
					<span class="grin">
						<span class="icon-grin"></span>
						<spam class="mInfo">Satisfação</spam><br> Aceitável
					</span>					
				</div>
				<div class="title">Acesso para Cadeirantes</div>
				<div class="stars">
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
				</div>
				
				<div class="title">Sinalização de pisos para deficientes visuais</div>
				<div class="stars">
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
				</div>
				
				<div class="title">Sanitários para cadeirantes</div>
				<div class="stars">
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
				</div>
				
				<div class="title">Instruções para deficientes visuais em braile</div>
				<div class="stars">
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star"></span>
				</div>
			</div>		
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="boxComentarios">
					<h3>Comentários</h3>
					
					<div class="singleComents">					
						<div class="userAvaliacao">
						<span class="glyphicon glyphicon-user"></span><b>Eduardo</b>
						</div>
						<div class="stars">
							<span class="glyphicon glyphicon-star"></span><span
								class="glyphicon glyphicon-star"></span><span
								class="glyphicon glyphicon-star"></span><span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star"></span>
						</div>
						<div class="textAvaliacao">O lugar é muito acessível, diferente dessa merda de site que não funciona! #EduSincero</div>					
					</div>
					
					<div class="singleComents">					
						<div class="userAvaliacao">
						<span class="glyphicon glyphicon-user"></span><b>Eduardo</b>
						</div>
						<div class="stars">
							<span class="glyphicon glyphicon-star"></span><span
								class="glyphicon glyphicon-star"></span><span
								class="glyphicon glyphicon-star"></span><span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star"></span>
						</div>
						<div class="textAvaliacao">O lugar é muito acessível, diferente dessa merda de site que não funciona! #EduSincero</div>					
					</div>
					
					
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<div class="boxAvaliacao">
	     			<c:if test="${usuarioLogado != null }">
						<h1>Faça sua avaliação</h1>
						<form action="cadastraAvaliacao" method="post" onsubmit="return validaForm($(this))">
							<c:forEach var="item" items="${listaQuestoes}">
								<div class="form-group">
									
									${item.questao }
									
									<label><span>${item.id}.</span> ${item.questao}</label>
									<div data-idGroup="${item.id}" class="stars group_star">
										<span data-star=1 class="glyphicon glyphicon-star"></span>
										<span data-star=2 class="glyphicon glyphicon-star"></span>
										<span data-star=3 class="glyphicon glyphicon-star"></span>
										<span data-star=4 class="glyphicon glyphicon-star"></span>
										<span data-star=5 class="glyphicon glyphicon-star"></span>
									</div>
									<input type="hidden" value="${item.id}" name="idQuestao[]">
									<input type="hidden" value="" id="avaliacao_${item.id}" name="avaliacao_${item.id}">
								</div>
							</c:forEach>
													
							<div class="form-group">
								<label>Comentário</label>
								<textarea class="form-control" rows=10 name="comentario"></textarea>
							</div>	
							
							<div class="form-group">
								<input type="hidden" name="idEstabelecimento" value="${idEstabelecimento}">
								<input type="hidden" name="idUsuario" value="${sessionScope.id}">
								<input type="submit" value="Enviar Avaliação" class="btn btn-success">
							</div>						
						</form>
					</c:if>
					<c:if test="${usuarioLogado == null }">
						<h1>Faça Login para avaliar</h1>
				
					</c:if>
				</div>
			</div>
		</div>
		
		
	</c:if>
</div>
<c:import url="../padrao/rodape.jsp" />