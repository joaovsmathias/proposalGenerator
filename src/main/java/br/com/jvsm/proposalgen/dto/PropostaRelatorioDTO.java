package br.com.jvsm.proposalgen.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PropostaRelatorioDTO {
	private Long propostaId;
	private LocalDate dataProposta;
	private Long produtosPropostaId;
	private String nomeProduto;
	private String descricaoProduto;
	private Integer quantidade;
	private BigDecimal desconto;
	private BigDecimal produtoValor;

	/*	
	public PropostaRelatorioDTO() {

	}*/

	public PropostaRelatorioDTO(Long propostaId, Long produtosPropostaId, String nomeProduto,
			String descricaoProduto, Integer quantidade, BigDecimal desconto,BigDecimal produtoValor) {

		this.propostaId = propostaId;
		this.produtosPropostaId = produtosPropostaId;
		this.nomeProduto = nomeProduto;
		this.descricaoProduto = descricaoProduto;
		this.quantidade = quantidade;
		this.desconto = desconto;
		this.produtoValor = produtoValor;

	}

	/*
	public PropostaRelatorioDTO(Long propostaId, LocalDate dataProposta, Long produtosPropostaId, String nomeProduto,
			String descricaoProduto, Integer quantidade, BigDecimal desconto) {
		this.propostaId = propostaId;
		this.dataProposta = dataProposta;
		this.produtosPropostaId = produtosPropostaId;
		this.nomeProduto = nomeProduto;
		this.descricaoProduto = descricaoProduto;
		this.quantidade = quantidade;
		this.desconto = desconto;
	}*/
	public Long getPropostaId() {
		return propostaId;
	}
	public void setPropostaId(Long propostaId) {
		this.propostaId = propostaId;
	}
	
	public BigDecimal getProdutoValor() {
		return produtoValor;
	}

	public void setProdutoValor(BigDecimal produtoValor) {
		this.produtoValor = produtoValor;
	}

	public LocalDate getDataProposta() {
		return dataProposta;
	}
	public void setDataProposta(LocalDate dataProposta) {
		this.dataProposta = dataProposta;
	}
	public Long getProdutosPropostaId() {
		return produtosPropostaId;
	}
	public void setProdutosPropostaId(Long produtosPropostaId) {
		this.produtosPropostaId = produtosPropostaId;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getDescricaoProduto() {
		return descricaoProduto;
	}
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getDesconto() {
		return desconto;
	}
	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

}
