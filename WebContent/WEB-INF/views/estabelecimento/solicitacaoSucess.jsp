<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="../padrao/header.jsp" />

	<div class="row">
		<div class="col-md-12">
			<p>
				<h2>Obrigado por solicitar a validação do seu estabelecimento</h2>
				<blockquote>				
					Nossa equipe entrará em contao em breve para uma ultima verificação.<br>
					Após a validação a imagem fornecida aparecerá na pagina do seu estabelecimeno e você terá a 
					possibilidade de responder a qualquer comentário da pagina.
				</blockquote>
				
				
				<a class="btn btn-primary" href="estabelecimento?id=${idEstabelecimento}">Voltar para o Estabelecimento</a>
				 
			</p>
		</div>
	</div>

<c:import url="../padrao/rodape.jsp" />