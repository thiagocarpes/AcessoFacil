package br.com.acessofacil.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.acessofacil.model.entity.Categoria;
import br.com.acessofacil.model.entity.Estabelecimento;
import br.com.acessofacil.model.entity.Usuario;
import br.com.acessofacil.model.service.CategoriaService;
import br.com.acessofacil.model.service.EstabelecimentoService;
import br.com.acessofacil.model.service.QuestoesService;
import br.com.acessofacil.model.service.UsuarioService;

@Controller
public class UsuarioController {
	EstabelecimentoService es;
	CategoriaService cs;
	UsuarioService us;
	QuestoesService qs;
	
	@Autowired
	public UsuarioController(EstabelecimentoService es, CategoriaService cs, UsuarioService us, QuestoesService qs){
		this.es = es;
		this.cs = cs;
		this.us = us;
		this.qs = qs;
	}
	
	@RequestMapping("home")
	public String telaLogin(HttpSession session) {
		session.setAttribute("nome", session.getAttribute("nome"));
		return "usuario/home";
	}
	
	@RequestMapping("quemSomos")
	public String quemSomos() {
		return "padrao/quemSomos";
	}
	
	@RequestMapping("comoFunciona")
	public String comoFunciona() {
		return "padrao/comoFunciona";
	}
	
	
	@RequestMapping("/fazerLogin")
	public String fazerLogin(Usuario usuario, HttpSession session, HttpServletRequest request,
							Model model) throws ServletException, IOException{
		
		String mensagem = "Email e/ou senha inválido(s)";
		
		if(us.login(usuario, request)){
			if(usuario.getTipoUsuario()==1) {
			session.setAttribute("usuarioLogado", usuario);
			session.setAttribute("nome", usuario.getNome());
			session.setAttribute("id", usuario.getId());
			return "redirect:home";
			}
			else {
				model.addAttribute("mensagem", mensagem);
				return "publico/login";
			}
		}
		return "publico/login";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session, Model model){
		session.invalidate();
		model.addAttribute("banner", true);
		return "redirect:index";
	}	
	
	private List<Categoria> listarCategorias() throws IOException{
		return cs.listarCategorias();
	}
	
	@RequestMapping("cadastroEstabelecimento")
	public String cadastroEstabelecimento(Model model) {
		try {
			model.addAttribute("lista", listarCategorias());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "usuario/cadastroEstabelecimento";
	}	
	
	@Transactional
	@RequestMapping("newEstabelecimento")
	public String novoEstabelecimento(Estabelecimento estabelecimento){
		try {
			System.out.println(estabelecimento.getImagem());
			es.inserirEstabelecimento(estabelecimento);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "usuario/cadastroEstabSucess";
	}
	
	private ArrayList<Estabelecimento> listarBusca(String status, String tipo, String texto) throws IOException{
		return es.listarBusca(status, tipo, texto);
	}
	
	@RequestMapping("buscaEstabelecimentos")
	public String buscaEstabelecimentos(Model model, Estabelecimento estabelecimento, HttpServletRequest request) {			
		String pTexto = request.getParameter("texto");
		String pTipo = request.getParameter("tipo");
		try {
			model.addAttribute("lista", listarCategorias());
			model.addAttribute("estabelecimentos", listarBusca(estabelecimento.getStatus(),pTipo, pTexto));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "usuario/estabelecimentos";
	}
	
	@RequestMapping("meusDados")
	public String telaCadastro(@RequestParam int id, Model model) {
		try {
			model.addAttribute("usuario", us.carregar(id));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "usuario/meusDados";
	}
	
	@RequestMapping("alteraUsuario")
	public String alteraUsuario(@RequestParam int id, String nome, String email, String cpf, String senha, Model model) {
		
		
		us.alterar(id, nome, email, cpf, senha);
		String mensagem = "Cadastro alterado com sucesso";
		
		try {
			model.addAttribute("usuario", us.carregar(id));
			model.addAttribute("mensagem", mensagem);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return "usuario/meusDados";
	}
	
	private ArrayList<Estabelecimento> listarBuscaSimples(String texto, int page) throws IOException{
		return es.listarBuscaSimples(texto, page);
	}
	
	private int totalEstab(String texto) {
		return es.totalEstab(texto);
	}
	
	@RequestMapping("buscaSimples")
	public String buscaSimples(Model model, HttpServletRequest request) {
		String pTexto = request.getParameter("texto");
		int pPage = Integer.parseInt(request.getParameter("page"));
		
		int total = totalEstab(pTexto);
		int paginas = total / 5;
		
		if(total % 5 > 0){
			paginas = paginas + 1;
		}
			
		model.addAttribute("texto", pTexto);
		model.addAttribute("paginas", paginas);

		try {
			model.addAttribute("busca", listarBuscaSimples(pTexto, pPage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "usuario/buscaSimples";
	}
	
	
}
