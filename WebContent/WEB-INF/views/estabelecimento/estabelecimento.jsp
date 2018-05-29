<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:import url="../padrao/header.jsp" />

<div class="container estabelecimentoShow">
	<c:if test="${not empty estabelecimento}">

		<div class="row">
			<div class="col-md-12">
				<h2>${ estabelecimento.nomeFantasia }<br> <small>${ estabelecimento.endereco },
						${ estabelecimento.bairro }, ${ estabelecimento.cidade } - ${ estabelecimento.estado }
					</small>
				</h2>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6 img">

				<c:if test="${estabelecimento.status == 'publico' || estabelecimento.status == 'denunciado'}">
					<div class="empresaImgGrande">
						<b>O estabelecimento ainda não foi validado no sistema. <br>
							Se você é o representante deste estabelecimento e deseja
							monitorar suas avaliações , por favor clique no botão a baixo
						</b><br> <br>
						<!-- MODAL PARA VALIDAR ESTABELECIMENTO -->
						<!-- BOTÃO QUE FAZ COM O QUE O MODAL SUBA NA TELA -->
						<button class="btn btn-success " data-toggle="modal"
							data-target="#myModal2">SOLICITAR VALIDAÇÃO</button>

						<!-- CONTEUDO DO MODAL -->
						<div style="text-align:left"  class="modal fade" id="myModal2" role="dialog">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">
											<spam class="glyphicon glyphicon-ok"></spam>
											SOLICITAR VALIDAÇÃO
										</h4>
									</div>
									<div class="modal-body">

										<form action="solicitaValidacao" id="solitaacesso" method="post" class="formularioCadastro" enctype="multipart/form-data">
											<input type="hidden" name="id" value="${estabelecimento.id }">
											<div class="row">
												<div class="col-md-12">
													<div class="form-group">
														<label for="nomeFantasia">Nome Fantasia</label> <input validate=1
															type="text" class="form-control" id="nomeFantasia"
															name="nomeFantasia"
															value="${estabelecimento.nomeFantasia }">
													</div>
												</div>
												<div class="col-md-12">
													<div class="form-group">
														<label for="categoria">Categoria:</label>
														<select validate=1	class="form-control" name="idCategoria">
															<option value="">Selecione a Categoria</option>
															<c:forEach var="categoria" items="${lista}">
																<option  value="${categoria.id}" > ${categoria.nome} </option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="col-md-2">
													<div class="form-group">
														<label for="endereco">Cep:</label> <input validate=1 type="text"
															class="form-control" name="cep"
															value="${estabelecimento.cep }">
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label for="endereco">Endereço:</label> <input validate=1 type="text"
															class="form-control" id="endereco" name="endereco"
															value="${estabelecimento.endereco }">
													</div>
												</div>
												<div class="col-md-2">
													<div class="form-group">
														<label for="endereco">Número:</label> <input validate=1 type="text"
															class="form-control" id="numero" name=numero
															value=${estabelecimento.numero }>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label for="endereco">Bairro:</label> <input validate=1 type="text"
															class="form-control" id="bairro" name=bairro
															value=${estabelecimento.numero }>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label for="estado">Estado:</label> <select validate=1
															class="form-control" name="estado" id="estado">
															<option value="">Selecione o Estado</option>
															<option value="AC">Acre</option>
															<option value="AL">Alagoas</option>
															<option value="AP">Amapá</option>
															<option value="AM">Amazonas</option>
															<option value="BA">Bahia</option>
															<option value="CE">Ceará</option>
															<option value="DF">Distrito Federal</option>
															<option value="ES">Espirito Santo</option>
															<option value="GO">Goiáis</option>
															<option value="MA">Maranhão</option>
															<option value="MT">Mato Grosso</option>
															<option value="MS">Mato Grosso do Sul</option>
															<option value="MG">Minas Gerais</option>
															<option value="PA">Pará</option>
															<option value="PB">Paraíba</option>
															<option value="PR">Paraná</option>
															<option value="PE">Pernambuco</option>
															<option value="PI">Piauí</option>
															<option value="RJ">Rio De Janeiro</option>
															<option value="RN">Rio Grande do Norte</option>
															<option value="RS">Rio Grande do Sul</option>
															<option value="RO">Rondônia</option>
															<option value="RR">Roraima</option>
															<option value="SC">Santa Catarina</option>
															<option value="SP">São Paulo</option>
															<option value="SE">Sergipe</option>
															<option value="TO">Tocantins</option>
														</select>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label for="cidade">Cidade:</label> <input validate=1 type="text"
															class="form-control" id="cidade" name="cidade"
															value=${estabelecimento.cidade }>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label for="telefone">Telefone:</label><input validate=1 type="text"
															class="form-control" id="telefone" name="telefone"
															value=${estabelecimento.telefone }>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label for="email">E-mail:</label> <input validate=1 type="email"
															class="form-control" id="email" name="email"
															value="${estabelecimento.email }">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label for="email">Senha:</label> <input validate=1 type="password"
															class="form-control" id="senha" name="senha"
															value="${estabelecimento.senha }">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label for="email">Confirmar Senha:</label> <input validate=1 type="password"
															class="form-control" id="cosenha" name="cosenha"
															value="${estabelecimento.senha }">
													</div>
												</div>
												<div class="col-md-12">
													<div class="form-group">
														<label for="imagem">Imagem da fachada do estabelecimento:</label>
														<input type="file" class="form-control" id="imagem" name="imagem" validate=1 >
													</div>
												</div>

												<div class="col-md-12">
													<button type="submit" class="btn btn-success" name="acao" value="Salvar" onclick="fm('#solitaacesso'); return false;">SOLICITAR</button>
												</div>
											</div>
										</form>

									</div>
								</div>
							</div>
						</div>


						<!-- FIM DO MODAL -->

					</div>
				</c:if>

				<c:if test="${estabelecimento.status == 'autenticado'}">
					<img src="image/estabelecimentos/${estabelecimento.imagem}">
				</c:if>

				<c:if test="${estabelecimento.status == 'reivindicado' }">
					<div class="empresaImgGrande">

						<br> <br> <b>Este estabelecimento já foi solicitado
							para validação e encontra-se em processo de verificação dos dados
							por parte de nossa equipe.<br>
						</b>
					</div>
				</c:if>

			</div>
			<div class="col-md-6">
				<c:if test="${StatusGeral > 0}">
					<div class="avaliacao">
						<c:if test="${StatusGeral <= 2}"><span class="grin" style="color:#ff0000"> <span class="icon-frustrated"></span> <spam class="mInfo">Satisfação</spam><br> Ruim </span></c:if>
						<c:if test="${StatusGeral == 3}"><span class="grin" style="color:#ff8000"> <span class="icon-wondering"></span> <spam class="mInfo">Satisfação</spam><br> Aceitável</span></c:if>
						<c:if test="${StatusGeral > 3}"><span class="grin"> <span class="icon-grin"></span> <spam class="mInfo">Satisfação</spam><br> Ótimo</span></c:if>
					</div>
					<c:forEach var="aval" items="${estabelecimento.avaliacoes}">
						<div class="title">${aval.questao.questao }</div>
						<div class="stars">
							<span class="glyphicon glyphicon-star" ${aval.mediaFinal >= 1 ? 'style="color: #f4d300;"' : 'style="color: rgb(210, 210, 210);"'}></span>
							<span class="glyphicon glyphicon-star" ${aval.mediaFinal >= 2 ? 'style="color: #f4d300;"' : 'style="color: rgb(210, 210, 210);"'}></span>
							<span class="glyphicon glyphicon-star" ${aval.mediaFinal >= 3 ? 'style="color: #f4d300;"' : 'style="color: rgb(210, 210, 210);"'}></span>
							<span class="glyphicon glyphicon-star" ${aval.mediaFinal >= 4 ? 'style="color: #f4d300;"' : 'style="color: rgb(210, 210, 210);"'}></span>
							<span class="glyphicon glyphicon-star" ${aval.mediaFinal >= 5 ? 'style="color: #f4d300;"' : 'style="color: rgb(210, 210, 210);"'}></span>
							
						</div>
					</c:forEach>
				</c:if>
				<c:if test="${StatusGeral == 0}"><h3>Estabelecimento não possui avaliações</h3></c:if>
			</div>
		</div>
		
		
		<c:if test="${not empty comentarios}">
			<div class="row">
				<div class="col-md-12">
					<hr>
					<div class="boxComentarios">
						<h3>Comentários</h3>
	
						<c:forEach var="com" items="${comentarios}">					
							<div class="singleComents">
								<div class="userAvaliacao">
									<span class="glyphicon glyphicon-user"></span><b>${com.usuario.nome }</b>
								</div>
								<div class="stars">
									<span class="glyphicon glyphicon-star" ${com.mediaAvaliacao >= 1 ? 'style="color: #f4d300;"' : 'style="color: rgb(210, 210, 210);"'}></span>
									<span class="glyphicon glyphicon-star" ${com.mediaAvaliacao >= 2 ? 'style="color: #f4d300;"' : 'style="color: rgb(210, 210, 210);"'}></span>
									<span class="glyphicon glyphicon-star" ${com.mediaAvaliacao >= 3 ? 'style="color: #f4d300;"' : 'style="color: rgb(210, 210, 210);"'}></span>
									<span class="glyphicon glyphicon-star" ${com.mediaAvaliacao >= 4 ? 'style="color: #f4d300;"' : 'style="color: rgb(210, 210, 210);"'}></span>
									<span class="glyphicon glyphicon-star" ${com.mediaAvaliacao >= 5 ? 'style="color: #f4d300;"' : 'style="color: rgb(210, 210, 210);"'}></span>
								</div>
								<div class="textAvaliacao">${com.comentario }</div>
								<c:if test="${com.resposta != null}">
										<br><div class="well" ><b style="color: #d9534f">Resposta do estabelecimento</b><br> ${com.resposta} </div>
								</c:if>
								<c:if test="${sessionScope.idEstabLogado == estabelecimento.id}">		
									<!-- MODAL PARA RESPONDER COMENTÁRIOS -->
									<!-- BOTÃO QUE FAZ COM O QUE O MODAL SUBA NA TELA -->
				
									<c:if test="${com.resposta == null}">
										<button class="btn btn-link" data-toggle="modal" data-target="#myModal2">Responder</button>
									</c:if>
									
									<!-- CONTEUDO DO MODAL -->
									<div style="text-align:left"  class="modal fade" id="myModal2" role="dialog">
										<div class="modal-dialog modal-lg">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">&times;</button>
													<h4 class="modal-title">
														<spam class="glyphicon glyphicon-ok"></spam>
														RESPONDER
													</h4>
												</div>
												<div class="modal-body">
			
													<form action="responderComents" method="post" class="formularioCadastro">
														<div class="row">
															<div class="col-md-12">
																<div class="form-group">
																	<textarea class="form-control" rows=10 name="resposta" placeholder="Responder..."></textarea> 
																</div>
															</div>
													
															<div class="col-md-12">
																<div class="form-group">
																	<input type="hidden" name="idEstabelecimento" value="${estabelecimento.id }">
																	<input type="hidden" name="idAval" value="${com.id }"> 
																</div>
																<button type="submit" class="btn btn-success" name="acao">RESPONDER</button>
															</div>
														</div>
													</form>
			
												</div>
											</div>
										</div>
									</div>
									<!-- FIM DO MODAL -->
								</c:if>
								
							</div>
							<hr>
							
						</c:forEach>
	
					</div>
				</div>
			</div>
		</c:if>
		
		<c:if test="${sessionScope.tipousuario != 2 && sessionScope.tipousuario != 3 && sessionScope.idEstabLogado == null}">
		<div class="row">
			<div class="col-md-12">
				<hr>
				
				<div class="boxAvaliacao">
					<c:if test="${not empty userAval}">
				    	<h4>Sua avaliação já foi registrada, obrigado por colaborar</h4>				    
				    </c:if>
					<c:if test="${usuarioLogado != null && empty userAval}">
						<h1>Faça sua avaliação</h1>
						
						<form action="cadastraAvaliacao" method="post"	onsubmit="return validaForm($(this))">
							<div style="padding-left:0px" class="col-md-6">
								<c:forEach var="item" items="${questoes}">
									<div class="form-group">
										<label><span>${item.id}.</span> ${item.questao}</label>
										<div data-idGroup="${item.id}" class="stars group_star">
											<span data-star=1 class="glyphicon glyphicon-star"></span> <span
												data-star=2 class="glyphicon glyphicon-star"></span> <span
												data-star=3 class="glyphicon glyphicon-star"></span> <span
												data-star=4 class="glyphicon glyphicon-star"></span> <span
												data-star=5 class="glyphicon glyphicon-star"></span>
										</div>
										<input type="hidden" value="${item.id}" name="idQuestao[]">
										<input type="hidden" value="" id="avaliacao_${item.id}"
											name="avaliacao_${item.id}">
									</div>
								</c:forEach>
							</div>
							<div style="padding-right:0px" class="col-md-6">
								<div class="form-group">
									<label>Comentário</label>
									<textarea class="form-control" rows=10 name="comentario"></textarea>
								</div>	
								<div class="form-group">
									<input type="hidden" name="idEstabelecimento"
										value="${idEstabelecimento}"> <input type="hidden"
										name="idUsuario" value="${sessionScope.id}"> <input
										type="submit" value="Enviar Avaliação" class="btn btn-success">
								</div>
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
		<div class="row">
			<div style="text-align:center" class="col-md-12">
				<hr>
				<c:if test="${estabelecimento.status == 'publico' }">
					<!-- MODAL PARA DENUNCIAR ESTABELECIMENTO -->
	
					<!-- BOTÃO QUE FAZ COM O QUE O MODAL SUBA NA TELA -->
					
					<b> Encontrou algo de errado? Você pode <a style="color:#d43f3a" href="javascript:$('#myModal').modal('show')">denunciar esse estabelecimento</a> </b>
					
					<!-- CONTEUDO DO MODAL -->
					<div style="text-align:left" class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">
										<spam class="glyphicon glyphicon-alert"></spam>
										DENUNCIA DE ESTABELECIMENTO
									</h4>
								</div>
								<div class="modal-body">
		
									<form action="denunciarEstabelecimento" id="Denunciar" method="post">
										<input type="hidden" name="id" value="${estabelecimento.id }">
										<p>Motivo da Denuncia:</p>
										<select validate=1 class="form-control" name="motivo" id="motivo">
											<option value="">*Selecione o motivo da denuncia*</option>
											<option value="duplicidade">Duplicidade</option>
											<option value="inexistente">Estabelecimento
										Inexistente</option>
										</select> <br>
										<p>Comentário:</p>
										<textarea validate=1 class="form-control" rows=5 name="comentario"></textarea>
										<br>
										<button type="submit" onclick="fm('#Denunciar'); return false;" class="btn btn-danger">DENUNCIAR</button>
									</form>
		
								</div>
							</div>
						</div>
					</div>
					<!-- FIM DO MODAL -->
				</c:if>
				<hr>
			</div>
		</div>
	</c:if>
</div>
<c:import url="../padrao/rodape.jsp" />