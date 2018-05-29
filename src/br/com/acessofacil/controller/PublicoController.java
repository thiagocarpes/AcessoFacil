package br.com.acessofacil.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.acessofacil.model.entity.Categoria;
import br.com.acessofacil.model.entity.Estabelecimento;
import br.com.acessofacil.model.entity.Usuario;
import br.com.acessofacil.model.service.CategoriaService;
import br.com.acessofacil.model.service.EstabelecimentoService;
import br.com.acessofacil.model.service.UsuarioService;

@Controller
public class PublicoController {
	UsuarioService us;
	EstabelecimentoService est;
	CategoriaService cat;

	@Autowired
	public PublicoController(UsuarioService us, EstabelecimentoService est, CategoriaService cat) {
		this.us = us;
		this.est = est;
		this.cat = cat;
	}

	@Transactional
	@RequestMapping("index")
	public String inicio(HttpServletRequest request, Model model, Categoria categoria) throws IOException {

		String whereQuery = "";
		String CatName = "";
		String type = request.getParameter("type");
		String pesquisar = request.getParameter("pesquisar");
		Boolean ScrollTo = false;
		int idCat = 0;

		if (pesquisar != null && pesquisar != "") {

			pesquisar = pesquisar.replace("'", "");
			pesquisar = pesquisar.replace("\\", "");
			byte[] bytes = pesquisar.getBytes(StandardCharsets.ISO_8859_1);
			pesquisar = new String(bytes, StandardCharsets.UTF_8);
			
			whereQuery = " WHERE (e.nomeFantasia like '%" + pesquisar + "%' OR e.cidade like '%" + pesquisar + "%' OR e.bairro like '%" + pesquisar + "%') ";
			ScrollTo = true;
		}
		
		if (categoria.getId() != 0) {
			whereQuery = " WHERE e.idCategoria=" + categoria.getId();
			List<Categoria> dadosCat = cat.getCategoria(categoria.getId());
			CatName = dadosCat.get(0).getNome();
			idCat = dadosCat.get(0).getId();
			ScrollTo = true;
		}
		
		List<Estabelecimento> allestab = est.listarEstabelecimentos(whereQuery, false);
		
		if (type != null && type != "") {
			List<Estabelecimento> allestab2 = new ArrayList<>();
			for(Estabelecimento rst : allestab) {
				if(type.equals("M") && rst.getStatusGeral() >= 2) { allestab2.add(rst); }
				if(type.equals("P") && rst.getStatusGeral() > 0 && rst.getStatusGeral() < 2) { allestab2.add(rst); }				
			}
			allestab = allestab2;
		}
				
		model.addAttribute("banner", true);
		model.addAttribute("type", type);
		model.addAttribute("pesquisar", pesquisar);
		model.addAttribute("idCat", idCat);
		model.addAttribute("estabelecimentos", allestab);
		model.addAttribute("categorias", cat.listarCategorias());
		model.addAttribute("CatName", CatName);
		model.addAttribute("ScrollTo", ScrollTo);
		model.addAttribute("total", allestab.size());
		return "publico/index";
	}

	@RequestMapping("cadastro")
	public String telaCadastro() {
		return "publico/cadastro";
	}

	@RequestMapping("login")
	public String telaLogin() {
		return "publico/login";
	}

	@RequestMapping("loginEstabelecimento")
	public String telaLoginEstabelecimento() {
		return "publico/loginEstabelecimento";
	}

	@Transactional
	@RequestMapping("novoUsuario")
	public String novoUsuario(Usuario usuario, HttpServletRequest request) {
		String confirmaSenha = request.getParameter("confirmaSenha");
		
		System.out.println(usuario.getSenha()+" "+usuario.getSenha().length()+" "+ confirmaSenha);
		if (usuario.getSenha().length() >= 6 && usuario.getSenha().length() <= 10) {
			try {
				us.inserirUsuario(usuario);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "usuario/cadastroSucess";
		}else {
			return "publico/cadastro";
		}
	}

}
