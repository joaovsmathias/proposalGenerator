package br.com.jvsm.proposalgen.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.jvsm.proposalgen.models.Proposta;

public class RquisicaoPropostaDTO {
	
	public String nome;
	public List<String> produtos = new ArrayList<>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<String> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<String> produtos) {
		this.produtos = produtos;
	}
	
	public Proposta toProposta() {
		Proposta proposta = new Proposta();
		proposta.setIdentificao(nome);
		proposta.setData(LocalDateTime.now());
		//List<Produto> produtos = null;
		return proposta;
	}
	

}
