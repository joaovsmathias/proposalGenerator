package br.com.jvsm.proposalgen.dto;

import java.math.BigDecimal;

import br.com.jvsm.proposalgen.models.Produto;

public class RequisicaoProdutoDTO {
	private String nome;
	private String descricao;
	private String valor;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public Produto toProduto() {
		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setDescricao(descricao);
		produto.setValor(new BigDecimal(valor));
		return produto;
	}
	

}
