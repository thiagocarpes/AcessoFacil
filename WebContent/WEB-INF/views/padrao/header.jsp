<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Acesso Fácil</title>

<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/customIcons.css" rel="stylesheet">
<link href="css/estilo.css?1" rel="stylesheet">
<script src="js/jquery-2.2.3.min.js"></script>

</head>
<body>

	<c:if test="${banner == true }">
		<div style="padding:0" class="container-fluid">
			<div class="banner">

				<div class="c"></div>

				<div style="position: relative;height: 100%;" class="container">
					<a class="logo" href="index"> <img src="image/logo.png"></a>
					<div class="ct">
						Acessibilidade como deve ser,<br> fácil e bem feita
					</div>
				</div>
			</div>
		</div>
		<div class="container-fluid blueBar">
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
						<div class="t">
							ACESSIBILIDADE É
							<spam class="dt">SEU</spam>
							DIREITO<BR>
						</div>
						<div class="st">Encontre as empresas que estão aptas para
							receber clientes com necessidades especiais.</div>
					</div>
					<form action="index" method="post">
						<div class="col-sm-6">
							<div class="formSearch">
								<div class="form-group">
									<label>Pesquisar Estabelecimento</label>
									<input class="form-control" name="pesquisar" type="text" placeholder="Digite o nome do estabelecimento ou uma região (Bairro ou Cidade)">
									<button type="submit" class="pesquisar"> <spam class="glyphicon glyphicon-search"></spam> Pesquisar	</button>
								</div>
								<div style="position:relative" class="form-group">
									<label>Avaliar um Estabelecimento</label>
									<input onblur="$('#returnSearch').fadeOut();$(this).val('')" class="form-control" name="avaliar" id="avaliarPost" type="text" placeholder="Digite o nome do estabelecimento">
									<div class="returnSearch" id="returnSearch"></div>
								</div>
							</div>
						</div>
					</form>
			</div>
			
		</div>
	</div>
	</c:if>
	<c:if test="${empty banner}">
		<div <c:if test="${sessionScope.tipousuario != 2}"> style="margin-top: 72px;" </c:if> class="container">
			<a href="index"> <img src="image/logo.png" style="margin-top: -35px; margin-bottom: -20px;"></a>
		</div>
		<div class="container-fluid <c:if test="${sessionScope.tipousuario != 2}"> grey_bar </c:if><c:if test="${sessionScope.tipousuario == 2}"> blue_bar </c:if>">
			<div class="container">
				<ul class="nav navbar-nav">
					<c:if test="${sessionScope.tipousuario != 2 && sessionScope.tipousuario !=3}">
						<li class="active"><a href="index">HOME</a></li>
						<li><a href="buscaEstabelecimentos">ESTABELECIMENTOS</a></li>						
						<li ><a href="quemSomos">SOBRE NÓS</a></li>
						<li><a href="comoFunciona">COMO FUNCIONA</a></li>
						<li><a href="#">FALE CONOSCO</a></li>
					</c:if>
					<c:if test="${sessionScope.tipousuario == 2}">
						<li class="active"><a href="homeAdmin">PAGINA INICIAL</a></li>
						<li><a href="estabelecimentos">ESTABELECIMENTOS</a></li>
						<li><a href="#">COMENTÁRIOS</a></li>
						<li><a href="logout">SAIR</a></li>
						
					</c:if>
					<c:if test="${sessionScope.tipousuario == 3}">
						<li class="active"><a href="estabelecimento?id=${id}">PAGINA INICIAL</a></li>
						<li><a href="quemSomos">SOBRE NÓS</a></li>
						<li><a href="comoFunciona">COMO FUNCIONA</a></li>
						<li><a href="#">FALE CONOSCO</a></li>
					</c:if>
						
				</ul>
			</div>
		</div>		
	</c:if>
	<div class="homeAdminConteudo">
	<div class="container">
		<c:import url="../padrao/menu.jsp" />