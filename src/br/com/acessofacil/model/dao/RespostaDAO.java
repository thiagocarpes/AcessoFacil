package br.com.acessofacil.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import br.com.acessofacil.model.entity.ComentariosEstabelecimentos;
import br.com.acessofacil.model.entity.Resposta;

@Repository
public class RespostaDAO {

	
	public Resposta carregar(int id) throws IOException {
		Resposta resposta = new Resposta();
		resposta.setId(id);
		String query = "SELECT * FROM resposta WHERE resposta.id = ?";

		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement pst = conn.prepareStatement(query);) {
			
			pst.setInt(1, id);
			
			try (ResultSet rs = pst.executeQuery();) {

				if (rs.next()) {
					resposta.setId(rs.getInt("id"));
					
					resposta.setResposta(rs.getString("resposta"));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return resposta;
	}
	
	public int cadastraResposta(int id, Resposta resposta) throws IOException{
		String sqlInsert = "INSERT INTO resposta (idAvaliacao, idEstabelecimento, resposta) VALUES(?, 0, ?)";
		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, id);
			stm.setString(2, resposta.getResposta());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					resposta.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resposta.getId();
	}
	

}
