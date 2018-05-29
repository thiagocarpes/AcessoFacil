package br.com.acessofacil.model.service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acessofacil.model.dao.UsuarioDAO;
import br.com.acessofacil.model.entity.Usuario;

@Service
public class UsuarioService {
	UsuarioDAO dao;
	
	@Autowired
	public UsuarioService(UsuarioDAO dao) {
		this.dao = dao;
	}
	
	public Usuario carregar(int id) throws IOException{
		return dao.carregar(id);
	}
	
	public int inserirUsuario(Usuario usuario) throws IOException{
		usuario.setDtCadastro(new Date());
		usuario.setTipoUsuario(1);
		return dao.inserirUsuario(usuario);
	}
	
	public void alterar(int id, String nome, String email, String cpf, String senha) {
		dao.alterar(id, nome, email, cpf, senha);
	}
	
	public boolean login(Usuario usuario, HttpServletRequest request){
		return dao.login(usuario, request);
	}
}
