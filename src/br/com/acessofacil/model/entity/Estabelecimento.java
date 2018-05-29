package br.com.acessofacil.model.entity;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="estabelecimento")
public class Estabelecimento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="idCategoria")
	private int idCategoria;
	
	@Column(name="nomeFantasia")
	private String nomeFantasia;
	
	@Column(name="cep")
	private String cep;
	
	@Column(name="bairro")
	private String bairro;
	
	@Column(name="cidade")
	private String cidade;
	
	@Column(name="estado")
	private String estado;
	
	@Column(name="endereco")
	private String endereco;
	
	@Column(name="numero")
	private int numero;

	@Column(name="cnpj")
	private String CPNJ;
	
	@Column(name="senha")
	private String senha;
	
	@Column(name="dtCadastro")
	private String dtCadastro;
	
	@Column(name="telefone")
	private String telefone;
	
	@Column(name="email")
	private String email;
	
	@Column(name="dtReivindicacao")
	private String dtReinvidicacao;
	
	@Column(name="dtDenuncia")
	private String dtDenuncia;
	
	@Column(name="comentarioDenuncia")
	private String comentarioDenuncia;
	
	@Column(name="motivoDenuncia")
	private String motivoDenuncia;
	
	@Column(name="status")
	private String status;
	
	@Column(name="imagem")
	private String imagem;
	
	@Column(name="tipousuario")
	private int tipoUsuario;	
	
	@Transient
	private ArrayList<ComentariosEstabelecimentos> comentarios;
	
	@Transient
	private int statusGeral;
	
	@Transient
	private ArrayList<Avaliacao> avaliacoes;

	public Estabelecimento() {}
	
	public ArrayList<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(ArrayList<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	
	public int getStatusGeral() {
		return this.statusGeral;
	}
	public void setStatusGeral(int statusGeral) {
		this.statusGeral = statusGeral;
	}
	public ArrayList<ComentariosEstabelecimentos> getComentariosEstabelecimentos() {
		return comentarios;
	}

	public void setComentariosEstabelecimentos(ArrayList<ComentariosEstabelecimentos> comentarios) {
		this.comentarios = comentarios;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int id) {
		this.idCategoria = id;
	}
	
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCPNJ() {
		return CPNJ;
	}
	public void setCPNJ(String cPNJ) {
		CPNJ = cPNJ;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(String dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDtReinvidicacao() {
		return dtReinvidicacao;
	}
	public void setDtReinvidicacao(String dtReinvidicacao) {
		this.dtReinvidicacao = dtReinvidicacao;
	}
	public String getDtDenuncia() {
		return dtDenuncia;
	}
	public void setDtDenuncia(String dtDenuncia) {
		this.dtDenuncia = dtDenuncia;
	}
	public String getComentarioDenuncia() {
		return comentarioDenuncia;
	}
	public void setComentarioDenuncia(String comentarioDenuncia) {
		this.comentarioDenuncia = comentarioDenuncia;
	}
	public String getMotivoDenuncia() {
		return motivoDenuncia;
	}
	public void setMotivoDenuncia(String motivoDenuncia) {
		this.motivoDenuncia = motivoDenuncia;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	
}
