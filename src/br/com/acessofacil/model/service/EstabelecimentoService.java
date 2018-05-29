package br.com.acessofacil.model.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.acessofacil.model.dao.EstabelecimentoDAO;
import br.com.acessofacil.model.entity.Estabelecimento;

@Service
public class EstabelecimentoService {
	EstabelecimentoDAO dao;
	
	@Autowired
	public EstabelecimentoService(EstabelecimentoDAO dao){
		this.dao = dao;
	}
	
	public int inserirEstabelecimento(Estabelecimento estabelecimento) throws IOException{
		return dao.inserirEstabelecimento(estabelecimento);
	}
	public Estabelecimento carregar(int id) throws IOException{
		return dao.carregar(id);
	}
	public void autenticar(int id) throws IOException{
		dao.autenticar(id);;
	}
	public void denunciar(int id, String comentario, String motivo) throws IOException{
		dao.denunciar(id, comentario, motivo);
	}
	public void rejeitar(int id) throws IOException{
		dao.rejeitar(id);
	}
	public void deleta(int id) throws IOException{
		dao.deleta(id);
	}
	public List<Estabelecimento> listarTodosReivindicados(){
		return dao.listarTodosReivindicados();
	}
	public List<Estabelecimento> listarReivindicados(){
		return dao.listarReivindicados();
	}
	public List<Estabelecimento> listarTodosDenunciados(){
		return dao.listarTodosDenunciados();
	}
	public List<Estabelecimento> listarDenunciados(){
		return dao.listarDenunciados();
	}
	
	public ArrayList<Estabelecimento> listarBuscaSimples(String texto, int page) throws IOException{
		return dao.listarBuscaSimples(texto, page);
	}
	
	public List<Estabelecimento> listarEstabelecimentos(String where, boolean showAll) throws IOException {
		return dao.listarEstabelecimentos(where, showAll);
	}
	
	public ArrayList<Estabelecimento> listarBusca(String status, String tipo, String texto) throws IOException{
		return dao.listarBusca(status, tipo, texto);
	}
	public int totalEstab(String texto){
		return dao.totalEstab(texto);
	}
	
	public void solicitaValidacao(Estabelecimento est) {
		dao.solicitaValidacao(est);
	}
	public boolean validarEstabelecimento(Estabelecimento estabelecimento, HttpServletRequest request) throws IOException {
		
		return dao.validarEstabelecimento(estabelecimento, request);
	}
	public void alterar(Estabelecimento estabelecimento) {
		dao.alterar(estabelecimento);
	}
	
	
}
