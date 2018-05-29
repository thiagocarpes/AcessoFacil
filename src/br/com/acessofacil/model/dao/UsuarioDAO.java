package br.com.acessofacil.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import br.com.acessofacil.model.entity.Usuario;

@Repository
public class UsuarioDAO {
	
	@PersistenceContext
	EntityManager manager;
	
	public int inserirUsuario(Usuario usuario) throws IOException {
		manager.persist(usuario);
		return usuario.getId();
	}	
	
	public Usuario carregar(int id){
		return manager.find(Usuario.class, id);
	}
	
	public void alterar(int id, String nome, String email, String cpf, String senha) {
		String sqlUpdate = "UPDATE usuarios SET nome = '"+nome+"', email = '"+email+"', cpf = '"+cpf+"', senha = '"+senha+"' where id = "+id+"";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean login(Usuario usuario, HttpServletRequest request) {
		
		String sqlSelect = "SELECT id, usuario, nome, email, tipousuario FROM usuarios WHERE usuario = ? AND senha = ?";
		// usando o try with resources do Java 7, que fecha o que abriu 
		try (Connection conn = ConnectionFactory.obterConexao(); PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			
			stm.setString(1, usuario.getUsuario());
			stm.setString(2, usuario.getSenha());			
			
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					usuario.setId(rs.getInt("id"));
					usuario.setUsuario(rs.getString("usuario"));
					usuario.setNome(rs.getString("nome"));
					usuario.setEmail(rs.getString("email"));
					usuario.setTipoUsuario(rs.getInt("tipousuario"));					
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
}
