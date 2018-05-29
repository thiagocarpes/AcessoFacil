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

import org.springframework.stereotype.Repository;

import br.com.acessofacil.model.entity.Comentario;
import br.com.acessofacil.model.entity.Estabelecimento;

@Repository
public class ComentarioDAO {
	@PersistenceContext
	EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<Comentario> listarReportados() {
		return manager.createQuery("select c from Comentario c").setMaxResults(5).getResultList();
	}	
	
	public ArrayList<Comentario> listarComentariosReportados() throws IOException {
		String query = "SELECT comentarios.id, estabelecimento.nomeFantasia, estabelecimento.cidade, estabelecimento.estado, comentarios.comentario, comentarios.dtReportado FROM comentarios JOIN estabelecimento ON comentarios.idEstabelecimento = estabelecimento.id where comentarios.status = 'reportado' order by comentarios.dtReportado desc limit 5";
		ArrayList<Comentario> comentarios = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.obterConexao();
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();){
			
			while(rs.next()){
				Comentario comentario = new Comentario();				
				Estabelecimento estabelecimento = new Estabelecimento();
				
				estabelecimento.setNomeFantasia("nomeFantasia");
				
				comentario.setId(rs.getInt("id"));
				comentario.setNomeFantasia(rs.getString("nomeFantasia"));
				comentario.setCidade(rs.getString("cidade"));
				comentario.setEstado(rs.getString("estado"));
				comentario.setEmail(rs.getString("email"));
				comentario.setTelefone(rs.getString("telefone"));				
				
				if(rs.getString("comentario").length() < 50){
					comentario.setComentario(rs.getString("comentario"));
				}else{
					comentario.setComentario(rs.getString("comentario").substring(0, 50)+"...");
				}
				comentario.setDtReportado(rs.getDate("dtReportado"));
				comentarios.add(comentario);
		
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return comentarios;
	}
	public ArrayList<Comentario> listarTodosComentariosReportados() throws IOException {
		String query = "SELECT comentarios.id, estabelecimento.nomeFantasia, estabelecimento.email, estabelecimento.telefone, estabelecimento.cidade, estabelecimento.estado, comentarios.comentario, comentarios.dtReportado FROM comentarios JOIN estabelecimento ON comentarios.idEstabelecimento = estabelecimento.id where comentarios.status = 'reportado' order by comentarios.dtReportado desc";
		ArrayList<Comentario> comentarios = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.obterConexao();
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();){
			
			while(rs.next()){
				Comentario comentario = new Comentario();
				comentario.setId(rs.getInt("id"));
				comentario.setNomeFantasia(rs.getString("nomeFantasia"));
				comentario.setCidade(rs.getString("cidade"));
				comentario.setEstado(rs.getString("estado"));
				comentario.setEmail(rs.getString("email"));
				comentario.setTelefone(rs.getString("telefone"));
				if(rs.getString("comentario").length() < 50){
					comentario.setComentario(rs.getString("comentario"));
				}else{
					comentario.setComentario(rs.getString("comentario").substring(0, 50)+"...");
				}
				comentario.setDtReportado(rs.getDate("dtReportado"));
				comentarios.add(comentario);
		
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return comentarios;
	}
	
	
	public ArrayList<Comentario> listarBuscaComentario(String tipo, String texto) throws IOException {
		String query = "SELECT comentarios.id as id, comentarios.comentario as comentario, comentarios.status as status, date_format(comentarios.dtReportado, '%d/%m/%y') as data, usuarios.nome as nome, estabelecimento.nomeFantasia as nomeFantasia from comentarios JOIN estabelecimento ON comentarios.idEstabelecimento = estabelecimento.id JOIN usuarios ON comentarios.idUsuario = usuarios.id WHERE "+tipo+" like '%"+texto+"%'";

		System.out.println(query);
		
		ArrayList<Comentario> comentarios = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.obterConexao();
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();){
			
			while(rs.next()){
				Comentario comentario = new Comentario();
				comentario.setId(rs.getInt("id"));
				comentario.setComentario(rs.getString("comentario"));
				comentario.setStatus(rs.getString("status"));
				comentario.setDtReportado(rs.getDate("data"));
				comentario.setUsuario(rs.getString("nome"));
				comentario.setNomeFantasia(rs.getString("nomeFantasia"));
				comentarios.add(comentario);
		
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return comentarios;
	}
	
	
	
	
	public Comentario carregar(int id) throws IOException {
		Comentario comentario = new Comentario();
		comentario.setId(id);
		String query = "SELECT comentarios.id, comentarios.status, comentarios.comentario, estabelecimento.nomeFantasia, estabelecimento.email, estabelecimento.telefone FROM comentarios join estabelecimento on comentarios.idEstabelecimento = estabelecimento.id WHERE comentarios.id = ?";

		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement pst = conn.prepareStatement(query);) {
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {

				if (rs.next()) {
					comentario.setId(rs.getInt("id"));
					comentario.setStatus(rs.getString("status"));
					comentario.setComentario(rs.getString("comentario"));
					comentario.setNomeFantasia(rs.getString("nomeFantasia"));
					comentario.setEmail(rs.getString("email"));
					comentario.setTelefone(rs.getString("telefone"));
				} else {
					comentario.setId(-1);
					comentario.setComentario(null);
					comentario.setNomeFantasia(null);
					comentario.setEmail(null);
					comentario.setTelefone(null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return comentario;
	}
	public void inativar(int id) {
		String sqlUpdate = "UPDATE comentarios SET status = 'inativo' WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void ativar(int id) {
		String sqlUpdate = "UPDATE comentarios SET status = 'publico' WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
