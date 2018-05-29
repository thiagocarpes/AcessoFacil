package br.com.acessofacil.model.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.acessofacil.model.entity.Categoria;

@Repository
public class CategoriaDAO {
	@PersistenceContext
	EntityManager manager;	
	
	@SuppressWarnings("unchecked")
	public List<Categoria> listarCategorias() throws IOException{
		return manager.createQuery("SELECT categoria FROM Categoria categoria").getResultList();
	}
	
	public List<Categoria> getCategoria(int id) throws IOException{
		return manager.createQuery("SELECT categoria FROM Categoria categoria WHERE categoria.id="+id).getResultList();
	}
}
