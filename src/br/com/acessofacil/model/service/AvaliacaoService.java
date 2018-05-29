package br.com.acessofacil.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.acessofacil.model.dao.AvaliacaoDAO;
import br.com.acessofacil.model.entity.Avaliacao;

@Service
public class AvaliacaoService {
	AvaliacaoDAO dao = new AvaliacaoDAO();
	public int cadastra(Avaliacao avaliacao) throws IOException {		
		return dao.cadastra(avaliacao);
	}
	public List<Avaliacao> UsuarioAvaliacao(int IdUser, int idEst) {
		return dao.UsuarioAvaliacao(IdUser, idEst);
	}
}
