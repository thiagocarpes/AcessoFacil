package br.com.acessofacil.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import br.com.acessofacil.model.entity.Avaliacao;
import br.com.acessofacil.model.entity.ComentariosEstabelecimentos;
import br.com.acessofacil.model.entity.Estabelecimento;
import br.com.acessofacil.model.entity.Questoes;
import br.com.acessofacil.model.entity.Usuario;

@Repository
public class EstabelecimentoDAO {
	@PersistenceContext
	EntityManager manager;

	public int inserirEstabelecimento(Estabelecimento estabelecimento) throws IOException {
		manager.persist(estabelecimento);
		return estabelecimento.getId();
	}

	public Estabelecimento carregar(int id) {
		Estabelecimento est = manager.find(Estabelecimento.class, id);
		this.MediaEstabelecimento(est, "estabelecimento");
		this.MediaEstabelecimento(est, "comentarios");
		return est;
	}

	public void ativar(Estabelecimento estabelecimento) throws IOException {
		manager.merge(estabelecimento);
	}

	@SuppressWarnings("unchecked")
	public List<Estabelecimento> listarTodosReivindicados() {
		return manager.createQuery("select e from Estabelecimento e where e.status = 'reivindicado'").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Estabelecimento> listarReivindicados() {
		return manager.createQuery("select e from Estabelecimento e where e.status = 'reivindicado'").setMaxResults(5)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Estabelecimento> listarTodosDenunciados() {
		return manager.createQuery("select e from Estabelecimento e where e.status = 'denunciado'").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Estabelecimento> listarDenunciados() {
		return manager.createQuery("select e from Estabelecimento e where e.status = 'denunciado'").setMaxResults(5)
				.getResultList();
	}

	public ArrayList<Estabelecimento> listarEstabelecimentos(String WhereQuery, boolean showAll) throws IOException {

		boolean insertList;

		String query = "SELECT u.id as idUsuario, u.nome, u.email uEmail, a.avaliacao, a.id as idAvaliacao, a.idUsuario as AidUsuario, a.idEstabelecimento as aIdEstabelecimento, e.id as idEstabelecimento, e.nomeFantasia, e.email eEmail, e.telefone, e.cidade, e.bairro, e.estado, date_format(e.dtCadastro, '%d/%m/%y') as dtCadastro, e.status, e.imagem "
				+ "FROM estabelecimento e LEFT JOIN avaliacao a ON a.idEstabelecimento=e.id LEFT JOIN usuarios u ON u.id=a.idUsuario "
				+ WhereQuery + " order by e.id desc";

		ArrayList<Estabelecimento> estabelecimentos = new ArrayList<>();
		ArrayList<Usuario> usuarios = new ArrayList<>();
		ArrayList<Avaliacao> avaliacoes = new ArrayList<>();

		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement pst = conn.prepareStatement(query);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {

				insertList = true;

				Estabelecimento estabelecimento = new Estabelecimento();
				Usuario usuario = new Usuario();
				Avaliacao avaliacao = new Avaliacao();

				estabelecimento.setId(rs.getInt("idEstabelecimento"));
				estabelecimento.setNomeFantasia(rs.getString("nomeFantasia"));
				if (rs.getString("eEmail").length() < 30) {
					estabelecimento.setEmail(rs.getString("eEmail"));
				} else {
					estabelecimento.setEmail(rs.getString("eEmail").substring(0, 30) + "...");
				}
				estabelecimento.setCidade(rs.getString("cidade"));
				estabelecimento.setTelefone(rs.getString("telefone"));
				estabelecimento.setBairro(rs.getString("bairro"));
				estabelecimento.setEstado(rs.getString("estado"));
				estabelecimento.setDtCadastro(rs.getString("dtCadastro"));
				estabelecimento.setStatus(rs.getString("status"));
				estabelecimento.setImagem(rs.getString("imagem"));

				for (Estabelecimento check : estabelecimentos) {
					if (check.getId() == rs.getInt("idEstabelecimento")) {
						insertList = false;
					}
				}

				if (insertList) {
					this.MediaEstabelecimento(estabelecimento, "estabelecimento");
					estabelecimentos.add(estabelecimento);
				}

				if (insertList || showAll) {
					usuario.setId(rs.getInt("idUsuario"));
					usuario.setNome(rs.getString("nome"));
					usuario.setEmail(rs.getString("uEmail"));
					usuarios.add(usuario);

					avaliacao.setId(rs.getInt("idAvaliacao"));
					avaliacao.setIdEstabelecimento(rs.getInt("aIdEstabelecimento"));
					avaliacao.setIdUsuario(rs.getInt("AIdUsuario"));
					avaliacoes.add(avaliacao);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		
		return estabelecimentos;
	}

	public ArrayList<Estabelecimento> listarBuscaSimples(String texto, int page) throws IOException {
		int limit = 5;
		int offset = (limit * page) - limit;

		String query = "SELECT id, nomeFantasia, email, telefone, cidade, estado, date_format(dtCadastro, '%d/%m/%y') as dtCadastro, status "
				+ "FROM estabelecimento " + "WHERE nomeFantasia like '%" + texto + "%' order by id desc limit " + limit
				+ " offset " + offset + " ";

		ArrayList<Estabelecimento> estabelecimentos = new ArrayList<>();

		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement pst = conn.prepareStatement(query);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				Estabelecimento estabelecimento = new Estabelecimento();
				estabelecimento.setId(rs.getInt("id"));
				estabelecimento.setNomeFantasia(rs.getString("nomeFantasia"));
				if (rs.getString("email").length() < 30) {
					estabelecimento.setEmail(rs.getString("email"));
				} else {
					estabelecimento.setEmail(rs.getString("email").substring(0, 30) + "...");
				}
				estabelecimento.setCidade(rs.getString("cidade"));
				estabelecimento.setTelefone(rs.getString("telefone"));
				estabelecimento.setEstado(rs.getString("estado"));
				estabelecimento.setDtCadastro(rs.getString("dtCadastro"));
				estabelecimento.setStatus(rs.getString("status"));
				estabelecimentos.add(estabelecimento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return estabelecimentos;
	}

	public ArrayList<Estabelecimento> listarBusca(String status, String tipo, String texto) throws IOException {

		String qStatus = (status != "") ? " status like '%" + status + "%' AND " : "";

		String query = "SELECT id, nomeFantasia, email, telefone, cidade, estado, date_format(dtCadastro, '%d/%m/%y') as dtCadastro, status "
				+ "FROM estabelecimento " + "WHERE " + qStatus + tipo + " like '%" + texto + "%' order by id desc";

		ArrayList<Estabelecimento> estabelecimentos = new ArrayList<>();

		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement pst = conn.prepareStatement(query);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				Estabelecimento estabelecimento = new Estabelecimento();
				estabelecimento.setId(rs.getInt("id"));
				estabelecimento.setNomeFantasia(rs.getString("nomeFantasia"));
				if (rs.getString("email").length() < 30) {
					estabelecimento.setEmail(rs.getString("email"));
				} else {
					estabelecimento.setEmail(rs.getString("email").substring(0, 30) + "...");
				}
				estabelecimento.setCidade(rs.getString("cidade"));
				estabelecimento.setTelefone(rs.getString("telefone"));
				estabelecimento.setEstado(rs.getString("estado"));
				estabelecimento.setDtCadastro(rs.getString("dtCadastro"));
				estabelecimento.setStatus(rs.getString("status"));
				estabelecimentos.add(estabelecimento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return estabelecimentos;
	}

	public int totalEstab(String texto) {
		int total = 0;
		String sqlSelect = "SELECT count(id) as qtd FROM estabelecimento WHERE nomeFantasia like '%" + texto + "%'";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					total = (rs.getInt("qtd"));
				} else {
					total = -1;
				}
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
			return total;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	public boolean login(Estabelecimento estabelecimento, HttpServletRequest request) {

		String sqlSelect = "SELECT * FROM estabelecimento WHERE email = ? AND senha = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {

			stm.setString(1, estabelecimento.getEmail());
			stm.setString(2, estabelecimento.getSenha());

			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					estabelecimento.setId(rs.getInt("id"));

					HttpSession sessao = request.getSession();
					sessao.setAttribute("id", rs.getInt("id"));
					sessao.setAttribute("ObjectUsuario", estabelecimento);

					return true;

				} else {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
			return false;
		}

	}

	public void autenticar(int id) {
		String sqlUpdate = "UPDATE estabelecimento SET status = 'autenticado' WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void rejeitar(int id) {
		String sqlUpdate = "UPDATE estabelecimento SET status = 'publico' WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void denunciar(int id, String comentario, String motivo) {
		String sqlUpdate = "UPDATE estabelecimento SET status = 'denunciado', comentarioDenuncia = '" + comentario
				+ "', dtDenuncia = NOW(), motivoDenuncia = '" + motivo + "' WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleta(int id) {
		String sqlDelete = "DELETE FROM estabelecimento WHERE id = " + id;
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void solicitaValidacao(Estabelecimento est) {
		String sqlUpdate = "UPDATE estabelecimento SET nomeFantasia = '" + est.getNomeFantasia() + "', idCategoria = '"
				+ est.getIdCategoria() + "', cep = '" + est.getCep() + "', endereco = '" + est.getEndereco() + "', numero = '" + est.getNumero()
				+ "', bairro = '" + est.getBairro() + "', estado = '" + est.getEstado() + "' , cidade = '" + est.getCidade() + "', telefone = '"
				+ est.getTelefone() + "', email = '" + est.getEmail() + "', senha = '" + est.getSenha() + "', tipoUsuario = '" + est.getTipoUsuario() + "', imagem = '" + est.getImagem()
				+ "', status = 'reivindicado' where id = " + est.getId() + "";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obterConexao(); PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void MediaEstabelecimento(Estabelecimento estabelecimento, String retorno) {
		
		ArrayList<Avaliacao> avaliacoes = new ArrayList<>();
		ArrayList<ComentariosEstabelecimentos> comentarios = new ArrayList<>();
		
		double somaGeral = 0.0;
		int perguntas = 0;
		int TotalGeralAvaliacoes = 1;
		
		
		if(retorno.equals("estabelecimento")) {
			
			String query = "SELECT a.id, a.idQuestao, a.idEstabelecimento, a.idUsuario, a.avaliacao, a.comentario, a.data, q.questao FROM avaliacao a INNER JOIN estabelecimento e ON a.idEstabelecimento=e.id INNER JOIN questoes q ON q.id=a.idQuestao WHERE e.id=? ORDER BY q.questao, a.idQuestao;";
			try (Connection conn = ConnectionFactory.obterConexao(); PreparedStatement stm = conn.prepareStatement(query);) {
				stm.setInt(1, estabelecimento.getId());
				
				try (ResultSet rs = stm.executeQuery();) {
					if(rs.next()) {
						rs.first();					
						int lastId=rs.getInt("id");
						while (rs.next()) lastId=rs.getInt("id");
							
						rs.first();
						int idOld = rs.getInt("idQuestao");
						String questaoOld = rs.getString("questao");
						int idQuestaoOld = rs.getInt("idQuestao");
						
						int SomaDeAvaliacao = rs.getInt("avaliacao");
						int TotalAvaliacoes = 1;
						
						while (rs.next()) {
							
							if(rs.getInt("idQuestao") != idOld || lastId==rs.getInt("id")) {
								
								//se for o �ltimo registro faz a adi��o final
								if(lastId==rs.getInt("id")){
									TotalAvaliacoes++;
									SomaDeAvaliacao += rs.getInt("avaliacao");
								}
								
								//inserindo no array de avaliacoes a quest�o do loop anterior com as avalia��es j� somadas
								Avaliacao aval = new Avaliacao();								
								Questoes q = new Questoes();
								q.setQuestao(questaoOld);
								q.setId(idQuestaoOld);
								aval.setQuestao(q);
								double calculo = Math.ceil((SomaDeAvaliacao*5)/(TotalAvaliacoes*5));
								somaGeral += calculo;
								perguntas++;
								aval.setMediaFinal(calculo);
								avaliacoes.add(aval);
								
								TotalAvaliacoes = 0;
								SomaDeAvaliacao = 0;
								TotalAvaliacoes++;
								SomaDeAvaliacao += rs.getInt("avaliacao");
								
							} else {
								TotalAvaliacoes++;
								SomaDeAvaliacao += rs.getInt("avaliacao");
							}
							
							idOld = rs.getInt("idQuestao");
							questaoOld = rs.getString("questao");
							idQuestaoOld = rs.getInt("idQuestao");
							TotalGeralAvaliacoes++;
							
						}
					}

					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			double calc = Math.ceil((somaGeral*5)/(perguntas*5));
			estabelecimento.setStatusGeral((int)calc);
			estabelecimento.setAvaliacoes(avaliacoes);
		
		} else if(retorno.equals("comentarios")) {
		
			String query = "SELECT a.id, a.comentario, a.idUsuario, a.avaliacao, u.nome, r.resposta FROM avaliacao a INNER JOIN usuarios u ON a.idUsuario=u.id INNER JOIN estabelecimento e ON a.idEstabelecimento=e.id LEFT JOIN resposta r ON r.idAvaliacao=a.id WHERE e.id=? ORDER BY a.idUsuario, r.resposta;";
			try (Connection conn = ConnectionFactory.obterConexao(); PreparedStatement stm = conn.prepareStatement(query);) {
				stm.setInt(1, estabelecimento.getId());
				
				try (ResultSet rs = stm.executeQuery();) {
					
						rs.first();					
						int lastId=rs.getInt("id");
						while (rs.next()) lastId=rs.getInt("id");
							
						rs.first();
						int idOld = rs.getInt("idUsuario");
						int idAvaliacao = rs.getInt("id");
						String nomeOld = rs.getString("nome");
						String comentarioOld = rs.getString("comentario");
						String respostaOld = rs.getString("resposta");
						
						
						int SomaDeAvaliacao = rs.getInt("avaliacao");
						int TotalAvaliacoes = 1;
						
						while (rs.next()) {
							
							if(rs.getInt("idUsuario") != idOld || lastId==rs.getInt("id")) {
								
								//se for o �ltimo registro faz a adi��o final
								if(lastId==rs.getInt("id")){
									TotalAvaliacoes++;
									SomaDeAvaliacao += rs.getInt("avaliacao");
									respostaOld = rs.getString("resposta");
								}
								
								Usuario user = new Usuario();
								user.setId(idOld);
								user.setNome(nomeOld);
								double calculo = Math.ceil((SomaDeAvaliacao*5)/(TotalAvaliacoes*5));
								ComentariosEstabelecimentos com = new ComentariosEstabelecimentos();
								com.setUsuario(user);
								com.setComentario(comentarioOld);
								if(respostaOld != null) { com.setResposta(respostaOld); }
								com.setMediaAvaliacao(calculo);
								com.setId(idAvaliacao);
								comentarios.add(com);
								
								TotalAvaliacoes = 0;
								SomaDeAvaliacao = 0;
								TotalAvaliacoes++;
								SomaDeAvaliacao += rs.getInt("avaliacao");
								
							} else {
								TotalAvaliacoes++;
								SomaDeAvaliacao += rs.getInt("avaliacao");
							}
							
							idOld = rs.getInt("idUsuario");
							idAvaliacao = rs.getInt("id"); 
							nomeOld = rs.getString("nome");
							comentarioOld = rs.getString("comentario");
							respostaOld = rs.getString("resposta");
							TotalGeralAvaliacoes++;
							
						}

					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			estabelecimento.setComentariosEstabelecimentos(comentarios);
		
			
		}

	}
	public boolean validarEstabelecimento(Estabelecimento estabelecimento, HttpServletRequest request) throws IOException{
		String sqlSelect = "SELECT e.id, e.tipousuario, e.nomeFantasia, e.email FROM estabelecimento e WHERE e.email = ? AND e.senha = ?";
		// usando o try with resources do Java 7, que fecha o que abriu 
		try (Connection conn = ConnectionFactory.obterConexao(); PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			
			stm.setString(1, estabelecimento.getEmail());
			stm.setString(2, estabelecimento.getSenha());	
			
			
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					estabelecimento.setId(rs.getInt("id"));
					estabelecimento.setEmail(rs.getString("email"));
					estabelecimento.setNomeFantasia(rs.getString("nomeFantasia"));
					estabelecimento.setTipoUsuario(rs.getInt("tipousuario"));
					
					
					HttpSession sessao = request.getSession();
					sessao.setAttribute("idEstabLogado", rs.getInt("id"));
					sessao.setAttribute("id", rs.getInt("id"));
					sessao.setAttribute("nomeFantasia", rs.getString("nomeFantasia"));
					sessao.setAttribute("email", rs.getString("email"));
					sessao.setAttribute("tipousuario", rs.getInt("tipousuario"));
					sessao.setAttribute("ObjectUsuario", estabelecimento);		
					
					
					return true;
					
				}else{
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
			return false;
		}

	}
	
	public void alterar(Estabelecimento estabelecimento) {
		String sqlUpdate = "UPDATE estabelecimento SET nomeFantasia='"+estabelecimento.getNomeFantasia()+"'"
				+ ", email='"+estabelecimento.getEmail()+"', senha='"+estabelecimento.getSenha()+"', telefone='"+estabelecimento.getTelefone()+"'"
						+ ", cidade='"+estabelecimento.getCidade()+"', estado='"+estabelecimento.getEstado()+"' where id = '"+estabelecimento.getId()+"'";
		// usando o try with resources do Java 7, que fecha o que abriu
		
		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
						
			stm.execute();
								
			} 
		 	catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}			
	}
	
}
