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
<link href="css/estilo.css" rel="stylesheet">

</head>
<body>

	
		<div style="margin-top: 10px; margin-bottom: -10px;" class="container">
			<a href="index"> <img src="image/logo.png"></a>
		</div>
		<div class="container-fluid grey_bar">
			<div class="container">
				<ul class="nav navbar-nav">
					<c:if test="${tipousuario == 1}">
						<li class="active"><a href="homeAdmin">PAGINA INICIAL</a></li>
						<li><a href="estabelecimentos">ESTABELECIMENTOS</a></li>
						<li><a href="comentarios">COMENTÁRIOS</a></li>
						<li><a href="logout">SAIR</a></li>
						
					</c:if>	
				</ul>
			</div>
		</div>		
	
	<div class="container">
