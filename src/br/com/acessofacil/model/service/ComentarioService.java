package br.com.acessofacil.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acessofacil.model.dao.ComentarioDAO;
import br.com.acessofacil.model.entity.Comentario;


@Service
public class ComentarioService {
	ComentarioDAO dao;
	
	@Autowired
	public ComentarioService(ComentarioDAO dao) {
		this.dao = dao;
	}
	
	
	public List<Comentario> listarReportados(){
		return dao.listarReportados();
	}
	
	
	
	public ArrayList<Comentario> listarComentariosReportados() throws IOException{
		return dao.listarComentariosReportados();
	}
	public ArrayList<Comentario> listarTodosComentariosReportados() throws IOException{
		return dao.listarTodosComentariosReportados();
	}
	public ArrayList<Comentario> listarBuscaComentario(String tipo, String texto) throws IOException{
		return dao.listarBuscaComentario(tipo, texto);
	}
	public Comentario carregar(int id) throws IOException{
		return dao.carregar(id);
	}
	public void inativar(int id){
		dao.inativar(id);
	}
	public void ativar(int id){
		dao.ativar(id);
	}
}
