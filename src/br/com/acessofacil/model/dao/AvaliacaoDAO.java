package br.com.acessofacil.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.acessofacil.model.entity.Avaliacao;

public class AvaliacaoDAO {
	
	public int cadastra(Avaliacao avaliacao) throws IOException {
		String sqlInsert = "INSERT INTO avaliacao (idQuestao, idEstabelecimento, idUsuario, avaliacao, comentario, data) VALUES(?, ?, ?, ?, ?, NOW())";
		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, avaliacao.getIdQuestao());
			stm.setInt(2, avaliacao.getIdEstabelecimento());
			stm.setInt(3, avaliacao.getIdUsuario());
			stm.setInt(4, avaliacao.getAvaliacao());
			stm.setString(5, avaliacao.getComentario());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					avaliacao.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return avaliacao.getId();
	}
	
	public ArrayList<Avaliacao> UsuarioAvaliacao(int IdUser, int idEst) {
		
		ArrayList<Avaliacao> avals = new ArrayList<>();
		
		String query = "SELECT * FROM avaliacao WHERE idUsuario=? AND idEstabelecimento=?";
		try (Connection conn = ConnectionFactory.obterConexao(); PreparedStatement stm = conn.prepareStatement(query);) {
			stm.setInt(1, IdUser);
			stm.setInt(2, idEst);
			try(ResultSet rs = stm.executeQuery();) {				
				if(rs.next()) {
					Avaliacao aval = new Avaliacao();
					
					aval.setId(rs.getInt("id"));
					aval.setIdQuestao(rs.getInt("idQuestao"));
					aval.setIdEstabelecimento(rs.getInt("idEstabelecimento"));
					aval.setIdUsuario(rs.getInt("idUsuario"));
					aval.setAvaliacao(rs.getInt("avaliacao"));
					aval.setComentario(rs.getString("comentario"));
					aval.setData(rs.getString("data"));
					avals.add(aval);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return avals;
	}

}
