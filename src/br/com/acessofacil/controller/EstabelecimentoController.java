package br.com.acessofacil.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Consumer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.acessofacil.model.entity.Avaliacao;
import br.com.acessofacil.model.entity.Categoria;
import br.com.acessofacil.model.entity.Estabelecimento;
import br.com.acessofacil.model.entity.Resposta;
import br.com.acessofacil.model.service.AvaliacaoService;
import br.com.acessofacil.model.service.CategoriaService;
import br.com.acessofacil.model.service.EstabelecimentoService;
import br.com.acessofacil.model.service.QuestoesService;
import br.com.acessofacil.model.service.RespostaService;

@Controller
public class EstabelecimentoController {

	EstabelecimentoService es;
	QuestoesService qst;
	AvaliacaoService aval;
	CategoriaService cat;
	RespostaService res;

	@Autowired
	public EstabelecimentoController(EstabelecimentoService es, QuestoesService qst, AvaliacaoService aval,
									CategoriaService cat, RespostaService res) {
		this.es = es;
		this.qst = qst;
		this.aval = aval;
		this.cat = cat;
		this.res = res;
	}

	@RequestMapping("minhaConta")
	public String telaLogin(HttpSession session) {
		session.setAttribute("tipousuario", session.getAttribute("tipousuario"));
		session.setAttribute("nomeFantasia", session.getAttribute("nomeFantasia"));
		return "estabelecimento/minhaConta";
	}
	
	@RequestMapping("responderComents")
	public String responder(HttpServletRequest request, Resposta resposta, Estabelecimento estabelecimento, Model model){
		int pId = Integer.parseInt(request.getParameter("idAval"));
		int pIdEstab = Integer.parseInt(request.getParameter("idEstabelecimento"));
		
		String mensagem = "Sua resposta foi enviada";
		try {
				res.cadastrarResposta(pId, resposta);
				model.addAttribute("mensagem", mensagem);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:estabelecimento?id="+pIdEstab;
		}
		
		
		return "redirect:estabelecimento?id="+pIdEstab;
	}
	
	
	@RequestMapping("dadosEstabelecimento")
	public String dadosEstabelecimento(@RequestParam int id, Model model) {
		
		
		try {
			model.addAttribute("estabelecimento", es.carregar(id));
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "estabelecimento/dadosEstabelecimento";
	}
	
	@RequestMapping("alterarDados")
	public String alterarDados(Estabelecimento estabelecimento, Model model) {

		es.alterar(estabelecimento);
		String mensagem = "Cadastro alterado com sucesso";
		try {
			model.addAttribute("estabelecimento", es.carregar(estabelecimento.getId()));
			model.addAttribute("mensagem", mensagem);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "estabelecimento/dadosEstabelecimento";
	}
	
	
	
	private List<Categoria> listarCategorias() throws IOException {
		return cat.listarCategorias();
	}

	@RequestMapping(value = "/estabelecimento", params = { "id" })
	public String VerEstabelecimento(@RequestParam(value = "id") int id, HttpServletRequest request, Model model,
			HttpSession session) throws ServletException, IOException {
		try {

			int idUser = 0;
			if (request.getSession().getAttribute("id") != null) {
				idUser = (int) request.getSession().getAttribute("id");
			}

			Estabelecimento ee = es.carregar(id);

			model.addAttribute("StatusGeral", ee.getStatusGeral());
			model.addAttribute("idEstabelecimento", id);
			model.addAttribute("estabelecimento", ee);
			model.addAttribute("comentarios", ee.getComentariosEstabelecimentos());
			model.addAttribute("questoes", qst.listarQuestoes());
			model.addAttribute("userAval", aval.UsuarioAvaliacao(idUser, id));
			model.addAttribute("usuarioLogado", request.getSession().getAttribute("usuarioLogado"));
			model.addAttribute("lista", listarCategorias());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "estabelecimento/estabelecimento";
	}

	@RequestMapping("cadastraAvaliacao")
	public String cadastraAvaliacao(@RequestParam("idQuestao[]") List<String> to, HttpServletRequest request,
			Model model) throws ServletException, IOException {
		try {
			for (String idQuestao : to) {
				Avaliacao avaliacao = new Avaliacao();
				avaliacao.setIdQuestao(Integer.parseInt(idQuestao));
				avaliacao.setIdEstabelecimento(Integer.parseInt(request.getParameter("idEstabelecimento")));
				avaliacao.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
				avaliacao.setAvaliacao(Integer.parseInt(request.getParameter("avaliacao_" + idQuestao)));
				avaliacao.setComentario(request.getParameter("comentario"));
				aval.cadastra(avaliacao);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect: estabelecimento?id=" + request.getParameter("idEstabelecimento");
	}

	public static String buscarCep(String cep) {
		String json;

		try {
			URL url = new URL("http://viacep.com.br/ws/" + cep + "/json");
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			final StringBuilder jsonSb = new StringBuilder();

			br.lines().forEach(new Consumer<String>() {
				@Override
				public void accept(String l) {
					jsonSb.append(l.trim());
				}
			});

			json = jsonSb.toString();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return json;
	}

	@RequestMapping(value = "getAdress", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String getAdress(HttpServletRequest request) throws MalformedURLException, IOException {
		String cep = request.getParameter("cep");
		return buscarCep(cep);
	}
	
	@RequestMapping(value = "getEstab", method = RequestMethod.POST, produces = "text/html")
	@ResponseBody
	public String getEstab(HttpServletRequest request) throws MalformedURLException, IOException {
		String kw = request.getParameter("kw");
		String html = "";
		int cc = 0;
		
		if(kw != null && kw != "") {
			List<Estabelecimento> allestab = es.listarEstabelecimentos("WHERE (e.nomeFantasia like '%" + kw + "%')", false);
			
			for(Estabelecimento rst : allestab) {
				if(cc == 8) { break; }
				html += "<div style='font-size:16px;'><img style='width:100px;height:70px;float:left;margin-right:10px;' src='image/estabelecimentos/"+rst.getImagem()+"'><b>"+rst.getNomeFantasia()+"</b><br><small>"+rst.getBairro()+", "+rst.getCidade()+" - "+rst.getEstado()+"</small><br>&nbsp;";
				html += "<button onclick='window.location.href=\"estabelecimento?id="+rst.getId()+"\"' type='button' name='command' value='entrar' class='avaliar' style='float: right;top: -26px;border-radius: 4px;'><spam class='glyphicon glyphicon-th-list'></spam>Avaliar</button><hr></div>";
				cc++;
			}
			
			byte[] bytes = html.getBytes(StandardCharsets.UTF_8);
			html = new String(bytes, StandardCharsets.ISO_8859_1);
		}

		return html;
	}

	@RequestMapping("loginEstab")
	public String loginEstabelecimento(Estabelecimento estabelecimento, HttpSession session,
										HttpServletRequest request, Model model) {
		
		String mensagem = "Email e/ou senha inválido(s).";
		try {

			if (es.validarEstabelecimento(estabelecimento, request)) {

				session.setAttribute("usuarioLogado", estabelecimento);

				return "redirect:estabelecimento?id=" + estabelecimento.getId();
			}
			else {
				model.addAttribute("mensagem", mensagem);
				return "publico/loginEstabelecimento";
			}
		} catch (IOException e) {
			e.printStackTrace();
			
			return "publico/loginEstabelecimento";
		}

	}

	/*@RequestMapping("estabelecimentoInvalido")
	public String invalido() {
		return "estabelecimento/estabelecimentoInvalido";
	}*/

	@RequestMapping("solicitaValidacao")
	public String solicitaValidacao(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		Estabelecimento est = new Estabelecimento();
		// Create path components to save the file
		final String UPLOAD_DIRECTORY = "C:\\Users\\supor\\eclipse-workspace\\AcessoFacil8.0\\WebContent\\image\\estabelecimentos";

		try {
			List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

			for (FileItem item : multiparts) {
				if (!item.isFormField()) {
					String name = new File(item.getName()).getName();
					est.setImagem(name);
					item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
				}
				if (item.getFieldName().equals("id")) {
					est.setId(Integer.parseInt(item.getString()));
				}
				if (item.getFieldName().equals("nomeFantasia")) {
					est.setNomeFantasia(item.getString());
				}
				if (item.getFieldName().equals("idCategoria")) {
					est.setIdCategoria(Integer.parseInt(item.getString()));
				}
				if (item.getFieldName().equals("cep")) {
					est.setCep(item.getString());
				}
				if (item.getFieldName().equals("endereco")) {
					est.setEndereco(item.getString());
				}
				if (item.getFieldName().equals("numero")) {
					est.setNumero(Integer.parseInt(item.getString()));
				}
				if (item.getFieldName().equals("bairro")) {
					est.setBairro(item.getString());
				}
				if (item.getFieldName().equals("estado")) {
					est.setEstado(item.getString());
				}
				if (item.getFieldName().equals("cidade")) {
					est.setCidade(item.getString());
				}
				if (item.getFieldName().equals("telefone")) {
					est.setTelefone(item.getString());
				}
				if (item.getFieldName().equals("email")) {
					est.setEmail(item.getString());
				}
				if (item.getFieldName().equals("senha")) {
					est.setSenha(item.getString());
				}
				
				est.setTipoUsuario(1);
				es.solicitaValidacao(est);

			}
			

		} catch (Exception ex) {

		}

		model.addAttribute("idEstabelecimento", est.getId());

		return "estabelecimento/solicitacaoSucess";
	}

}
