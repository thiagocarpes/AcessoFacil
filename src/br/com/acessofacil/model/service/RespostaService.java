package br.com.acessofacil.model.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acessofacil.model.dao.RespostaDAO;
import br.com.acessofacil.model.entity.Resposta;

@Service
public class RespostaService {
	RespostaDAO dao;
	
	@Autowired
	public RespostaService(RespostaDAO dao) {
		this.dao = dao;
	}
	
	
	public Resposta carregar(int id) throws IOException {
		return dao.carregar(id);
	}
	
	public int cadastrarResposta(int id, Resposta resposta) throws IOException{
		return dao.cadastraResposta(id, resposta);
	}
}
