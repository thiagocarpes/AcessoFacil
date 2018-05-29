package br.com.acessofacil.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acessofacil.model.dao.QuestoesDAO;
import br.com.acessofacil.model.entity.Questoes;

@Service
public class QuestoesService {
	QuestoesDAO dao; 
	
	@Autowired
	public QuestoesService(QuestoesDAO dao){
		this.dao = dao;
	}
	
	public ArrayList<Questoes> listarQuestoes() throws IOException{
		return dao.listarQuestoes();
	}
	
	public List<Questoes> listarTodasQuestoes(){
		return dao.listarTodasQuestoes();
	}
	
}
