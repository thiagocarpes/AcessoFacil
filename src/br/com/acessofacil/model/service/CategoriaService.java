package br.com.acessofacil.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acessofacil.model.dao.CategoriaDAO;
import br.com.acessofacil.model.entity.Categoria;

@Service
public class CategoriaService {
	CategoriaDAO dao;
	
	@Autowired
	public CategoriaService(CategoriaDAO dao){
		this.dao = dao;
	}
	
	public List<Categoria> listarCategorias() throws IOException{
		return dao.listarCategorias();
	}
	
	public List<Categoria> getCategoria(int id) throws IOException {
		return dao.getCategoria(id);
	}
}
