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


import br.com.acessofacil.model.entity.Questoes;

@Repository
public class QuestoesDAO {
	@PersistenceContext
	EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<Questoes> listarTodasQuestoes() {
		return manager.createQuery("select q from Questoes q").getResultList();
	}
		
	public ArrayList<Questoes> listarQuestoes() throws IOException {
		String query = "SELECT id, questao, status FROM questoes";
		ArrayList<Questoes> qst = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.obterConexao();
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();){
			
			while(rs.next()){
				Questoes questoes = new Questoes();
				questoes.setId(rs.getInt("id"));
				questoes.setQuestao(rs.getString("questao"));
				questoes.setStatus(rs.getString("status"));
				qst.add(questoes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return qst;
	}
}
