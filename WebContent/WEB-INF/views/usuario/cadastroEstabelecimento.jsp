<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:import url="../padrao/header.jsp" />
<div class="col-md-3">
	<c:import url="../padrao/menuHome.jsp" />
</div>
<div class="col-md-9 userColumLeft">
	<div class="col-md-12 userColumRight">


		<!-- EDITAR SOMENTE ESSE BLOCO -->

		<form action="newEstabelecimento" method="post"	class="formularioCadastro">
			<input type="hidden" name="status" value="publico">
			<div class="row">
				<div class="col-md-12">
					<h3>Cadastro de Estabelecimento</h3>
					<div class="form-group">
						<label for="nomeFantasia">Nome Fantasia</label> <input type="text"
							class="form-control" id="nomeFantasia" name="nomeFantasia">
					</div>
				</div>
				<div class="col-md-12">
					<div class="form-group">
						<label for="categoria">Categoria:</label> <select
							class="form-control" name="idCategoria">
							<option value="#">Selecione a Categoria</option>
							<c:forEach var="categoria" items="${lista}">
								<option value="${categoria.id}">${categoria.nome}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="col-md-12">
					<div class="form-group">
						<label for="endereco">Cep:</label> <input type="text"
							class="form-control" name="cep">
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="endereco">Endere�o:</label> <input type="text"
							class="form-control" id="endereco" name="endereco">
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<label for="endereco">N�mero:</label> <input type="text"
							class="form-control" id="numero" name=numero>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label for="endereco">Bairro:</label> <input type="text"
							class="form-control" id="bairro" name=bairro>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="estado">Estado:</label> <select class="form-control"
							name="estado" id="estado">
							<option value="#">Selecione o Estado</option>
							<option value="AC">Acre</option>
							<option value="AL">Alagoas</option>
							<option value="AP">Amap�</option>
							<option value="AM">Amazonas</option>
							<option value="BA">Bahia</option>
							<option value="CE">Cear�</option>
							<option value="DF">Distrito Federal</option>
							<option value="ES">Espirito Santo</option>
							<option value="GO">Goi�is</option>
							<option value="MA">Maranh�o</option>
							<option value="MT">Mato Grosso</option>
							<option value="MS">Mato Grosso do Sul</option>
							<option value="MG">Minas Gerais</option>
							<option value="PA">Par�</option>
							<option value="PB">Para�ba</option>
							<option value="PR">Paran�</option>
							<option value="PE">Pernambuco</option>
							<option value="PI">Piau�</option>
							<option value="RJ">Rio De Janeiro</option>
							<option value="RN">Rio Grande do Norte</option>
							<option value="RS">Rio Grande do Sul</option>
							<option value="RO">Rond�nia</option>
							<option value="RR">Roraima</option>
							<option value="SC">Santa Catarina</option>
							<option value="SP">S�o Paulo</option>
							<option value="SE">Sergipe</option>
							<option value="TO">Tocantins</option>
						</select>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="cidade">Cidade:</label> <input type="text"
							class="form-control" id="cidade" name="cidade">
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="telefone">Telefone:</label> <input type="text"
							class="form-control" id="telefone" name="telefone">
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="email">E-mail:</label> <input type="email"
							class="form-control" id="email" name="email">
					</div>
				</div>
				<div class="col-md-12">
					<button type="submit" class="btn btn-primary" name="acao"
						value="Salvar">CADASTRAR</button>
				</div>
			</div>
		</form>

		<!-- FIM DO BLOCO -->


	</div>
</div>
<c:import url="../padrao/rodape.jsp" />