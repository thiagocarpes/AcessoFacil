package br.com.acessofacil.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.acessofacil.model.entity.Comentario;
import br.com.acessofacil.model.entity.Estabelecimento;
import br.com.acessofacil.model.entity.Usuario;
import br.com.acessofacil.model.service.ComentarioService;
import br.com.acessofacil.model.service.EstabelecimentoService;
import br.com.acessofacil.model.service.UsuarioService;

@Controller
public class AdminController {
	UsuarioService us;
	EstabelecimentoService es;
	ComentarioService cos;

	@Autowired
	public AdminController(UsuarioService us, EstabelecimentoService es, ComentarioService cos) {
		this.us = us;
		this.es = es;
		this.cos = cos;
	}

	@RequestMapping("admin")
	public String inicio() {
		return "admin/login";
	}

	@RequestMapping("fazerLoginAdmin")
	public String fazerLogin(Usuario usuario, HttpSession session, HttpServletRequest request) {
		if (us.login(usuario, request)) {
			session.setAttribute("usuarioLogado", usuario);
			session.setAttribute("tipousuario", usuario.getTipoUsuario());
			System.out.println(usuario.getTipoUsuario());
			return "redirect:homeAdmin";
		}
		return "redirect:admin";
	}

	private List<Estabelecimento> listarTodosReivindicados() throws IOException {
		return es.listarTodosReivindicados();
	}

	private List<Estabelecimento> listarReivindicados() throws IOException {
		return es.listarReivindicados();
	}

	private List<Estabelecimento> listarDenunciados() throws IOException {
		return es.listarDenunciados();
	}

	private List<Estabelecimento> listarTodosDenunciados() throws IOException {
		return es.listarTodosDenunciados();
	}

	private List<Comentario> listarReportados() throws IOException {
		return cos.listarReportados();
	}

	@RequestMapping("/homeAdmin")
	public String indexAdmin(Model model) {
		try {
			model.addAttribute("reivindicados", listarReivindicados());
			model.addAttribute("denunciados", listarDenunciados());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "admin/homeAdmin";
	}

	@RequestMapping("logoutAdmin")
	public String logoutAdmin(HttpSession session, Model model) {
		session.invalidate();
		model.addAttribute("banner", true);
		return "admin/admin";
	}

	@RequestMapping("solicitadoAutenticacao")
	public String solicitadoAutenticacao(@RequestParam int id, Model model, Estabelecimento estabelecimento) {
		try {
			estabelecimento = es.carregar(estabelecimento.getId());
			model.addAttribute("estabelecimento", estabelecimento);
			return "admin/solicitadoAutenticacao";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("reivindicacoesSolicitadas")
	public String reivindicadosAutenticacao(Model model) {
		try {
			model.addAttribute("reinvidicados", listarTodosReivindicados());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/reivindicacoesSolicitadas";
	}

	@RequestMapping("estabelecimentoDenunciado")
	public String estabelecimentoDenunciado(@RequestParam int id, Model model, Estabelecimento estabelecimento) {
		try {
			estabelecimento = es.carregar(estabelecimento.getId());
			model.addAttribute("estabelecimento", estabelecimento);
			return "admin/estabelecimentoDenunciado";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("autenticarEstabelecimento")
	public String ativarEstabelecimento(@RequestParam int id, Model model, Estabelecimento estabelecimento)
			throws IOException {
		es.autenticar(id);

		try {
			estabelecimento = es.carregar(estabelecimento.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("estabelecimento", estabelecimento);
		return "admin/solicitadoAutenticacao";
	}
	
	@RequestMapping("rejeitarAutenticacao")
	public String rejeitarEstabelecimento(@RequestParam int id, Model model, Estabelecimento estabelecimento)
			throws IOException {
		es.rejeitar(id);

		try {
			estabelecimento = es.carregar(estabelecimento.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("estabelecimento", estabelecimento);
		return "admin/solicitadoAutenticacao";
	}
	
	@RequestMapping("deletarAutenticacao")
	public String deletarAutenticacao(@RequestParam int id, Model model, Estabelecimento estabelecimento)
			throws IOException {
		es.deleta(id);

		return "redirect:homeAdmin";
	}

	@RequestMapping("estabelecimentosDenunciados")
	public String estabelecimentosDenunciados(Model model) {
		try {
			model.addAttribute("estabelecimentos", listarTodosDenunciados());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/estabelecimentosDenunciados";
	}

	@RequestMapping("denunciarEstabelecimento")
	public String denunciarEstabelecimento(@RequestParam int id, @RequestParam String comentario,
			@RequestParam String motivo, Model model, HttpServletRequest request)
			throws IOException {
		int pId = Integer.parseInt(request.getParameter("id"));
		String pComentario = request.getParameter("comentario");
		String pMotivo = request.getParameter("motivo");
		
		System.out.println(pComentario);

		es.denunciar(pId, pComentario, pMotivo);

		return "publico/denunciaSucess";
	}

}
